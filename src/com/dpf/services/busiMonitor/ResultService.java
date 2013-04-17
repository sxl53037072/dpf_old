/**************************************************
 * Copyright (c) 2013.
 * 文件名称: ResultService.java
 * 摘　　要: 
 *
 * 当前版本: 1.0
 * 作　　者: 宋晓灵
 * 完成日期: 2013-3-29
 **************************************************/
package com.dpf.services.busiMonitor;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.dpf.dao.busiMonitor.SQLDao;
import com.dpf.exception.ApplicationException;
import com.dpf.exception.SystemException;
import com.dpf.util.DataUtil;
import com.dpf.util.JsonBiz;

public class ResultService {
	private static Logger logger = Logger
			.getLogger(ResultService.class.getName());
	/**
	 * 功能:	获取自定义查询列定义
	 *
	 * @param key
	 * @return
	 * @throws ApplicationException
	 * @throws SystemException
	 */
	public Object getField(String key) throws ApplicationException, SystemException{
		return JsonBiz.getJsonDataForOption(SQLDao.getFieldJson(key));		
	}
	/**
	 * 功能:	执行SQL返回结果
	 *
	 * @param request
	 * @return
	 * @throws ApplicationException
	 * @throws SystemException
	 */
	public Object getPageResultJson(HttpServletRequest request) throws ApplicationException, SystemException{
		SQLResult sqlResult = new SQLResult();
		return JSONObject.fromObject(sqlResult.getPageResultJson(DataUtil.requestToMap(request)));
	}
	/**
	 * 功能:	根据SQL获取列定义，只在未配置GET_VALUE_CFG_FIELD时返回
	 *
	 * @param request
	 * @return
	 * @throws ApplicationException
	 * @throws SystemException
	 */
	public Object getColModel(HttpServletRequest request) throws ApplicationException, SystemException{
		SQLResult sqlResult = new SQLResult();
		return JSONObject.fromObject(sqlResult.getColumnModel(DataUtil.requestToMap(request)));
	}
	/**
	 * 功能:	获取自定义查询配置 包括基本的一些grid配置/及工具栏按钮配置
	 *
	 * @param key
	 * @return
	 * @throws ApplicationException
	 * @throws SystemException
	 */
	public Object getValueCfg(String key) throws ApplicationException, SystemException{
		HashMap<String, Object> map = SQLDao.getValueCfg(key);
		if(!DataUtil.isNullOrEmpty(String.valueOf(map.get("toolbar_menu_id")))){
			List<HashMap<String, String>> list = SQLDao.getSysMenuItem(map.get("toolbar_menu_id").toString());
			map.put("toolbar_menu", list);
		}		
		return JSONObject.fromObject(map);
	}
	/**
	 * 功能:	获取搜索控件配置
	 *
	 * @param key
	 * @return
	 * @throws ApplicationException
	 * @throws SystemException
	 */
	public Object getSqlParam(String key) throws ApplicationException, SystemException{
		return JsonBiz.getJsonDataForOption(SQLDao.getSqlParam(key));		
	}
	/**
	 * 功能:	动态执行SQL
	 *
	 * @param sql
	 * @return
	 * @throws ApplicationException
	 * @throws SystemException
	 */
	public Object execSql(String sql) throws ApplicationException, SystemException{
		return JsonBiz.getJsonDataForOption(SQLDao.execSql(sql));		
	}
}
