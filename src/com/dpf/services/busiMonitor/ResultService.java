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
	 * 功能:	获取自定义查询各项配置
	 *
	 * @param key  SQL_ID标识
	 * @param request  请求
	 * @return  map 
	 * {
	 * 		colModel : [],          //列定义
	 *      valueCfg : {},         //表格基本配置
	 *      toolbar_menu : [],     //工具栏定义
	 *      sqlParam : []          //搜索控件配置
	 * }
	 * @throws ApplicationException
	 * @throws SystemException
	 */
	public Object getConfig(String key, HttpServletRequest request) throws ApplicationException, SystemException{
		HashMap<String, Object> configMap = new HashMap<String, Object>();						
		List<HashMap<String, String>> colList = SQLDao.getFieldJson(key);
		SQLResult sqlResult = new SQLResult();
		if(colList.size() == 0){
			colList = sqlResult.getColumnModelList(DataUtil.requestToMap(request));
		}
		configMap.put("colModel", colList);
		HashMap<String, Object> valueCfgMap = SQLDao.getValueCfg(key);
		configMap.put("valueCfg", valueCfgMap);
		if(!DataUtil.isNullOrEmpty(String.valueOf(valueCfgMap.get("toolbar_menu_id")))){
			List<HashMap<String, String>> menuList = SQLDao.getSysMenuItem(valueCfgMap.get("toolbar_menu_id").toString());
			configMap.put("toolbar_menu", menuList);
		}
		List<HashMap<String, String>> sqlParam = SQLDao.getSqlParam(key);
		configMap.put("sqlParam", sqlParam);
		return JsonBiz.getJsonDataForOneRecord(configMap);
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
