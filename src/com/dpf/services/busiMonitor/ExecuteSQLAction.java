package com.dpf.services.busiMonitor;

import java.sql.Connection;
import java.sql.ResultSet;

import com.dpf.domain.busiMonitor.SQLInfo;
import com.dpf.exception.ApplicationException;
import com.dpf.services.query.SQLParser;


public interface ExecuteSQLAction
{
    public String buildSQL(String sql, SQLParser sqlParser)
            throws ApplicationException;

    public int getTotalCount(Connection conn, SQLInfo info)
            throws ApplicationException;

    public ResultSet execute(Connection conn, SQLInfo info)
            throws ApplicationException;
}
