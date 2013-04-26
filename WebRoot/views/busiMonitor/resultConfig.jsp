<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"> 
<html>
	<head>   
	   <title>查询配置</title>
	   <%@ include file="../../include/include.jsp" %>
	   <script>	
	   	   var initToolbarGrid = 0;
		   jQuery(function(){	
			   jQuery('#resultConfigGrid').jqGrid({				   
				   url : "<%=rootPath%>/busiMonitor/resultConfig/list",
				   width:'100%',
					hidegrid:true,
					multiselect:false,
					mtype:'post',
					remoteSort: false,
		            colModel:[
							{label: 'ID', name:'sqlId', width:120, align:'left', sortable:false},  
							{label: '备注', name:'remark', width:120, align:'left', sortable:false},
							{label: '类型', name:'sqlType', width:120, align:'left', sortable:false}
		    	    ],
		    	    pager:'#resultConfigGrid_pager',
		    	    subGrid: true,
					subGridRowExpanded: function(subgrid_id, row_id) {	
						var row = $("#resultConfigGrid").jqGrid("getOriRowData",row_id);
						var $detail = $('<div style="padding:2px;height:250px;background-color:#F5F5F5" alreadyLoad="N" id="ddv-' + row_id + '">');
						$('#' + subgrid_id).append($detail);
					   	var $ul1 = $('<ul style="list-style-type:none">');
					   	var text = row.sqlText;
					   	text = text.replace("<","&lt;");
						$ul1.append($("<li class='datagridDetailLi'>SQL_TEXT：</li>").append($("<textarea readonly=true style='width:700px;height:200px'>").html(text)));
						$detail.append($ul1);
					},
					mytoolbar : [
						{
							text : "新增",
							cuscls : "add",
							handler : function(){	
								setToolbarGrid();
								$("#resultConfig_dialog").show().dialog({
									title : "新增配置",
									height : 688,
									width : 960,
									model : true,
									buttons : {										
										"确定":function(){
											var formData = jQuery('#resultConfig_form').serialize();					
											jQuery.ajax({
												url: "<%=rootPath%>/busiMonitor/resultConfig/add",
												type:"post",
												async:false,
												dataType : "json",
												data:formData,
												success: function(returnStr){
													if(returnStr!=null && returnStr.error ){
														alert('编辑失败！'); 
													}else{
														alert('编辑成功！'); 
														$("#resultConfigGrid").trigger("reloadGrid");														
													}
													$("#resultConfig_dialog").dialog( "close" );
												}
											});
										},
										"取消":function(){
											$( this ).dialog( "close" );
										}
									}
								});
							}
						},
						{
							text : "修改",
							cuscls : "edit",
							handler : function(){
								var gr = jQuery("#resultConfigGrid").jqGrid('getGridParam','selrow');
								if( gr != null ) {
									var rowData = $("#resultConfigGrid").jqGrid("getOriRowData",gr);
									for(var k in rowData){
										try{
											$("#add_"+k+"_id").val(rowData[k]);
										}catch(e){
											
											$("#add_"+k+"_id").html(rowData[k]);
										}
									}
									setToolbarGrid(rowData.toolbarMenuId);
									$("#resultConfig_dialog").show().dialog({
										title : "修改配置",
										height : 388,
										width : 960,
										model : true,
										buttons : {										
											"确定":function(){
												var formData = jQuery('#resultConfig_form').serialize();
												jQuery.ajax({
													url: "<%=rootPath%>/busiMonitor/resultConfig/edit",
													type:"post",
													async:false,
													dataType : "json",
													data:formData,
													success: function(returnStr){
														if(returnStr!=null && returnStr.error ){
															alert('编辑失败！'); 
														}else{
															alert('编辑成功！'); 
															$("#resultConfigGrid").trigger("reloadGrid");														
														}
														$("#resultConfig_dialog").dialog( "close" );
													}
												});
											},
											"取消":function(){
												$( this ).dialog( "close" );
											}
										}
									});
									
								}
							}
						}
					]
			   });
		   });
		   function setToolbarGrid(groupId){
			   if(initToolbarGrid == 1){
				   jQuery('#toolbarGrid').setGridParam({"page":1});
				   jQuery('#toolbarGrid').setGridParam({"postData":""});
				   jQuery('#toolbarGrid').setGridParam({"postData":{"groupId":groupId}}).trigger("reloadGrid");
			   }else{
				   initToolbarGrid = 1;
				   var url = 
				   jQuery('#toolbarGrid').jqGrid({			
//	 				   caption : "工具栏配置",
					   url : "<%=rootPath%>/busiMonitor/resultConfig/toolbarList",
					   postData : {"groupId": groupId || "-999999"},
					   hidegrid:true,
					   width:710,
					   autowidth:true,
					   multiselect:false,
					   mtype:'post',
					   remoteSort: false,
					   pager:'#toolbarGrid_pager',
					   colModel:[
								{label: 'groupId', name:'groupId', width:120, align:'left', sortable:false},  
								{label: '按钮名称', name:'itemLabel', width:120, align:'left', sortable:false},
								{label: '按钮事件名', name:'event', width:120, align:'left', sortable:false},
								{label: '图标', name:'ico', width:120, align:'left', sortable:false},
								{label: '是否分割线', name:'isLine', width:120, align:'left', sortable:false},
								{label: '排序标识', name:'sortId', width:120, align:'left', sortable:false},
								{label: '备注', name:'remark', width:120, align:'left', sortable:false},
								{label: '导入JS路径', name:'importJs', width:120, align:'left', sortable:false},
								{label: '菜单名称', name:'menuNameCn', width:120, align:'left', sortable:false}
			    	   ],
					   mytoolbar : [
					   		{
					   			text : "新增",
					   			cuscls : "add",
								handler : function(){	
									
								}
					   		},
					   		{
					   			text : "修改",
					   			cuscls : "edit",
								handler : function(){	
									
								}
					   		}
				       ]
				   });
			   }			   
		   }
	   </script>
	<style>
	</style>
	</head>	
	<body >
	<div class="group">
		<table id="resultConfigGrid"></table>
		<div id="resultConfigGrid_pager"></div>
	</div>
	
	
	<div id="resultConfig_dialog" style="display:none">
		<form id="resultConfig_form" method="post" class="adminform" action="" style="padding-top:10px;">	   	
		    	<label><span class="required">*</span>id:</label>
		        <input  name="sqlId" id="add_sqlId_id" type="text">	
		        <label><span class="required">*</span>类型:</label>
		        <select id="add_sqlType_id" name="sqlType">
		        	<option value="SQL" SELECTED>SQL</option>
		        	<option value="PROC">PROC</option>
		        </select>
		        <div></div>	     
		        <label><span class="required">*</span>注释:</label>
		        <input  name="remark" id="add_remark_id" type="text">	
		        <div></div>
		        <label>SQL文本:</label>
		        <textarea style="height:200px;width:700px;" name="sqlText" id="add_sqlText_id" ></textarea>		        
		</form>
		<div class="adminform" >
			<label>工具栏配置:</label>
			<div class="group">
				<table id="toolbarGrid"></table>
				<div id="toolbarGrid_pager"></div>
			</div>
			<div class="group">
				<table id="searchGrid"></table>
				<div id="searchGrid_pager"></div>
			</div>
		</div>
	</div>
	</body>
</html>
