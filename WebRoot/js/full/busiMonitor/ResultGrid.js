var ResultGrid = {
	local : "",
	setValueCfg : function(valueCfg, jqObj, $grid){
		if(valueCfg["TITLE"] != ""){
			jqObj["caption"] = valueCfg["TITLE"];
		}
		if(valueCfg["IS_PAGE"] == "0BF"){
			jqObj["pager"] = "";
		}else{
			jqObj["pager"] = jqObj["pager"]?jqObj["pager"]:$grid.attr("id")+"_pager";
		}
		if(valueCfg["PAGE_SIZE"] != ""){
			jqObj["rowNum"] = valueCfg["PAGE_SIZE"];
		}
		if(valueCfg["IS_FORCEFIT"] == "0BF"){
			jqObj["shrinkToFit"] = true;
		}else{
			jqObj["shrinkToFit"] = false;
		}
		if(valueCfg["HIDDEN_COLUMNS"] != ""){
			var hideArr = valueCfg["HIDDEN_COLUMNS"].split(",");
			$.each(hideArr, function(i, v){
				$.each(jqObj["colModel"], function(ii, vv){
					if(v.toUpperCase() == vv.name.toUpperCase()){
						vv.hidden = true;
					}
				});
			});
		}	
		if(valueCfg["DBLCLICK"] != ""){					
			jqObj["ondblClickRow"] = function(rowid,iRow,iCol,e){
				eval(valueCfg["DBLCLICK"])(rowid,iRow,iCol,e);
			};
		}
		if(valueCfg["IMPORT_JS"] != ""){
			var jsArr = valueCfg["IMPORT_JS"].split(",");
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
		if(valueCfg["IMPORT_CSS"] != ""){
			var cssArr = valueCfg["IMPORT_CSS"].split(",");
			$.each(cssArr, function(i, v){
				var oImportCss = window.document.createElement("link");
				oImportCss.rel = "stylesheet";
				oImportCss.type = "text/css";
				oImportCss.href = ResultGrid.local+v;
				var oLastJs = document.scripts[document.scripts.length - 1];
				oLastJs.insertAdjacentElement('afterEnd', oImportCss);
			});		
		}
		if(valueCfg["CONFIG_SCRIPT"] != ""){
			jqObj = $.extend(true, jqObj, valueCfg["CONFIG_SCRIPT"]);
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
//$.extend($.fn.ResultGrid,$.fn.jqGrid);