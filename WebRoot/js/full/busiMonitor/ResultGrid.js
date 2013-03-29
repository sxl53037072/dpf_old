$.fn.ResultGrid = (function(options){
	var GET_DATA_URL = "busiMonitor/";
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
		var data = getData({url:GET_DATA_URL+"/colModel/"+options.result});
		
	}else{
		alert("miss result");
	}
})();