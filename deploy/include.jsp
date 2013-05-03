<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String rootPath = request.getContextPath();
String baseJsPath = "full";
%>
<script type="text/javascript" src="<%=rootPath%>/js/all.min.js"></script>
<link rel="stylesheet" type="text/css"	href="<%=rootPath%>/js/<%=baseJsPath %>/jqGrid/themes/redmond/jquery-ui-1.8.2.custom.css" />
<%-- <link rel="stylesheet" type="text/css"	href="<%=rootPath%>/js/<%=baseJsPath %>/jquery-ui/themes/base/jquery-ui.css" /> --%>
<link rel="stylesheet" type="text/css"	href="<%=rootPath%>/js/<%=baseJsPath %>/jqGrid/themes/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/themes/default/validationEngine.jquery.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/js/<%=baseJsPath %>/toolbar/css/core.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/js/<%=baseJsPath %>/toolbar/css/Toolbar.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/js/<%=baseJsPath %>/jquery-layout/jquery.layout.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/js/<%=baseJsPath %>/jquery-ztree/jquery.ztree.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/js/<%=baseJsPath %>/busiMonitor/ResultGrid.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/css/global.css" />
<script>
	//自定义查询根路径
	ResultGrid.local = '<%=rootPath%>';
</script>