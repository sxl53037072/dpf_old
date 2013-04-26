package com.dpf.domain.busiMonitor;

import java.util.List;

/**
 * <p>
 * company：
 * </p>
 * <Description>描述:自定义查询sql</p>
 * <p>
 * create_time： 2013-3-25 下午3:38:46
 * </p>
 * 
 * @author songxl
 * @version ver 1.0
 * @since jdk1.6
 */
public class SQLInfo {	

	public static final String SQL = "SQL";

	public static final String PROC = "PROC";

	private int sqlId;

	private String sqlText;

	private String sqlType;

	private int cursorIndex;

	private String paramXml;

	private List<?> paramList;
	
	public int getSqlId() {
		return sqlId;
	}

	public void setSqlId(int sqlId) {
		this.sqlId = sqlId;
	}

	public String getSqlText() {
		return sqlText;
	}

	public void setSqlText(String sqlText) {
		this.sqlText = sqlText;
	}

	public String getSqlType() {
		return sqlType;
	}

	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}

	public int getCursorIndex() {
		return cursorIndex;
	}

	public void setCursorIndex(int cursorIndex) {
		this.cursorIndex = cursorIndex;
	}

	public String getParamXml() {
		return paramXml;
	}

	public void setParamXml(String paramXml) {
		this.paramXml = paramXml;
	}

	public List<?> getParamList() {
		return paramList;
	}

	public void setParamList(List<?> paramList) {
		this.paramList = paramList;
	}
}
