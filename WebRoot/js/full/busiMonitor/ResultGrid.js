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
				alert("error msg ="+textStatus+"_"+errorThrown);
			}
		});
		return data;
	};
	if(options.result){
		var colModel = [];
		var colData = getData({url:GET_DATA_URL+"colModel/"+options.result});		
		if(colData && colData.succeed && colData.data && colData.data.list.length > 0){
			colModel = colData.data.list;
		}
		//var jqData = getData({url:GET_DATA_URL+"list/"+options.result,data:options.resultParam});	
		var postData = options.resultParam || {};
		var jqObj = $.extend(true, {}, resultGrid_default, options, {"url":GET_DATA_URL+"list/"+options.result,"postData":postData});
		jqObj["colModel"] = $.merge(options.colModel, colModel);
		$grid.jqGrid(jqObj);
//		$grid.jqGrid({
//			hidegrid : true,
//			multiselect : false,
//			mtype : 'post',
//			loadui : "block",
//			url : GET_DATA_URL+"list/"+options.result,
//			remoteSort : false,
//			shrinkToFit:false,
//			colModel : colModel,
//			pager : options.pager
//		});
		
		//$grid.jqGrid("clearGridData");
		//$grid.jqGrid({"addJSONData":jqData});
		//$grid[0].addJSONData(jqData);
		
	}else{
		alert("miss result");
	}
};