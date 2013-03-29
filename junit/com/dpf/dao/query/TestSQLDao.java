package com.dpf.dao.query;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dpf.dao.busiMonitor.SQLDao;
import com.dpf.domain.busiMonitor.SQLInfo;
import com.dpf.exception.ApplicationException;
import com.dpf.services.query.SQLParser;

/**************************************************
 * Copyright (c) 2013.
 * 文件名称: TestSQLDao.java
 * 摘　　要: 
 *
 * 当前版本: 1.0
 * 作　　者: 宋晓灵
 * 完成日期: 2013-3-27
 **************************************************/
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebRoot/WEB-INF/junit.xml" })
public class TestSQLDao {
	@Autowired
	public SQLDao SQLDao;
	
	@Test
	public void testGetSqlInfo() throws ApplicationException{
		SQLInfo SQLInfo = SQLDao.getSqlInfo(1);
		System.out.println("sql="+SQLInfo.getSqlText());
		SQLParser sqlParser = new SQLParser();
		sqlParser.setSql(SQLInfo.getSqlText());
		Element paramElement = DocumentHelper.createElement("params");
		sqlParser.setParamElement(paramElement);
		sqlParser.parse();
		System.out.println("parse sql="+sqlParser.getSql());
	}
}
