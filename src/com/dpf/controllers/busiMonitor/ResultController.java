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
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Post;

/**************************************************
 * Copyright (c) 2013.
 * 文件名称: ResultController.java
 * 摘　　要: 
 *
 * 当前版本: 1.0
 * 作　　者: 宋晓灵
 * 完成日期: 2013-3-29
 **************************************************/
@Path("result")
public class ResultController {
	@Autowired
	private ResultService resultService;
	
	@Post("colModel/{id}")
	public Object queryColModel(Invocation inv){
		System.out.println("id="+inv.getParameter("result"));
		try {
			System.out.println("json="+resultService.getField(inv.getParameter("result")));
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
