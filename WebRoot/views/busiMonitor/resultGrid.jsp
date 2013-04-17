<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"> 
<html>
	<head>   
	   <title>查询</title>
	   <%@ include file="../../include/include.jsp" %>
	   <script>
	   		
	   
		   jQuery(function(){	
			   jQuery('#productSellListGrid').ResultGrid({
				   result : "1"//,
				   //resultParam : {"STATUS_FLAG": "3"}
			   });
			   
			   
			   
			   
			   
			   
			   
			   
			   $("#refGrid").on("click", function(){
				   jQuery('#productSellListGrid').setGridParam({"page":1});
				   jQuery('#productSellListGrid').setGridParam({"postData":{"status_flag": "1"}});
				   jQuery('#productSellListGrid').trigger("reloadGrid");	
			   })
			   
			   
			   /* jQuery('#productSellListGrid').ResultGrid({
// 					multiselect:false,
					result : "1",
					resultParam : {"status_flag": "3"},
					checkboxSingle:true,
					mtype:'post',
		            colModel:[
							{name:'PID',index:'PID', hidden:true, key:true},  
							{label: '产品代码', name:'PRODUCT_CODE',index:'PRODUCT_CODE', width:80, align:'left', sortable:false},
							{label: '产品名称', name:'PRODUCT_NAME',index:'PRODUCT_NAME', width:100, align:'left', sortable:false},  
							{label: '产品类型', name:'PRODUCT_TYPE_COVER',index:'PRODUCT_TYPE', width:80, align:'right', sortable:false},							
							{label: '推荐栏目', name:'PROFESSION',index:'PROFESSION', width:180, align:'left', sortable:false, formatter:function(cellvalue, options, rowObject){
								var data="";
								if(rowObject.HOTSELL_FLAG == 1){
									data += '<input type="checkbox" checked=true disabled=true/><span >热销产品</span>'
								}else{
									data += '<input type="checkbox" disabled=true/><span >热销产品</span>'
								}
								data += "&nbsp;&nbsp;&nbsp;&nbsp;";
								if(rowObject.NEWSELL_FLAG == 1){
									data += '<input type="checkbox" checked=true disabled=true/><span >新发产品</span>'
								}else{
									data += '<input type="checkbox" disabled=true/><span >新发产品</span>'
								}
								data += "&nbsp;&nbsp;&nbsp;&nbsp;";
								if(rowObject.RECOMMEND_FLAG == 1){
									data += '<input type="checkbox" checked=true disabled=true/><span >推荐标志</span>'
								}else{
									data += '<input type="checkbox" disabled=true/><span >推荐标志</span>'
								}
								return data;
							}},
							{label: '状态', name:'STATUS_FLAG_NAME',index:'STATUS_FLAG_NAME', width:80, align:'right', sortable:false, formatter:function(cellvalue, options, rowObject){
								switch(rowObject.STATUS_FLAG+""){
									case "0":
										return "发布审核中";
									case "1":
										return "已发布";	
									case "2":
										return "已失效";	
									case "3":
										return "草稿";	
									case "4":
										return "失效审核中";		
								}
							}},
							{label: '推荐理由', name:'RECOMMEND_REASON',index:'RECOMMEND_REASON', width:300, align:'left', sortable:false},
							{name:'STATUS_FLAG',index:'STATUS_FLAG', hidden:true},
							{name:'PRODUCT_TYPE',index:'PRODUCT_TYPE', hidden:true},
							{name:'NAV_LASTEST',index:'NAV_LASTEST', hidden:true},
							{name:'RRINTHREEMONTH',index:'RRINTHREEMONTH', hidden:true},
							{name:'NAV_DATE',index:'NAV_DATE', hidden:true},
							{name:'INTRODUCION',index:'INTRODUCION', hidden:true},
							{name:'INTRODUCION_MOBILE',index:'INTRODUCION_MOBILE', hidden:true},
							{name:'HOTSELL_FLAG',index:'HOTSELL_FLAG', hidden:true},
							{name:'NEWSELL_FLAG',index:'NEWSELL_FLAG', hidden:true},
							{name:'RECOMMEND_FLAG',index:'RECOMMEND_FLAG', hidden:true},
							{name:'IMGID',index:'IMGID', hidden:true},
							{name:'CREATE_TIME',index:'CREATE_TIME', hidden:true}
		    	    ],
		    	    pager:'#productSellListGrid_pager',
		    	   // mytoolbar: productSellListGrid_toolbar,
		    	    ondblClickRow: function(rowid,iRow,iCol,e){
		    	    	
		    	    }
		    	    
				});	 */		
			   //productSell.init();			   
		   });
		 
			   
		   
	   </script>
	<style>
		
		
				
		
		
	</style>
	</head>	
	<body >
	<div id="titleBarDiv" class="pageTitle" style="left: 0px; top: 0px;">
		<h2><input type="button" id="refGrid" value="ref" /></h2>
	</div>
	<div id="toolBarDiv" class="pageTool" style="left: 0px; top: 26px;"></div>	
	
	<!-- <div id="productSellListGrid_search" class="grid_search">
		<label id="">产品名称</label>
		<input type="text" />
	</div> -->
	
	<div class="group">
		<table id="productSellListGrid"></table>
		<div id="productSellListGrid_pager"></div>
	</div>
<!-- 	<input id='aaa' type='text' class='Wdate' onfocus="WdatePicker({skin:'blueFresh',dateFmt:'yyyy-MM-dd'});">  -->
	<input type="text" onfocus='WdatePicker({dateFmt:"yyyy-MM-dd"});' class="Wdate" id="CREATE_TIME111">
	
	<div id="uploadDialog" style="display:none;">
		<form id="uploadForm" enctype="multipart/form-data" target="uploadIframe" method="post" action="../hotProduct/productSellAction.do?act=upload">
			<table align="center" width="350" border="0" cellpadding="0" cellspacing="0">
			        <tr>
			          <td>&nbsp;</td>
			        </tr>
			        <tr>
			          <td width="91%"><b>请选择上传的文件：</b><br>			           
			            <input id="file1" name="file1" type="file" size="35">
			            <br>
			            <div style="color:#ff0000">请上传的后缀为jpg,gif,png的图片[120*120]</div>
			          </td>
			        </tr>
			</table>
		</form>
	</div>
	<iframe name="uploadIframe" style="display:none;"></iframe>
		
	</body>
</html>
