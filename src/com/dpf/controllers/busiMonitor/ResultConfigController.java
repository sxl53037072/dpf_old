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
	@Post("toolbarList")
	public Object toolbarList(Invocation inv){
		return resultConfigService.toolbarList(inv.getRequest());
	}
	
	@Get
	public String resultConfig(Invocation inv,Paging page){
		return "resultConfig";
	}
}
