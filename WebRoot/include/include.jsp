<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String rootPath = request.getContextPath();
%>
<script type="text/javascript" src="<%=rootPath%>/js/full/jquery-1.7.2.js"></script>
<script type="text/javascript" src="<%=rootPath%>/js/full/jqGrid/js/jquery.jqGrid.src.js"></script>
<script type="text/javascript" src="<%=rootPath%>/js/full/jqGrid/js/i18n/grid.locale-cn.js"></script>
<%-- <script type="text/javascript" src="<%=rootPath%>/js/full/jquery-ui/jquery.ui.core.js"></script> --%>
<%-- <script type="text/javascript" src="<%=rootPath%>/js/full/jquery-ui/jquery.ui.widget.js"></script> --%>
<%-- <script type="text/javascript" src="<%=rootPath%>/js/full/jquery-ui/jquery.ui.mouse.js"></script> --%>
<%-- <script type="text/javascript" src="<%=rootPath%>/js/full/jquery-ui/jquery.ui.tabs.js"></script> --%>
<%-- <script type="text/javascript" src="<%=rootPath%>/js/full/jquery-ui/jquery.ui.position.js"></script> --%>
<%-- <script type="text/javascript" src="<%=rootPath%>/js/full/jquery-ui/jquery.ui.draggable.js"></script> --%>
<%-- <script type="text/javascript" src="<%=rootPath%>/js/full/jquery-ui/jquery.ui.dialog.js"></script> --%>
<%-- <script type="text/javascript" src="<%=rootPath%>/js/full/jquery-ui/jquery.ui.button.js"></script> --%>
<script type="text/javascript" src="<%=rootPath%>/js/full/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="<%=rootPath%>/js/full/jquery-layout/jquery.layout-latest.js"></script>
<script type="text/javascript" src="<%=rootPath%>/js/full/jquery.validationEngine.js"></script>
<script type="text/javascript" src="<%=rootPath%>/js/full/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="<%=rootPath%>/js/full/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=rootPath%>/js/full/busiMonitor/ResultGrid.js"></script>
<script type="text/javascript" src="<%=rootPath%>/js/full/toolbar/js/Toolbar.js"></script>
<script type="text/javascript" src="<%=rootPath%>/js/full/jquery-ztree/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="<%=rootPath%>/js/full/global.js"></script>

<link rel="stylesheet" type="text/css"	href="<%=rootPath%>/js/full/jqGrid/themes/redmond/jquery-ui-1.8.2.custom.css" />
<%-- <link rel="stylesheet" type="text/css"	href="<%=rootPath%>/js/full/jquery-ui/themes/base/jquery-ui.css" /> --%>
<link rel="stylesheet" type="text/css"	href="<%=rootPath%>/js/full/jqGrid/themes/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/themes/default/validationEngine.jquery.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/js/full/toolbar/css/core.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/js/full/toolbar/css/Toolbar.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/js/full/jquery-layout/jquery.layout.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/js/full/jquery-ztree/jquery.ztree.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/js/full/busiMonitor/ResultGrid.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/css/global.css" />
<script>
	//自定义查询根路径
	ResultGrid.local = '<%=rootPath%>';
</script>