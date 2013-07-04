/**************************************************
 * Copyright (c) 2013.
 * 文件名称: ResultConfigController.java
 * 摘　　要: 
 *
 * 当前版本: 1.0
 * 作　　者: 宋晓灵
 * 完成日期: 2013-4-25
 **************************************************/
package com.dpf.controllers.busiMonitor;

import com.dpf.domain.Paging;
import com.dpf.services.busiMonitor.ResultConfigService;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

@Path("resultConfig")
public class ResultConfigController {
	private ResultConfigService resultConfigService = new ResultConfigService();
	
	@Post("list")
	public Object queryConfig(Invocation inv){
		return resultConfigService.getConfig(inv.getRequest());
	}
	
	@Post("add")
	public Object addConfig(Invocation inv){
		try {
			resultConfigService.addConfig(inv.getRequest());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}		
	@Post("edit")
	public Object editConfig(Invocation inv){
		try {
			resultConfigService.editConfig(inv.getRequest());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Post("del")
	public Object delConfig(Invocation inv){
		try {
			resultConfigService.delConfig(inv.getRequest());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Post("toolbarList")
	public Object toolbarList(Invocation inv){
		return resultConfigService.toolbarList(inv.getRequest());
	}
	@Post("toolbarDetail")
	public Object toolbarDetail(Invocation inv){
		return resultConfigService.toolbarDetail(inv.getRequest());
	}
	@Post("toolbarDetail/add")
	public Object toolbarListAdd(Invocation inv){
		return null;
	}
	
	@Post("searchList")
	public Object searchList(Invocation inv){
		return resultConfigService.searchList(inv.getRequest());
	}
	@Post("searchList/add")
	public Object addSearchList(Invocation inv) throws Exception{
		resultConfigService.addSearch(inv.getRequest());
		return null;
	}
	@Post("searchList/edit")
	public Object editSearchList(Invocation inv) throws Exception{
		resultConfigService.editSearch(inv.getRequest());
		return null;
	}
	@Post("searchList/del")
	public Object delSearchList(Invocation inv) throws Exception{
		resultConfigService.deleteSearch(inv.getRequest());
		return null;
	}
	
	@Post("selectDs")
	public Object selectDs(Invocation inv) throws Exception{
		return resultConfigService.selectDs(inv.getRequest());
	}
	
	@Get
	public String resultConfig(Invocation inv,Paging page){
		return "resultConfig";
	}
}
