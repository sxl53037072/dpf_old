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
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
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
	public void delConfig(HttpServletRequest request) throws Exception{
		resultConfigDao.delConfig(request);
	}
	public Object toolbarList(HttpServletRequest request){
		HashMap<String, Object> map = resultConfigDao.toolbarList(request);
		return JSONObject.fromObject(map);
	}	
	public Object toolbarDetail(HttpServletRequest request){
		HashMap<String, Object> map = resultConfigDao.toolbarDetail(request);
		return JSONObject.fromObject(map);
	}
	
	public Object searchList(HttpServletRequest request){
		HashMap<String, Object> map = resultConfigDao.searchList(request);
		return JSONObject.fromObject(map);
	}
	public void addSearch(HttpServletRequest request) throws Exception{
		resultConfigDao.addSearch(request);
	}
	public void editSearch(HttpServletRequest request) throws Exception{
		resultConfigDao.editSearch(request);
	}
	public void deleteSearch(HttpServletRequest request) throws Exception{
		resultConfigDao.deleteSearch(request);
	}
	
	public Object selectDs(HttpServletRequest request) throws Exception{
		List<HashMap<String, String>> list = resultConfigDao.selectDs(request);
		return JSONArray.fromObject(list);
	}
	
}
