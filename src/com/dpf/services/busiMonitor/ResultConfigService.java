/**************************************************
 * Copyright (c) 2013.
 * 文件名称: ResultConfigService.java
 * 摘　　要: 
 *
 * 当前版本: 1.0
 * 作　　者: 宋晓灵
 * 完成日期: 2013-4-25
 **************************************************/
package com.dpf.services.busiMonitor;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.dpf.dao.busiMonitor.ResultConfigDao;

public class ResultConfigService {
	private ResultConfigDao resultConfigDao = new ResultConfigDao();
	
	public Object getConfig(HttpServletRequest request){
		HashMap<String, Object> map = resultConfigDao.getConfig(request);
		return JSONObject.fromObject(map);
	}
	public void addConfig(HttpServletRequest request) throws Exception{
		resultConfigDao.addConfig(request);
	}
	public void editConfig(HttpServletRequest request) throws Exception{
		resultConfigDao.editConfig(request);
	}
	public Object toolbarList(HttpServletRequest request){
		HashMap<String, Object> map = resultConfigDao.toolbarList(request);
		return JSONObject.fromObject(map);
	}
	
}
