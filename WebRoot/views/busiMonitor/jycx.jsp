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
			     { id:1, pId:0, name:"当日查询", open:true},
			     { id:7, pId:1, name:"当日委托"},
			     { id:8, pId:1, name:"当日成交"},
			     { id:12, pId:1, name:"当日新股申购"},
			     { id:13, pId:1, name:"当日银证转账"},
			     { id:14, pId:1, name:"开放式基金委托"},
			     { id:15, pId:1, name:"基金定投"},
			     
			     { id:33, pId:0, name:"昨日查询", open:true},
			     { id:16, pId:33, name:"昨日资金查询"},
			     { id:20, pId:33, name:"昨日股票持仓查询"},
			     { id:25, pId:33, name:"昨日开放式基金份额"}			     
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
									'<div><table id="resultGrid"></table> <div id="resultGrid_pager"></div></div>');	
							if(treeNode.id == "7"){
								jQuery('#resultGrid').ResultGrid({				   
								   result : treeNode.id,
								   subGrid: true,
								   subGridRowExpanded: function(subgrid_id, row_id) {	
										var row = $("#resultGrid").jqGrid("getOriRowData",row_id);
										var $detail = $('<div style="padding:2px;height:250px;background-color:#F5F5F5" alreadyLoad="N" id="ddv-' + row_id + '">');
										$('#' + subgrid_id).append($detail);
									   	var $ul1 = $('<ul style="list-style-type:none">');
										$ul1.append($("<li class='datagridDetailLi'>成交时间：</li>").append($("<input type='text' readonly=true style='width:150px'>")));
										$ul1.append($("<li class='datagridDetailLi'>证券代码：</li>").append($("<input type='text' readonly=true style='width:150px'>")));
										$ul1.append($("<li class='datagridDetailLi'>证券名称：</li>").append($("<input type='text' readonly=true style='width:150px'>")));
										var $ul2 = $('<ul style="list-style-type:none">');
										$ul2.append($("<li class='datagridDetailLi'>买卖方向：</li>").append($("<input type='text' readonly=true style='width:150px'>")));
										$ul2.append($("<li class='datagridDetailLi'>成交均价：</li>").append($("<input type='text' readonly=true style='width:150px'>")));
										$ul2.append($("<li class='datagridDetailLi'>成交数量：</li>").append($("<input type='text' readonly=true style='width:150px'>")));
										var $ul3 = $('<ul style="list-style-type:none">');
										$ul3.append($("<li class='datagridDetailLi'>成交金额：</li>").append($("<input type='text' readonly=true style='width:150px'>")));
										$ul3.append($("<li class='datagridDetailLi'>成交笔数：</li>").append($("<input type='text' readonly=true style='width:150px'>")));
										$ul3.append($("<li class='datagridDetailLi'>&nbsp;&nbsp;&nbsp;&nbsp;合同号：</li>").append($("<input type='text' readonly=true style='width:150px'>")));
										var $ul4 = $('<ul style="list-style-type:none">');
										$ul4.append($("<li class='datagridDetailLi'>股东账号：</li>").append($("<input type='text' readonly=true style='width:150px'>")));
										$detail.append($ul1);
										$detail.append($ul2);
										$detail.append($ul3);
										$detail.append($ul4);
									}
							    });
							}else if(treeNode.id == "8" || treeNode.id == "9" || treeNode.id == "10" || treeNode.id == "11"){
								jQuery('#resultGrid').ResultGrid({				   
								   result : treeNode.id,
								   gridComplete : function(){
									   $("#zcdm").after($("#drcjDiv").html());
								   }
							    });
							}else if(treeNode.id == "17" || treeNode.id == "18" || treeNode.id == "19" || treeNode.id == "20"){	
								jQuery('#resultGrid').ResultGrid({				   
								   result : treeNode.id,
								   gridComplete : function(){
									   $("#zqdm").after("<div></div>"+$("#zrgpccDiv").html());
								   }
							    });
							}else if(treeNode.id == "21" || treeNode.id == "22" || treeNode.id == "23" || treeNode.id == "24" || treeNode.id == "25"){		
								jQuery('#resultGrid').ResultGrid({				   
								   result : treeNode.id,
								   gridComplete : function(){
									   $("#zcdm").after($("#zrkfsjjfeDiv").html());
								   }
							    });								
							}else{
								jQuery('#resultGrid').ResultGrid({				   
								   result : treeNode.id
							    });
							}
						    
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
	
	.datagridDetailLi {
		float:left;
		margin-right:20px;
		margin-top:10px;
		height:50px;
		line-height:60px;
		vertical-align:middle;
	} 
	.lineText {
		border:0px;
		border-bottom:1px;
		border-bottom-style:solid;
		border-bottom-color:#C0C0C0;
	}
	body{font-size:12px;}
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
	
	
	<div id="drcjDiv">
		<LABEL id=resultGrid_cxlx_label>查询类型</LABEL>
		<input type="radio" name="cxlx" checked></input>成交明细
		<input type="radio" name="cxlx"></input>按股票汇总
		<input type="radio" name="cxlx"></input>按客户汇总
		<input type="radio" name="cxlx"></input>所有汇总
	</div>
	
	<div id="zrgpccDiv">
		<LABEL id=resultGrid_cxlx_label>查询类型</LABEL>
		<input type="radio" name="cxlx" checked></input>明细
		<input type="radio" name="cxlx"></input>股票汇总
		<input type="radio" name="cxlx"></input>客户汇总
		<input type="radio" name="cxlx"></input>所有汇总
	</div>
	
	<div id="zrkfsjjfeDiv">
		<LABEL id=resultGrid_cxlx_label>查询类型</LABEL>
		<input type="radio" name="cxlx" checked></input>明细
		<input type="radio" name="cxlx"></input>按基金汇总
		<input type="radio" name="cxlx"></input>按类型汇总
		<input type="radio" name="cxlx"></input>按客户汇总
		<input type="radio" name="cxlx"></input>按投资顾问汇总
	</div>	
	</body>
</html>
