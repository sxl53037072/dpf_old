package com.dpf.services.query;

public class SqlParamToken implements ParamToken
{
    private static final String SQL_PARAM_TOKEN = "?";

    public String getToken()
    {
        return SQL_PARAM_TOKEN;
    }

}
