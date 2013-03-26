package com.dpf.services.query;

import java.util.List;

public class ReplaceParamByToken implements ReplaceParam
{
    static final String TOKEN;

    static
    {
        ParserParamToken pt = new ParserParamToken(-1);
        TOKEN = pt.getToken();
    }

    public String replace(ParamInfo paramInfo, List paramList, ParamToken pt)
    {
        return TOKEN;
    }

}
