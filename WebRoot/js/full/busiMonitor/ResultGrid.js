var ResultGrid = {
	local : "",
	_isNull : function(str){
		if(str == "" || str == "undefined" || str == null || str == "null"){
			return true;
		}
		return false;
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
				var oImportJs = window.document.createElement("script");
				oImportJs.language = "javascript";
				oImportJs.type = "text/javascript";
				oImportJs.defer = true;
				oImportJs.src = ResultGrid.local+v;
				var oLastJs = document.scripts[document.scripts.length - 1];
				oLastJs.insertAdjacentElement('afterEnd', oImportJs);
			});		
		}
		if(!ResultGrid._isNull(valueCfg["import_css"])){
			var cssArr = valueCfg["import_css"].split(",");
			$.each(cssArr, function(i, v){
				var oImportCss = window.document.createElement("link");
				oImportCss.rel = "stylesheet";
				oImportCss.type = "text/css";
				oImportCss.href = ResultGrid.local+v;
				var oLastJs = document.scripts[document.scripts.length - 1];
				oLastJs.insertAdjacentElement('afterEnd', oImportCss);
			});		
		}
		if(!ResultGrid._isNull(valueCfg["config_script"])){
			var obj = $.parseJSON(valueCfg["config_script"]);
			jqObj = $.extend(true, jqObj, obj);
		}
	}
};

$.fn.ResultGrid = function(options){	
	var GET_DATA_URL = "result/";
	var $grid = $(this);
	var resultGrid_default = {
		mtype : 'post',
		loadui : "block"
	};
	var getData = function(option){
		var setting = {
			url : "",
			data : {},
			callback : ""
		};	
		$.extend(setting,option);
		var data = {};
		jQuery.ajax({
			url: setting.url,
			type:"post",
			async:false,
			data:setting.data,
			dataType : "json",
			success: function(returnStr){
				data = returnStr;
				if(setting.callback)setting.callback(data);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown){
				alert("error getData ="+textStatus+"_"+errorThrown);
			}
		});
		return data;
	};
	if(options.result){
		var colModel = [];
		var colData = getData({url:GET_DATA_URL+"colModel/"+options.result});		
		if(filterData(colData) != null){
			colModel = colData.data.list;
		}
		var postData = options.resultParam || {};
		var jqObj = $.extend(true, {}, resultGrid_default, options, {"url":GET_DATA_URL+"list/"+options.result,"postData":postData});
		jqObj["colModel"] = $.merge(colModel || [], options.colModel || []);
		if(jqObj["colModel"].length == 0){
			colData = getData({url:GET_DATA_URL+"dataColModel/"+options.result});		
			if(filterData(colData) != null){
				colModel = colData.data.list;
				jqObj["colModel"] = colModel;
			}
		}
		var valueCfg = getData({url:GET_DATA_URL+"valueCfg/"+options.result});				
		ResultGrid.setValueCfg(valueCfg, jqObj, $grid);
		$grid.jqGrid(jqObj);
	}else{
		alert("miss result");
	}
};
