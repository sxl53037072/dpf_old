function filterData(d){
	if(d && d.succeed && d.data && d.data.list.length > 0){
		return d.data.list;
	}else{
		return null;
	}
	
}