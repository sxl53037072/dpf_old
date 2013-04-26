function filterData(d){
	if(d && d.succeed && d.data && d.data.list.length > 0){
		return d.data.list;
	}else{
		return null;
	}
	
}
function filterHtml(data){
	data = data.replace("<", "&lt;");
	data = data.replace(">", "&gt;");
	data = data.replace("&", "&amp;");
	data = data.replace('"', "&quot;");
	return data;
}