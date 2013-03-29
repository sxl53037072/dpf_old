package com.dpf.services.busiMonitor;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpf.dao.busiMonitor.SQLDao;
import com.dpf.exception.ApplicationException;
import com.dpf.exception.SystemException;
/**************************************************
 * Copyright (c) 2013.
 * 文件名称: ResultService.java
 * 摘　　要: 
 *
 * 当前版本: 1.0
 * 作　　者: 宋晓灵
 * 完成日期: 2013-3-29
 **************************************************/
@Service("resultService")
public class ResultService {
	private static Logger logger = Logger
			.getLogger(ResultService.class.getName());
	@Autowired
	private SQLDao SQLDao;
	public String getField(String key) throws ApplicationException, SystemException{
		return SQLDao.getFieldJson(key);
	}
}
