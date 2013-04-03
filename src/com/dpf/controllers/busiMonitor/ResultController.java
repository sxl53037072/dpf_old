/**************************************************
 * Copyright (c) 2013.
 * 文件名称: ResultController.java
 * 摘　　要: 
 *
 * 当前版本: 1.0
 * 作　　者: 宋晓灵
 * 完成日期: 2013-3-29
 **************************************************/
package com.dpf.controllers.busiMonitor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.dpf.domain.Paging;
import com.dpf.exception.ApplicationException;
import com.dpf.exception.SystemException;
import com.dpf.services.busiMonitor.ResultService;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

@Path("result")
public class ResultController {	
	private ResultService resultService = new ResultService();
	
	@Post("colModel/{id}")
	public Object queryColModel(@Param("id") String id, Invocation inv){
		try {			
			return resultService.getField(id);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}					
		return null;
	}
	@Post("list/{resultKey}")
	public Object queryData(@Param("resultKey") String id, Invocation inv){
		try {			
			return resultService.getPageResultJson(inv.getRequest());
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Get
	public String resultGrid(Invocation inv,Paging page){
		return "resultGrid";
	}
}
