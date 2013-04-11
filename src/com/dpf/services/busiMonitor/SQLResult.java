/**************************************************
 * Copyright (c) 2013.
 * 文件名称: SQLResult.java
 * 摘　　要: 
 *
 * 当前版本: 1.0
 * 作　　者: 宋晓灵
 * 完成日期: 2013-4-2
 **************************************************/
package com.dpf.services.busiMonitor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;

import com.dpf.dao.busiMonitor.SQLDao;
import com.dpf.domain.Paging;
import com.dpf.domain.busiMonitor.SQLInfo;
import com.dpf.exception.ApplicationException;
import com.dpf.exception.SystemException;
import com.dpf.services.query.ParamInfo;
import com.dpf.services.query.SQLParser;
import com.dpf.util.DBCtrl;
import com.dpf.util.DataUtil;
import com.dpf.util.JsonBiz;
import com.dpf.util.JsonUtil;
public class SQLResult {		
	private SQLInfo sqlInfo = new SQLInfo();

	private ExecuteSQLAction action = null;
	
	private Element resultNode;
	
	private Paging paging = new Paging();
	
	private void buildSQL(String key) throws ApplicationException,
			SystemException {
		// int sqlId = Integer.parseInt(this.resultNode.attributeValue("id"));
		this.sqlInfo = SQLDao.getSqlInfo(key);
		if(DataUtil.isNullOrEmpty(this.sqlInfo.getSqlType())){
			throw new ApplicationException("未找到指定ID的自定义查询:"+key);
		}
		if (this.sqlInfo.getSqlType().equals(SQLInfo.SQL)) {
			this.action = new ExecuteSQL();
		} else {
			this.action = new ExecuteProc();
		}
		SQLParser sqlParser = new SQLParser();
		sqlParser.setParamElement(this.resultNode);
//		sqlParser.setSVCtrl(this.svCtrl);
		this.sqlInfo.setSqlText(this.action.buildSQL(this.sqlInfo.getSqlText(),
				sqlParser));
		this.sqlInfo.setParamList(sqlParser.getParamList());
		// System.out.println(this.resultNode.asXML());
	}
	
	public String getPageResultJson(Map map){
        int totalCount = 0;
        this.resultNode = JsonUtil.jsonToXml(map);
        Connection conn = null;
        ResultSet rs = null;
		try{
			conn = DBCtrl.getConnection();
			this.buildSQL((String)map.get("resultKey"));
			totalCount = this.action.getTotalCount(conn, this.sqlInfo);
			if (this.sqlInfo.getSqlType().equals(SQLInfo.SQL)) {
				this.buildPageSQL();
			}
			rs = this.action.execute(conn, this.sqlInfo);	
			return JsonUtil.rsToJqGrid(rs, totalCount, paging);
		}catch(Exception e){
			e.printStackTrace();
			return JsonBiz.isSucceed(false, e.toString()).toString();
		}finally{
			DBCtrl.close(conn, rs);
		}
		
	}
	public String getColumnModel(Map map){
        this.resultNode = JsonUtil.jsonToXml(map);
        Connection conn = null;
        ResultSet rs = null;
		try{
			conn = DBCtrl.getConnection();
			this.buildSQL((String)map.get("resultKey"));
			this.buildPageSQL();
			rs = this.action.execute(conn, this.sqlInfo);	
			return JsonUtil.rsToColMode(rs);
		}catch(Exception e){
			e.printStackTrace();
			return JsonBiz.isSucceed(false, e.toString()).toString();
		}finally{
			DBCtrl.close(conn, rs);
		}
	}

	private void buildPageSQL() {
		StringBuffer sqlBuf = new StringBuffer("");
		String sortField = this.getElementText(this.resultNode,
				"param[@name='sort']", "");
		if (!sortField.equals("")) {
			String sortDir = this.getElementText(this.resultNode,
					"param[@name='order']", "");

			sqlBuf.append("SELECT * FROM (").append(this.sqlInfo.getSqlText())
					.append(") ORDER BY \"").append(sortField).append("\" ")
					.append(sortDir);
		} else {
			sqlBuf.append(this.sqlInfo.getSqlText());
		}
		StringBuffer buf = new StringBuffer("");
		buf.append("SELECT * FROM (SELECT ROWNUM SID_ROWNUM,A.* FROM (")
				.append(sqlBuf).append(") A) B WHERE B.SID_ROWNUM>? AND B.SID_ROWNUM<=?");
		this.sqlInfo.setSqlText(buf.toString());
		List paramList = this.sqlInfo.getParamList();
		int page = this.getElementNumber(this.resultNode,
				"param[@name='page']", 1);
		int rows = this.getElementNumber(this.resultNode,
				"param[@name='rows']", 10);
		int start = (page-1)*rows;
		int end = start + rows;
		paging.setPage(page);
		paging.setRows(rows);
		ParamInfo paramInfo = new ParamInfo();
		paramInfo.setValue(new Integer(start));
		paramInfo.setType(ParamInfo.INT_TYPE);
		paramList.add(paramInfo);

		paramInfo = new ParamInfo();
		paramInfo.setValue(new Integer(end));
		paramInfo.setType(ParamInfo.INT_TYPE);
		paramList.add(paramInfo);
	}

	private String getElementText(Element oElement, String xpath,
			String defaultValue) {
		Element e = (Element) oElement.selectSingleNode(xpath);
		return (e == null) ? defaultValue : e.getText();
	}

	private int getElementNumber(Element oElement, String xpath,
			int defaultValue) {
		return Integer.parseInt(this.getElementText(oElement, xpath,
				Integer.toString(defaultValue)));
	}
}
