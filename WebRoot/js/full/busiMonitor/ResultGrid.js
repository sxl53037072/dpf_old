// 得到url搜索串
function getURLSearch(isParent)
{
	var strSearch ="";
	if(isParent){
	 strSearch= window.parent.location.search;
	}
	else{
		 strSearch= location.search;
	}
	var reg1 = /=/gi;
	var reg2 = /&/gi;
	strSearch = strSearch.substr(1, strSearch.length);
	strSearch = strSearch.replace(reg1, '":"');
	strSearch = strSearch.replace(reg2, '","');
	if (strSearch == "")
		return null;
	else
		strSearch = '{"' + strSearch + '"}';
	eval("var ArrayUrl=" + strSearch);
	return ArrayUrl;
}

function getUrlParam()
{
	return getURLSearch() || {};
}
var ResultGrid = {
	local : "",
	GET_DATA_URL : "busiMonitor/result/",
	_isNull : function(str){
		if(str == "" || str == "undefined" || str == null || str == "null"){
			return true;
		}
		return false;
	},
	jsonToObj:function(str){
		if(!str)return null;
		if(typeof str == 'object')return str;
		try{
	    	return Function("return "+str)();
		}catch(e){ return null;}
	},
	getData : function(option){
		var setting = {
			url : "",
			data : {},
			callback : ""
		};	
		$.extend(setting,option);
		var data = {};
		jQuery.ajax({
			url: ResultGrid.local+"/"+setting.url,
			type:"post",
			async:false,
			data:setting.data,
			dataType : "json",
			success: function(returnStr){
				data = returnStr;
				if(setting.callback)setting.callback(data);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown){
				alert("error getData ="+textStatus+"_"+errorThrown+"_"+setting.url);
			}
		});
		return data;
	},
	importFile : function(url, type){
		if(this._isNull(url))return;
		if(type == "css"){
			var oImportCss = window.document.createElement("link");
			oImportCss.rel = "stylesheet";
			oImportCss.type = "text/css";
			oImportCss.href = url;
			document.getElementsByTagName("head").item(0).appendChild(oImportCss);   
		}else if(type == "js"){
			var oImportJs = window.document.createElement("script");
			oImportJs.language = "javascript";
			oImportJs.type = "text/javascript";
			oImportJs.defer = true;
			oImportJs.src = url;
			document.getElementsByTagName("head").item(0).appendChild(oImportJs);   
		}
	},
	clone:function(fromObj){    
	   var toObj = {};
	   for(var i in fromObj){    
	      if(typeof fromObj[i] == "object"){    
	         toObj[i]={};    
	         toObj[i] = ResultGrid.clone(fromObj[i]);    
	         continue;    
	      }    
	      toObj[i] = fromObj[i];    
	   }
	   return toObj;    
	},
	setColModel : function(colModel){
		for(var i=0; i<colModel.length; i++){
			if(!this._isNull(colModel[i]["config_script"])){
				colModel[i] = $.extend(colModel[i], ResultGrid.jsonToObj(colModel[i]["config_script"]));
			}
		}
		return colModel;
	},
	setValueCfg : function(valueCfg, jqObj, $grid){
		if(!ResultGrid._isNull(valueCfg["title"])){
			jqObj["caption"] = valueCfg["title"];
		}
		if(valueCfg["is_page"] == "0BF"){
			jqObj["pager"] = "";
		}else{
			jqObj["pager"] = jqObj["pager"]?jqObj["pager"]:$grid.attr("id")+"_pager";
		}
		if(!ResultGrid._isNull(valueCfg["page_size"])){
			jqObj["rowNum"] = valueCfg["page_size"];
		}
		if(valueCfg["is_forcefit"] == "0BF"){
			jqObj["shrinkToFit"] = true;
		}else{
			jqObj["shrinkToFit"] = false;
		}		
		if(!ResultGrid._isNull(valueCfg["hidden_columns"])){
			var hideArr = valueCfg["hidden_columns"].split(",");
			$.each(hideArr, function(i, v){
				$.each(jqObj["colModel"], function(ii, vv){
					if(v.toUpperCase() == vv.name.toUpperCase()){
						vv.hidden = true;
					}
				});
			});
		}			
		if(!ResultGrid._isNull(valueCfg["dblclick"])){	
			jqObj["ondblClickRow"] = function(rowid,iRow,iCol,e){
				eval(valueCfg["dblclick"])(rowid,iRow,iCol,e);
			};
		}
		if(!ResultGrid._isNull(valueCfg["import_js"])){
			var jsArr = valueCfg["import_js"].split(",");
			$.each(jsArr, function(i, v){
				ResultGrid.importFile(ResultGrid.local+v, "js");
			});		
		}
		
		
		if(!ResultGrid._isNull(valueCfg["import_css"])){
			var cssArr = valueCfg["import_css"].split(",");
			$.each(cssArr, function(i, v){
				ResultGrid.importFile(ResultGrid.local+v, "css");
			});		
		}
		if(!ResultGrid._isNull(valueCfg["config_script"])){
			var obj = this.jsonToObj(valueCfg["config_script"]);
			jqObj = $.extend(true, jqObj, obj);
		}
		
	},
	setToolbar : function(toolbarMenu, jqObj, $grid){
		if(toolbarMenu && toolbarMenu.length > 0){
			if(!ResultGrid._isNull(toolbarMenu[0]["import_js"])){
				ResultGrid.importFile(ResultGrid.local+toolbarMenu[0]["import_js"], "js");
			}			
			var btns = [];
			for(var i=0; i<toolbarMenu.length; i++){
				var menu = toolbarMenu[i];
				ResultGrid["toolbar_"+menu["item_label"]] = menu;
				if(menu["display"] == 1){
					if(menu["is_line"] == "0BT"){
						btns.push("-");
					}else{
						var css = {};
						if(menu["ico"]){
					      css = {"background" : "transparent url("+ResultGrid.local+"/js/full/toolbar/image/"+menu["ico"]+") no-repeat 0 2px",
						  		 "padding-left" : "20px"};
						}
						btns.push({
							text : menu["item_label"],
							css : css,		
							handler : function(){
								var ev = ResultGrid["toolbar_"+$.trim($(this).html())]["event"];
								if(!ResultGrid._isNull(ev)){
									eval(ev).apply(this, arguments);
								}								
							}
						});
					}
				}
			}
			if(btns.length > 0)jqObj["mytoolbar"] = btns;
		}
	},
	setSqlParam : function(sqlParamData, valueCfg, $grid){
		/*
		 *  <div id="GRIDID_search" class="grid_search">
		 *		<label id="GRIDID_COLUMNID_label">XXXX</label>
		 *		<input type="text" ID="COLUMNID" />
		 *	</div>
		 */
		if(sqlParamData.length > 0){
			var gridId = $grid.attr("id");
			var $gridSearch = $("<div id='"+gridId+"_search' to='"+gridId+"' class='grid_search'>");
			var columnNum = 3;
			if(!this._isNull(valueCfg)){
				columnNum = valueCfg["column_num"] || 3;
			}
			for(var i=0; i<sqlParamData.length; i++){
				var $label = $("<label id='"+(gridId+"_"+sqlParamData[i]["param_name"]+"_label")+"' >"+sqlParamData[i]["param_label"]+"</label>");
				var $el = ResultGrid._setSearch(sqlParamData[i]);					
				$gridSearch.append($label).append($el);
				if(i!=0 && (i+1)%columnNum==0){
					$gridSearch.append("<div></div>");
				}
			}	
			var $search_btn = $("<input type='button' onclick='ResultGrid.search(this)' style='margin-left:5px' id='"+gridId+"_search_btn' value='搜索' /> ");
			$gridSearch.append($search_btn);
			$grid.before($gridSearch);
		}
	},
	_setSearch : function(data){		
		var comp_name = data["comp_name"];
		var comp_cfg = data["comp_cfg"] || {};
		var $ele;
		if(comp_name == "textfield"){
			$ele = $("<input class='search_element' type='text' id='"+data["param_name"]+"' >");					
		}else if(comp_name == "result_datefield"){
			$ele = $("<input id='"+data["param_name"]+"' type='text' class='Wdate search_element' onClick='WdatePicker()'>");//onfocus='WdatePicker({skin:\"blueFresh\",dateFmt:\"yyyy-MM-dd\"});'			
		}else if(comp_name == "arrayselect"){
			$ele = $("<select class='search_element' id='"+data["param_name"]+"' >");
			var comp_ds = data["comp_ds"] || {};
			for(var i=0; i<comp_ds.length; i++){
				var $option = $("<option value='"+comp_ds[i][0]+"'>"+comp_ds[i][1]+"</option> ");
				$ele.append($option);
			}			
		}else if(comp_name == "sqlselect"){
			var comp_ds = data["comp_ds"];
			$ele = $("<select class='search_element' id='"+data["param_name"]+"' >");
			if(comp_ds){
				var data = ResultGrid.getData({url:ResultGrid.GET_DATA_URL+"execSql",data:{"sql":comp_ds}});
				if(filterData(data) != null){
					data = data.data.list;
					for(var i=0; i<data.length; i++){
						var $option = $("<option value='"+data[i]["value"]+"'>"+data[i]["name"]+"</option> ");
						$ele.append($option);
					}
				}
			}			
		}
		if($ele){
			for(var c in comp_cfg){
				if(c == "width" || c == "height"){
					$ele.css(c, comp_cfg[c]);
				}else {
					$ele.attr(c, comp_cfg[c]);
				}				
			}	
		}
		return $ele;
	},
	search : function(me){
		var $grid_search = $(me).closest(".grid_search");
		var gridId = $grid_search.attr("to");
		var $grid = $("#"+gridId);
		var postData = {};
		$grid_search.find(".search_element").each(function(){
			postData[$(this).attr("id")] = $(this).val();
		});		
		$grid.setGridParam({"page":1});
		$grid.setGridParam({"postData":""});
		$grid.setGridParam({"postData":postData});
		$grid.trigger("reloadGrid");	
	}
};

$.fn.ResultGrid = function(options){	
	var $grid = $(this);
	var resultGrid_default = {
		mtype : 'post',
		loadui : "block"
	};
	if(options.result){
		var gridConfig = ResultGrid.getData({url:ResultGrid.GET_DATA_URL+"config/"+options.result});	
		if(gridConfig && gridConfig.succeed && gridConfig.data){
			gridConfig = gridConfig.data;
		}
		var postData = options.resultParam || {};
		var jqObj = $.extend(true, {}, resultGrid_default, options, {"url":ResultGrid.local+"/"+ResultGrid.GET_DATA_URL+"list/"+options.result,"postData":postData});
		jqObj["colModel"] = $.merge(ResultGrid.setColModel(gridConfig["colModel"]) || [], options.colModel || []);//数据库列定义+页面列定义		
		ResultGrid.setValueCfg(gridConfig["valueCfg"], jqObj, $grid);		
		ResultGrid.setSqlParam(gridConfig["sqlParam"], gridConfig["valueCfg"], $grid);
		ResultGrid.setToolbar(gridConfig["toolbar_menu"], jqObj, $grid);
		$grid.jqGrid(jqObj);
	}else{
		alert("缺少 result 参数");
	}
};
