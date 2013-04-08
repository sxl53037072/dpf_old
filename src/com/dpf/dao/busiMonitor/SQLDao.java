/**************************************************
 * Copyright (c) 2013.
 * 文件名称: SQLDao.java
 * 摘　　要: 自定义查询获取配置
 *
 * 当前版本: 1.0
 * 作　　者: 宋晓灵
 * 完成日期: 2013-3-27
 **************************************************/
package com.dpf.dao.busiMonitor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.dpf.domain.busiMonitor.SQLInfo;
import com.dpf.exception.ApplicationException;
import com.dpf.exception.SystemException;
import com.dpf.util.DBCtrl;
import com.dpf.util.DataUtil;
import com.dpf.util.JsonUtil;

public class SQLDao {
	
	private static final String SQL_GET_SQL_TEXT = "SELECT B.SQL_ID, B.SQL_TEXT, B.SQL_TYPE, B.CURSOR_INDEX FROM GET_VALUE_CFG A, SQL_CFG B WHERE A.GET_VALUE_ID = B.SQL_ID AND A.GET_VALUE_CFG_ID = ?";
	
	private static final String SQL_GET_FIELD = "SELECT upper(NAME) name,LABEL,WIDTH,CONFIG_SCRIPT FROM GET_VALUE_CFG_FIELD WHERE GET_VALUE_CFG_ID = ? ORDER BY SORT_ID";
	
	private static final String SQL_GET_VALUE_CFG = 
					"SELECT T.GET_VALUE_CFG_ID,\n" +
					"       T.TITLE,\n" + 
					"       T.IS_PAGE,\n" + 
					"       T.PAGE_SIZE,\n" + 
					"       T.IS_FORCEFIT,\n" + 
					"       T.TOOLBAR_MENU_ID,\n" + 
					"       T.RIGHT_MENU_ID,\n" + 
					"       T.HIDDEN_COLUMNS,\n" + 
					"       T.DBLCLICK,\n" + 
					"       T.CONFIG_SCRIPT,\n" + 
					"       T.IMPORT_JS,\n" + 
					"       T.IMPORT_CSS,\n" + 
					"       T.COLUMN_CFG_TYPE,\n" + 
					"       T.DEFAULT_SEARCH,\n" + 
					"       T.FILED_SEARCH\n" + 
					"  FROM GET_VALUE_SHOW_CFG T\n" + 
					" WHERE T.GET_VALUE_CFG_ID = ?";
	
	/**
	 * 功能:	获取自定义查询的SQL
	 *
	 * @param key 自定义查询ID
	 * @return 查询到的数据
	 */
	public static SQLInfo getSqlInfo(String key) {
		SQLInfo sqlInfo = new SQLInfo();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try{
			conn = DBCtrl.getConnection();
			pstmt = conn.prepareStatement(SQL_GET_SQL_TEXT);
			pstmt.setString(1, key);
			rs = pstmt.executeQuery();
			if(rs.next()){
				sqlInfo.setSqlId(rs.getInt(1));
				sqlInfo.setSqlText(rs.getString(2));
				sqlInfo.setSqlType(rs.getString(3));
				sqlInfo.setCursorIndex(rs.getInt(4));	
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBCtrl.close(conn, rs, pstmt);
		}
		return sqlInfo;		
	}
	
	/**
	 * 功能:	获取自定义查询列定义
	 *
	 * @param key 自定义查询ID
	 * @return 查询到的数据 
	 * @throws ApplicationException
	 * @throws SystemException
	 */
	public static List<HashMap<String, String>> getFieldJson(String key) throws ApplicationException,
			SystemException {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try{
			conn = DBCtrl.getConnection();
			pstmt = conn.prepareStatement(SQL_GET_FIELD);
			pstmt.setString(1, key);
			rs = pstmt.executeQuery();
			while(rs.next()){
				HashMap<String, String> map = new HashMap<String, String>();
				map = DataUtil.rsToMap(rs);			
				list.add(map);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBCtrl.close(conn, rs, pstmt);
		}
		return list;//JsonUtil.listToJson(list);
	}
	/**
	 * 功能:	获取自定义查询配置
	 *
	 * @param key 自定义查询ID
	 * @return 查询到的数据 
	 * @throws ApplicationException
	 * @throws SystemException
	 */
	public static HashMap<String, String> getValueCfg(String key) throws ApplicationException,
			SystemException {
		HashMap<String, String> map = new HashMap<String, String>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try{
			conn = DBCtrl.getConnection();
			pstmt = conn.prepareStatement(SQL_GET_VALUE_CFG);
			pstmt.setString(1, key);
			rs = pstmt.executeQuery();
			if(rs.next()){
				map = DataUtil.rsToMap(rs);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBCtrl.close(conn, rs, pstmt);
		}
		return map;
	}
}
