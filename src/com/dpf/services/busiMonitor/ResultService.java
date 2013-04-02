package com.dpf.services.busiMonitor;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpf.dao.busiMonitor.SQLDao;
import com.dpf.domain.busiMonitor.SQLInfo;
import com.dpf.exception.ApplicationException;
import com.dpf.exception.SystemException;
import com.dpf.services.query.SQLParser;
import com.dpf.util.DBCtrl;
import com.dpf.util.DataUtil;
/**************************************************
 * Copyright (c) 2013.
 * 文件名称: ResultService.java
 * 摘　　要: 
 *
 * 当前版本: 1.0
 * 作　　者: 宋晓灵
 * 完成日期: 2013-3-29
 **************************************************/
public class ResultService {
	private static Logger logger = Logger
			.getLogger(ResultService.class.getName());
	public String getField(String key) throws ApplicationException, SystemException{
		return SQLDao.getFieldJson(key);
	}
	public String getPageResultJson(HttpServletRequest request) throws ApplicationException, SystemException{
		SQLResult sqlResult = new SQLResult();
		return sqlResult.getPageResultJson(DataUtil.requestToMap(request));
	}
}
