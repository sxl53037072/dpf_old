package com.dpf.services.busiMonitor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.dpf.domain.busiMonitor.SQLInfo;
import com.dpf.exception.ApplicationException;
import com.dpf.services.query.ParamInfo;
import com.dpf.services.query.SQLParser;

public class ExecuteSQL implements ExecuteSQLAction
{
    public String buildSQL(String sql, SQLParser sqlParser)
            throws ApplicationException
    {
        sqlParser.setSql(sql);
        sqlParser.parse();
        return sqlParser.getSql();
    }

    private ResultSet executeSQL(Connection conn, String sql, List paramList)
            throws ApplicationException
    {
        try
        {
            // System.out.println("**************************************");
            // System.out.println("sql=" + sql);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < paramList.size(); i++)
            {
                ParamInfo v = (ParamInfo) paramList.get(i);
                // System.out.println(i + "=" + v.getValue());
                pstmt.setObject(i + 1, v.getValue(), v.getType());
            }
            return pstmt.executeQuery();
        }
        catch (SQLException e)
        {
            String[] msgs = { "错误的sql=" + sql };
            throw new ApplicationException("执行SQL错误", e, msgs);
        }
    }

    public int getTotalCount(Connection conn, SQLInfo info)
            throws ApplicationException
    {
        StringBuffer buf = new StringBuffer("");
        buf.append("SELECT COUNT(*) COUNT FROM (").append(info.getSqlText())
                .append(")");
        ResultSet rs = this.executeSQL(conn, buf.toString(), info
                .getParamList());
        try
        {
            rs.next();
            return rs.getInt(1);
        }
        catch (SQLException e)
        {
            throw new ApplicationException("获取记录数量错误!", e);
        }
    }

    public ResultSet execute(Connection conn, SQLInfo info)
            throws ApplicationException
    {
        return this.executeSQL(conn, info.getSqlText(), info.getParamList());
    }

}
