<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String rootPath = request.getContextPath();
String baseJsPath = "full";
%>
<script type="text/javascript" src="<%=rootPath%>/js/<%=baseJsPath %>/jquery-1.7.2.js"></script>
<script type="text/javascript" src="<%=rootPath%>/js/<%=baseJsPath %>/jqGrid/js/jquery.jqGrid.src.js"></script>
<script type="text/javascript" src="<%=rootPath%>/js/<%=baseJsPath %>/jqGrid/js/i18n/grid.locale-cn.js"></script>
<%-- <script type="text/javascript" src="<%=rootPath%>/js/<%=baseJsPath %>/jquery-ui/jquery.ui.core.js"></script> --%>
<%-- <script type="text/javascript" src="<%=rootPath%>/js/<%=baseJsPath %>/jquery-ui/jquery.ui.widget.js"></script> --%>
<%-- <script type="text/javascript" src="<%=rootPath%>/js/<%=baseJsPath %>/jquery-ui/jquery.ui.mouse.js"></script> --%>
<%-- <script type="text/javascript" src="<%=rootPath%>/js/<%=baseJsPath %>/jquery-ui/jquery.ui.tabs.js"></script> --%>
<%-- <script type="text/javascript" src="<%=rootPath%>/js/<%=baseJsPath %>/jquery-ui/jquery.ui.position.js"></script> --%>
<%-- <script type="text/javascript" src="<%=rootPath%>/js/<%=baseJsPath %>/jquery-ui/jquery.ui.draggable.js"></script> --%>
<%-- <script type="text/javascript" src="<%=rootPath%>/js/<%=baseJsPath %>/jquery-ui/jquery.ui.dialog.js"></script> --%>
<%-- <script type="text/javascript" src="<%=rootPath%>/js/<%=baseJsPath %>/jquery-ui/jquery.ui.button.js"></script> --%>
<script type="text/javascript" src="<%=rootPath%>/js/<%=baseJsPath %>/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="<%=rootPath%>/js/<%=baseJsPath %>/jquery-layout/jquery.layout-latest.js"></script>
<script type="text/javascript" src="<%=rootPath%>/js/<%=baseJsPath %>/jquery.validationEngine.js"></script>
<script type="text/javascript" src="<%=rootPath%>/js/<%=baseJsPath %>/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath%>/js/<%=baseJsPath %>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=rootPath%>/js/<%=baseJsPath %>/busiMonitor/ResultGrid.js"></script>
<script type="text/javascript" src="<%=rootPath%>/js/<%=baseJsPath %>/toolbar/js/Toolbar.js"></script>
<script type="text/javascript" src="<%=rootPath%>/js/<%=baseJsPath %>/jquery-ztree/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="<%=rootPath%>/js/<%=baseJsPath %>/jquery-combogrid/plugin/jquery.ui.combogrid-1.6.2.js"></script>
<script type="text/javascript" src="<%=rootPath%>/js/<%=baseJsPath %>/global.js"></script>

<link rel="stylesheet" type="text/css" href="<%=rootPath%>/js/<%=baseJsPath %>/jqGrid/themes/redmond/jquery-ui-1.8.2.custom.css" />
<%-- <link rel="stylesheet" type="text/css"	href="<%=rootPath%>/js/<%=baseJsPath %>/jquery-ui/themes/base/jquery-ui.css" /> --%>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/js/<%=baseJsPath %>/jqGrid/themes/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/themes/default/validationEngine.jquery.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/js/<%=baseJsPath %>/toolbar/css/core.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/js/<%=baseJsPath %>/toolbar/css/Toolbar.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/js/<%=baseJsPath %>/jquery-layout/jquery.layout.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/js/<%=baseJsPath %>/jquery-ztree/jquery.ztree.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/js/<%=baseJsPath %>/jquery-combogrid/jquery.ui.combogrid.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/js/<%=baseJsPath %>/busiMonitor/ResultGrid.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/css/global.css" />
<script>
	//自定义查询根路径
	ResultGrid.local = '<%=rootPath%>';
</script>