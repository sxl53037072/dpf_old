$.fn.ResultGrid = function(options){
	var GET_DATA_URL = "result/";
	var jqGrid = $("#"+this.id);
	var getData = function(option){
		var setting = {
			url : "",
			callback : ""
		};	
		$.extend(setting,option);
		var data = {};
		jQuery.ajax({
			url: setting.url,
			type:"post",
			async:false,
			dataType : "json",
			success: function(returnStr){
				data = returnStr;
				if(setting.callback)setting.callback(data);
			}
		});
		return data;
	};
	if(options.result){
		var colModel = [];
		var colData = getData({url:GET_DATA_URL+"colModel/"+options.result});		
		if(colData && colData.length > 0){
			colModel = colData;
		}
		var jqData = getData({url:GET_DATA_URL+"list/"+options.result});	
		alert(jqData);
//		jqGrid.jqGrid({
//			hidegrid:true,
//			multiselect:false,
//			mtype:'post',
//			loadui:"block",
//		    url:GET_DATA_URL+"list/"+options.result,
//			remoteSort: false,
//			//shrinkToFit:false,
//            colModel:colModel	 
//    	});
	}else{
		alert("miss result");
	}
};