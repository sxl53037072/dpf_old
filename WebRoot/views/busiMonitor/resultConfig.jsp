<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"> 
<html>
	<head>   
	   <title>查询配置</title>
	   <%@ include file="../../include/include.jsp" %>
	   <script type="" src="resultConfig.js"></script>
	   <script>	
	   	   var initSearchGrid = 0;	   	
	   	   function editButton(type, notClose){
			   var flag = jQuery('#resultConfig_form').validationEngine('validate');
			   var formData = {};
				if(flag){
					formData = formOperator.costructFormData("resultConfig_form");
					formData["toolbar_menu_id"] = $("#toolbar").attr("v") || "";
					jQuery.ajax({
						url: "<%=rootPath%>/busiMonitor/resultConfig/"+type,
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
							if(!notClose)$("#resultConfig_dialog").dialog( "close" );
						}
					});
				}
				return formData;
		   }
	   	   function addConfig(){
		   		$("#resultConfig_form").form("clear");
				setSearchGrid();
				setToolbar();
				$("#resultConfig_dialog").show().dialog({
					title : "新增配置",
					height : 688,
					width : 960,
					modal : true,
					buttons : {										
						"确定":function(){
							editButton("add");
						},
						"取消":function(){
							$( this ).dialog( "close" );
						}
					}
				});
	   	   }
	   	   function editConfig(rowData){
		   		$("#resultConfig_form").form("clear");
				var gr = jQuery("#resultConfigGrid").jqGrid('getGridParam','selrow');
				if( gr != null || rowData) {
					if(!rowData){
						rowData = $("#resultConfigGrid").jqGrid("getOriRowData",gr);
					}
					for(var k in rowData){
						try{
							$("#add_"+k+"_id").val(rowData[k]);
						}catch(e){
							
							$("#add_"+k+"_id").html(rowData[k]);
						}
					}
					setSearchGrid(rowData.sqlId);
					setToolbar(rowData.toolbarMenuId,rowData.toolbarMenuName);
					$("#resultConfig_dialog").show().dialog({
						title : "修改配置",
						height : 688,
						width : 960,
						model : true,
						buttons : {										
							"确定":function(){
								editButton("edit");
							},
							"取消":function(){
								$( this ).dialog( "close" );
							}
						}
					});
					
					
				}
	   	   }
		   jQuery(function(){	
			   $("#add_COMP_ID_id").selectDs("SELECT COMP_LABEL NAME,COMP_ID VALUE FROM COMPONENT ORDER BY COMP_ID");
			   $("#resultConfig_form").validationEngine();
			   jQuery('#resultConfigGrid').jqGrid({				   
				   url : "<%=rootPath%>/busiMonitor/resultConfig/list",
				   width:'100%',
					hidegrid:true,
					multiselect:true,
					mtype:'post',
					remoteSort: false,
		            colModel:[
							{label: 'ID', name:'sqlId', width:120, align:'left', sortable:false, key:true},  
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
								addConfig();
							}
						},
						{
							text : "修改",
							cuscls : "edit",
							handler : function(){
								editConfig();
							}
						},
						{
							text : "删除",
							cuscls : "delete",
							handler : function(){
								formOperator.delRows("resultConfigGrid","<%=rootPath%>/busiMonitor/resultConfig/del",function(){
									$("#resultConfigGrid").trigger("reloadGrid");
								});
							}
						}
					],
					ondblClickRow: function(rowid,iRow,iCol,e){
		    	    	var rowData = jQuery('#resultConfigGrid').jqGrid("getOriRowData",rowid);
		    	    	editConfig(rowData);
		    	    },
		    	    onCellSelect:function(rowid,iCol,cellcontent,e){
		    	    	if(iCol != 1){
		    	    		$("#resultConfigGrid").setSelection(rowid);
		    	    	}
		    		}
			   });
		   });
		   function setToolbar(groupId,toolbarMenuName){
			   
			   if($( "#toolbar" ).hasClass("ui-autocomplete-input")){
					$( "#toolbar" ).val(toolbarMenuName || "").attr("v", groupId || "").attr("n", toolbarMenuName || "");
					$( "#toolbar" ).combogrid("clearData");
					$( "#toolbar" ).combogrid("setValue", groupId).combogrid("setText", toolbarMenuName);
				}else{
					$( "#toolbar" ).combogrid({
						url : "<%=rootPath%>/busiMonitor/resultConfig/toolbarList",
						width:"450",
						async:false,
						//autoChoose:true,
						//设置是否不可用默认可用
						disabled:false,
						//参数设置
				        setParams:function(q) { 
					      	  var args = {
					      			searchName:q					      			
					          };
					    	  return args;
				        },	
				         //width:按百分比设置宽度	
						colModel: [{'columnName':'SYS_FUNC_MENU_GROUP_ID',width:'20', 'label':'菜单标识', align:"left", key:true}, 
						           {'columnName':'REMARK',width:'50', 'label':'菜单备注', align:"left"}
						],
						select: function( event, ui ) {
							if(!ui.item)return false;
							$( "#toolbar" ).val( ui.item.REMARK);
							$( "#toolbar" ).attr("v", ui.item.SYS_FUNC_MENU_GROUP_ID).attr("n", ui.item.REMARK);
							return false;
						}
					});				
					$( "#toolbar" ).val(toolbarMenuName || "").attr("v", groupId || "").attr("n", toolbarMenuName || "");
					//$("#toolbar").combogrid("initLoad");	
					$( "#toolbar" ).combogrid("setValue", groupId).combogrid("setText", toolbarMenuName);
				}
		   }
		   function beforeSearch(sqlId){
		   		if(!sqlId){
		   			if(confirm("需要先保存表单，是否进行保存操作?")){
		   				var formData = editButton("add", 1);
		   				return formData["sqlId"];
		   			}
		   		}else{
		   			return sqlId;
		   		}
		   		return "";
		   }
		   function setSearchGrid(sqlId){			  
			   if(initSearchGrid == 1){
				   jQuery('#searchGrid').setGridParam({"page":1});
				   jQuery('#searchGrid').setGridParam({"postData":""});
				   jQuery('#searchGrid').setGridParam({"postData":{"sqlId":sqlId}}).trigger("reloadGrid");
			   }else{
				   initSearchGrid = 1;
				   jQuery('#searchGrid').attr("sqlId",sqlId);
				   jQuery('#searchGrid').jqGrid({			
					   url : "<%=rootPath%>/busiMonitor/resultConfig/searchList",
					   postData : {"sqlId": sqlId || "-999999"},
					   hidegrid:true,
					   width:710,
					   shrinkToFit:true,
					   multiselect:false,
					   mtype:'post',
					   remoteSort: false,
					   autowidth:true,
					   pager:'#searchGrid_pager',
					   multiselect:true,
					   colModel:[								
								{label: '名称', name:'PARAM_LABEL', width:120, align:'left', sortable:false},
								{label: '标识', name:'PARAM_NAME', width:120, align:'left', sortable:false},
								{label: '类型', name:'COMP_NAME', width:120, align:'left', sortable:false},
								{label: '排序', name:'SORT_ID', width:120, align:'left', sortable:false},
								{label: '数据源', name:'COMP_DS', width:120, align:'left', sortable:false},
								{label: '配置', name:'COMP_CFG', width:120, align:'left', sortable:false},
								{name:'SQL_PARAM_ID',hidden:true,key:true, sortable:false}
			    	   ],
					   mytoolbar : [
					   		{
					   			text : "新增",
					   			cuscls : "add",
								handler : function(){	
									var sqlId = jQuery('#searchGrid').attr("sqlId");
									var sqlId = beforeSearch(sqlId);
									if(sqlId){
										addSearchDialog(sqlId);
									}
								}
					   		},
					   		{
					   			text : "修改",
					   			cuscls : "edit",
								handler : function(){	
									var ids = jQuery('#searchGrid').getGridParam('selarrrow');		
									if(ids.length==0){
										alert('请选择一条数据!');
									}
									else if(ids.length>1){
										alert('只能选择一条数据!');
									}else{
										var rows = jQuery('#searchGrid').jqGrid('getOriRowData',ids);					
										editSearchDialog(rows);
									}
									
								}
					   		},
					   		{
					   			text : "删除",
					   			cuscls : "delete",
								handler : function(){	
									formOperator.delRows("searchGrid", "<%=rootPath%>/busiMonitor/resultConfig/searchList/del", function(){
										$("#searchGrid").trigger("reloadGrid");				
									});
								}
					   		},
				       ],
				       ondblClickRow: function(rowid,iRow,iCol,e){
			    	    	var rowData = jQuery('#searchGrid').jqGrid("getOriRowData",rowid);
			    	    	editSearchDialog(rowData);
			    	   },
			    	   onCellSelect:function(rowid,iCol,cellcontent,e){
			    	    	if(iCol != 1){
			    	    		$("#searchGrid").setSelection(rowid);
			    	    	}
			    		}
				   });
				  jQuery('#searchGrid').setGridWidth("710");
			   }	
		   }
		   function addSearchDialog(sqlId){
			   $("#searchGrid_form").form("clear");			   
			   $("#searchGrid_dialog").show().dialog({
				    title : "新增搜索配置",
					height : 500,
					width : 780,
					modal : true,
					buttons : {										
						"确定":function(){
							var formData = formOperator.costructFormData("searchGrid_form");
							formData["sqlId"] = sqlId;
							jQuery.ajax({
								url: "<%=rootPath%>/busiMonitor/resultConfig/searchList/add",
								type:"post",
								async:false,
								dataType : "json",
								data:formData,
								success: function(returnStr){
									if(returnStr!=null && returnStr.error ){
										alert('新增失败！'); 
									}else{
										alert('新增成功！'); 
										$("#searchGrid").trigger("reloadGrid");														
									}
									$("#searchGrid_dialog").dialog( "close" );
								}
							});
						},
						"取消":function(){
							$( this ).dialog( "close" );
						}
					}
			   });
		   }
		   function editSearchDialog(data){
			   $("#searchGrid_form").form("clear");
			   $("#searchGrid_form").form("load", data);
			   $("#searchGrid_dialog").show().dialog({
				    title : "编辑搜索配置",
					height : 500,
					width : 780,
					model : true,
					buttons : {										
						"确定":function(){
							var formData = formOperator.costructFormData("searchGrid_form");
							formData["sqlId"] = data["SQL_ID"];
							jQuery.ajax({
								url: "<%=rootPath%>/busiMonitor/resultConfig/searchList/edit",
								type:"post",
								async:false,
								dataType : "json",
								data:formData,
								success: function(returnStr){
									if(returnStr!=null && returnStr.error ){
										alert('编辑失败！'); 
									}else{
										alert('编辑成功！'); 
										$("#searchGrid").trigger("reloadGrid");														
									}
									$("#searchGrid_dialog").dialog( "close" );
								}
							});
						},
						"取消":function(){
							$( this ).dialog( "close" );
						}
					}
			   });
		   }
		   
		   function initToolbar(){
			   jQuery('#toolbarGrid').jqGrid({				   
				    url : "<%=rootPath%>/busiMonitor/resultConfig/toolbarDetail",
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
		    	    pager:'#toolbarGrid_pager'
			   });
		   }
		   function toToolbarEdit(){
			   $("#toolbar_dialog").show.dialog({
				   
			   });
		   }
	   </script>
	<style>
	</style>
	</head>	
	<body style="overflow-x:hidden;">
	<div class="group">
		<table id="resultConfigGrid"></table>
		<div id="resultConfigGrid_pager"></div>
	</div>
	
	
	<div id="resultConfig_dialog" style="display:none">
		<form id="resultConfig_form" method="post" class="adminform" action="" style="padding-top:10px;">	   	
		    	<label><span class="required">*</span>id:</label>
		        <input class="validate[required]" name="sqlId" id="add_sqlId_id" type="text" />	
		        <label><span class="required">*</span>类型:</label>
		        <select class="validate[required]"  id="add_sqlType_id" name="sqlType">
		        	<option value="SQL" selected>SQL</option>
		        	<option value="PROC">PROC</option>
		        </select>
		        <div></div>	     
		        <label><span class="required">*</span>注释:</label>
		        <input class="validate[required]" name="remark" id="add_remark_id" type="text" />	
		        <div></div>
		        <label><span class="required">*</span>SQL文本:</label>
		        <textarea class="validate[required]"  style="height:200px;width:700px;" name="sqlText" id="add_sqlText_id" ></textarea>		 
		        <label style="display: inline-block;width:140px;text-align: right;">工具栏配置:</label>
				<input type="text" id="toolbar" name="toolbar_menu_id" value="" v="" style="width:300px"/>
				<a href="javascript:;" onclick="toToolbarEdit()">配置</a>       				
		</form>
		<div style="margin-right:10px;margin-bottom:9px;margin-top:9px;">
			<label style="display:inline-block;width:140px;text-align:right;">搜索栏配置:</label>
			<div class="group" style="float:right;width:770px;">
				<table id="searchGrid"></table>
				<div id="searchGrid_pager"></div>
			</div>
		</div>
	</div>
	
	
	<div id="searchGrid_dialog" style="display:none">	
		<form id="searchGrid_form" method="post" class="adminform" action="" style="padding-top:10px;">	   
				<input type="hidden" name="SQL_PARAM_ID" id="add_SQL_PARAM_ID_id" />	
		    	<label><span class="required">*</span>名称:</label>
		        <input  name="PARAM_LABEL" id="add_PARAM_LABEL_id" type="text" />	
		        <label><span class="required">*</span>标识:</label>
		        <input  name="PARAM_NAME" id="add_PARAM_NAME_id" type="text" />	
		        <div></div>
		        <label><span class="required">*</span>类型:</label>
		        <select  name="COMP_ID" id="add_COMP_ID_id"></select>	
				<label><span class="required">*</span>排序:</label>
		        <input  name="SORT_ID" id="add_SORT_ID_id" type="text" />				        
		        <div></div>
		        <label>数据:</label>
		        <textarea  name="COMP_DS" id="add_COMP_DS_id" style="width:550px;height:130px;"></textarea>	
		        <div></div>
		        <label>配置:</label>
		        <textarea  name="COMP_CFG" id="add_COMP_CFG_id" style="width:550px;height:130px;"></textarea>		
		</form>
	</div>
	
	<div id="toolbar_dialog" style="display:none">	
		<div class="group" style="float:right;width:770px;">
			<table id="toolbarGrid"></table>
			<div id="toolbarGrid_pager"></div>
		</div>
	</div>
	
	
	</body>
</html>
