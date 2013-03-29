<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"> 
<html>
	<head>   
	   <title>查询</title>
	   <%@ include file="../../include/include.jsp" %>
	   <script>
		   jQuery(function(){			      			   
			   jQuery('#productSellListGrid').jqGrid({
// 					multiselect:false,
					checkboxSingle:true,
					mtype:'post',
					url:"product/list",
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
								switch(rowObject.STATUS_FLAG){
									case 0:
										return "发布审核中";
									case 1:
										return "已发布";	
									case 2:
										return "已失效";	
									case 3:
										return "草稿";	
									case 4:
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
		    	    
				});			
			   //productSell.init();			   
		   });
		 
			   
		   
	   </script>
	<style>
		#productSellListGrid{
			width: 90%;
		}		
		.productForm #baseInfo {
			border-collapse: collapse;
			margin: 5px 0 15px 15px;
			width: 90%;
		}		
		
		.productForm #baseInfo td {
			margin: 0px;
			padding: 5px 2px;			
		}
		.productForm #baseInfo input{
			width:90%
		}
		.productForm #baseInfo select {
 			width:96%; 
			background-color:#fff;
			border:1px solid #ccc
		}
		.productForm #baseInfo .Title{
			text-align: right;
		}
		.expand {
			cursor: pointer;
		}
		.baseInfo{
			font-family: "宋体";color:#1e5494;font-size:12px;
		}
		textarea{height:60px;width:98%;}
		.productForm #baseInfo td input[type='checkbox']{
			width:20px;vertical-align:middle;
		}	
		table td.Data{overflow:visible;}	
		.required{color:red}
		
	</style>
	</head>	
	<body >
	<div id="titleBarDiv" class="pageTitle" style="left: 0px; top: 0px;">
		<h2><em>热销产品</em></h2>
	</div>
	<div id="toolBarDiv" class="pageTool" style="left: 0px; top: 26px;"></div>	
	<div class="group">
		<table id="productSellListGrid"></table>
		<div id="productSellListGrid_pager"></div>
	</div>
	
	<div id="productSell_dialog" style="display:none;">
		<form action="" id="productSell_form" class="productForm">
			<input type="hidden" id="add_PID_id" name="PID" />
			<div class="group">
				<img class="expand" src="../customerview/images/expand.gif" />基本信息<span style="float: right;"></span>
			</div>
			<table id="baseInfo" style="font-size: 12px;" cellPadding="0" cellSpacing="0">	
				<tr>
					<td width="15%"></td>
					<td width="24%"></td>
					<td width="24%"></td>
					<td width="15%"></td>
					<td></td>
				</tr>
				<tr>
					<td rowspan="7" align="center" width="120px">
					<img style="margin:0px 5px 0px 5px;cursor:pointer;" id="imgShow"  width="120" height="120" src=""  onclick="productSell.uploadDialog()"/>
					<br />
					<div class="btn"  style="margin:5px 0px 0px 5px"  id="requestBtn" onclick="productSell.uploadDialog()">
			          <div class="act_btn">图片上传</div>			          
			          <input type="hidden" name="IMGID" id="add_IMGID_id"/>
			        </div>
					</td>						
				</tr>	
				<tr>
					<td class="Title">产品代码：</td>
					<td class="Data" ><input type="text" id="add_PRODUCT_CODE_id" name="PRODUCT_CODE" onchange="productSell.productCodeChange()"/></td>
					<td class="Title"><span class="required">*</span>产品名称：</td>
					<td class="Data" ><input type="text" id="add_PRODUCT_NAME_id" name="PRODUCT_NAME" /></td>
				</tr>		
				<tr>
					<td class="Title"><span class="required">*</span>产品类型：</td>
					<td class="Data"><select id="add_PRODUCT_TYPE_id" name="PRODUCT_TYPE" onchange="productSell.productTypeChange()"></select> 
					&nbsp;&nbsp;&nbsp;
					<input type="text" id="add_PRODUCT_TYPE_OTHER_id" style="display:none;padding:0px;mapping:0px;"/></td>
					<td colspan="2"></td>
				</tr>
				<tr>
					<td class="Title"><span class="required">*</span>推荐理由：</td>
					<td class="Data" colspan="3"><textarea id="add_RECOMMEND_REASON_id" name="RECOMMEND_REASON"></textarea></td>
				</tr>
				<tr>
					<td class="Title">推荐栏目：</td>
					<td class="Data" colspan="3">
						<table border=0>
							<tr>
								<td>
									<div style="white-space:nowrap">
										<input type="checkbox" id="add_HOTSELL_FLAG_id" checked="true" /><span >热销产品</span>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="checkbox" id="add_NEWSELL_FLAG_id"  /><span>新发产品</span>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="checkbox" id="add_RECOMMEND_FLAG_id" /><span>推荐标志</span>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="Title" id="add_NAV_LASTEST_name">最新净值(元)：</td>
					<td class="Data" ><input type="text" id="add_NAV_LASTEST_id" name="NAV_LASTEST" readOnly=true disabled=true/></td>
					<td class="Title" id="add_NAV_DATE_name">净值日期：</td>
					<td class="Data" ><input type="text" id="add_NAV_DATE_id" name="NAV_DATE"  readOnly=true disabled=true/></td>
				</tr>
				<tr>
					<td class="Title" id="add_RRINTHREEMONTH_name">今年以来增长率(%)：</td>
					<td class="Data"><input type="text" id="add_RRINTHREEMONTH_id" name="RRINTHREEMONTH" readOnly=true  disabled=true/></td>
					<td colspan="2"></td>
				</tr>		
			</table>
			
			<div class="group">
				<img class="expand" src="../customerview/images/expand.gif" />产品简介(手机文本 <span style="color:#ff0000">200字内</span>)<span style="float: right;"></span>
			</div>
			<div class="group">
				<table width="100%" cellPadding="0" cellSpacing="0">
					<tr>
						<td><textarea id="add_INTRODUCION_MOBILE_id" name="INTRODUCION_MOBILE" ></textarea></td>
					</tr>
				</table>
			</div>				
										
		</form>
			<div class="group">
				<img class="expand" src="../customerview/images/expand.gif" />产品介绍(HTML版)<span style="float: right;"></span>
			</div>
			<div class="group">
				<table width="100%">
					<tr>
						<td><textarea id="add_INTRODUCION_id" name="INTRODUCION"></textarea></td>
					</tr>
				</table>
			</div>
	</div>
	
	<div id="uploadDialog" style="display:none;">
		<form id="uploadForm" enctype="multipart/form-data" target="uploadIframe" method="post" action="../hotProduct/productSellAction.do?act=upload">
			<table align="center" width="350" border="0" cellpadding="0" cellspacing="0">
			        <tr>
			          <td>&nbsp;</td>
			        </tr>
			        <tr>
			          <td width="91%"><b>请选择上传的文件：</b><br>			           
			            <input id="file1" name="file1" type="file" size="35" onkeydown="return false;">
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
