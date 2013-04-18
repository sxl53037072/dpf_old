<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"> 
<html>
	<head>   
	   <title>查询</title>
	   <%@ include file="../../include/include.jsp" %>
	   <script>	   		
		   jQuery(function(){	
			   var param = getUrlParam();
			   var result = param.id;
			   delete param.id;
			   
			   jQuery('#productSellListGrid').ResultGrid({				   
				   result : result,
				   resultParam : param
			   });
		   });
	   </script>
	<style>
	</style>
	</head>	
	<body >
	<img src="1.png" style="width:1420px;height:90px" alt="" />
	<div class="group">
		<table id="productSellListGrid"></table>
		<div id="productSellListGrid_pager"></div>
	</div>
	</body>
</html>
