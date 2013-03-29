package com.dpf.dao.query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.dpf.domain.query.SQLInfo;
import com.dpf.exception.ApplicationException;
import com.dpf.exception.SystemException;

/**************************************************
 * Copyright (c) 2013.
 * 文件名称: SQLDao.java
 * 摘　　要: 自定义查询获取配置
 *
 * 当前版本: 1.0
 * 作　　者: 宋晓灵
 * 完成日期: 2013-3-27
 **************************************************/
@Repository("SQLDao")
public class SQLDao {
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	private static final String SQL_GET_SQL_TEXT = "SELECT B.SQL_ID, B.SQL_TEXT, B.SQL_TYPE, B.CURSOR_INDEX FROM GET_VALUE_CFG A, SQL_CFG B WHERE A.GET_VALUE_ID = B.SQL_ID AND A.GET_VALUE_CFG_ID = ?";
	
	public SQLInfo getSqlInfo(int key) {
		final SQLInfo sqlInfo = new SQLInfo();
		jdbcTemplate.query(SQL_GET_SQL_TEXT, new Object[] { key },
		new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				sqlInfo.setSqlId(rs.getInt(1));
				sqlInfo.setSqlText(rs.getString(2));
				sqlInfo.setSqlType(rs.getString(3));
				sqlInfo.setCursorIndex(rs.getInt(4));	
			}
		});
		return sqlInfo;		
	}
}
