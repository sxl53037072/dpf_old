package com.dpf.services.query;

import java.util.List;
import org.dom4j.Element;

import com.dpf.exception.ApplicationException;

public class SQLParser
{
    StringParser strParser    = new StringParser();

    ParamParser  paramParser  = new ParamParser();

    SubParser    subParser    = new SubParser();

    FilterParser filterParser = new FilterParser();

    String       sql;

    public boolean hasParamVal(String parseSQL) throws ApplicationException
    {
        String str = paramParser.parseToToken(parseSQL);
        return !ParamParser.hasDelParam(str);
    }

    private String transformParamToDel(String transformSQL)
    {
        return paramParser.parseToToken(transformSQL);
    }

    public String transformParam(String transformSQL)
            throws ApplicationException
    {
        String s = paramParser.parseToSQL(transformSQL);
        return s;
    }

    public String parseString(String parseSQL)
    {
        return this.strParser.parse(parseSQL);
    }

    public String revertStr(String revertSQL)
    {
        return this.strParser.revert(revertSQL);
    }

    public void parse() throws ApplicationException
    {
        sql = parseString(sql);
        sql = transformParamToDel(sql);
        if (ParamParser.hasDelParam(sql))
        {
            sql = subParser.parse(sql);
            sql = filterParser.parse(sql);
            sql = subParser.revert(sql);
        }
        sql = paramParser.paramTokenParse(sql);
        sql = revertStr(sql);
    }

    public void simpleParse() throws ApplicationException
    {
        sql = parseString(sql);
        sql = transformParam(sql);
        sql = revertStr(sql);
    }

    public String getSql()
    {
        return sql;
    }

    public void setSql(String sql)
    {
        this.sql = sql;
    }

    public void setParamElement(Element paramElement)
    {
        paramParser.setParamElement(paramElement);
    }

    public void setSVCtrl(SysVariableCtrl ctrl)
    {
        paramParser.setSVCtrl(ctrl);
    }

    public List getParamList()
    {
        return paramParser.getParamList();
    }

    public void setParamList(List paramsList)
    {
        paramParser.setParamList(paramsList);
    }
}
