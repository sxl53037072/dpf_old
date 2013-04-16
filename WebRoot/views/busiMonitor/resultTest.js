function editProduct(rowid,iRow,iCol,e){
	alert(rowid+"_"+iRow+"_"+iCol+"_"+e);
}
function addRow(){
	var ids = $("#productSellListGrid").getGridParam('selarrrow');
	alert(ids.join(","));
}