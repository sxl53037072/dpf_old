package com.dpf.services.busiMonitor;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.dpf.domain.busiMonitor.SQLInfo;
import com.dpf.exception.ApplicationException;
import com.dpf.services.query.ParamInfo;
import com.dpf.services.query.SQLParser;

import oracle.jdbc.driver.OracleTypes;

public class ExecuteProc implements ExecuteSQLAction {
	public String buildSQL(String sql, SQLParser sqlParser)
			throws ApplicationException {
		sqlParser.setSql(sql);
		sqlParser.simpleParse();
		return sqlParser.getSql();
	}

	public int getTotalCount(Connection conn, SQLInfo info)
			throws ApplicationException {
		return 0;
	}

	public ResultSet execute(Connection conn, SQLInfo info)
			throws ApplicationException {
		StringBuffer sbuf = new StringBuffer("{ CALL ");
		sbuf.append(info.getSqlText());
		sbuf.append("}");
		List paramList = info.getParamList();
		String sql = sbuf.toString();
		int cursor = info.getCursorIndex();
		try {
//			System.out.println("**************************************");
//			System.out.println("sql=" + sql);
			CallableStatement csmt = conn.prepareCall(sql);
			csmt.registerOutParameter(cursor, OracleTypes.CURSOR);
			for (int i = 1; i <= paramList.size(); i++) {
				if (i != cursor) {
					ParamInfo v = (ParamInfo) paramList.get(i - 1);
//					System.out.println(i + "=" + v.getValue());
					csmt.setObject(i, v.getValue(), v.getType());
				}
			}
			csmt.execute();
			return (ResultSet) csmt.getObject(cursor);
		} catch (SQLException e) {
			String[] msgs = { "错误的sql=" + sql };
			throw new ApplicationException("执行SQL错误", e, msgs);
		}
	}
}
