/*
Copyright (c) 2003-2012, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

//设置工具栏，高度为70
	CKEDITOR.replace('myactivity',
	{ 	toolbar :[	//加粗 斜体， 下划线 穿过线 下标字 上标字
	  	          	['Bold','Italic','Underline','Strike','Subscript','Superscript'],
	  	      	  	//数字列表 实体列表 减小缩进 增大缩进
	  	          	['NumberedList','BulletedList','-','Outdent','Indent'],
	  	          	//左对齐 居中对齐 右对齐 两端对齐
	  	          	['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
	  	          	//超链接 取消超链接 锚点
	  	          	['Link','Unlink'],
	  	          	['-','Table','HorizontalRule','-','SpecialChar','-'],
	  	      		//样式 格式 字体 字体大小
	  	        	['Styles','Format','Font','FontSize'],
	  	        	//文本颜色 背景颜色
	  	        	['TextColor','BGColor'],
	  	        	['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
	  	        	
	  	         ],	
		height:300 }); 
	
	function getEditorHtml(){
		var oEditor = CKEDITOR.instances.myactivity;
		return oEditor.getData();
	}
	
	function setEditorHtml(text){
		var oEditor = CKEDITOR.instances.myactivity;
		return oEditor.setData(text);
	}
