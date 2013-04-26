<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"> 
<html>
	<head>   
	   <title>查询</title>
	   <%@ include file="../../include/include.jsp" %>
	   <script>	   		
		   jQuery(function(){	
			   $("body").layout({ applyDemoStyles: false });
			   
			   //树
			   var treeNode = [
			     { id:1, pId:0, name:"融资融券客户资产负债查询", open:true},
			     { id:4, pId:1, name:"信用额度查询"},
			     { id:5, pId:1, name:"信用交易委托"},
			     { id:6, pId:1, name:"信用划转查询"},
			   ];
			   var setting = {
					data: {
						simpleData: {
							enable: true
						}
					},
					callback: {
						onClick: function(event, treeId, treeNode, clickFlag){
							$("#gridDiv").html('	<div id="titleBarDiv" class="pageTitle" style="left: 0px; top: 0px;">'+
									'<h2><em>'+treeNode.name+'</em></h2>	'+
									'</div>'+
									'<div><table id="productSellListGrid"></table> <div id="productSellListGrid_pager"></div></div>');	
						    jQuery('#productSellListGrid').ResultGrid({				   
							   result : treeNode.id
						    });
						}
					}
				};
			   
			   var treeObj = $.fn.zTree.init($("#treeDemo"), setting, treeNode);
			   var node = treeObj.getNodeByParam("id", 4, null);
			   treeObj.selectNode(node);

		   });
	   </script>
	<style>
	div.pageTitle {
	    background: url("images/bg.gif") repeat-x scroll 0 0 transparent;
	    border-bottom: 1px solid #B0B6C2;
	    clear: both;
	    height: 25px;
	    position: relative;
	    white-space: nowrap;
	}
	
	div.pageTitle h2 {
	    background: url("images/bg.gif") no-repeat scroll right -52px transparent;
	    float: left;
	    padding: 0 20px 0 10px;
	    margin: 0;
	    white-space: nowrap;
	}
	
	div.pageTitle h2 em {
	    background: url("images/title.gif") no-repeat scroll 0 center transparent;
	    display: block;
	    font: 12px/25px "宋体";
	    text-indent: 20px;
	    white-space: nowrap;
	}
	</style>
	</head>	
	<body >
	<div class="ui-layout-center">
		<div class="group" id="gridDiv">
			<table id="productSellListGrid"></table>
			<div id="productSellListGrid_pager"></div>
		</div>
	</div>
	<div class="ui-layout-north"><img src="1.png" style="width:1434px;height:90px" alt="" /></div>
<!-- 	<div class="ui-layout-south">South</div> -->
<!-- 	<div class="ui-layout-east">East</div> -->
	<div class="ui-layout-west">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
	
	</body>
</html>
