package com.dpf.services.query;

import java.util.List;
import java.util.regex.Matcher;

public class ParamTokenParserReplace extends ReplaceStr
{
    private List paramList;

    private List currentParamList;

    public ParamTokenParserReplace(List paramList, List currentParamList)
    {
        this.paramList = paramList;
        this.currentParamList = currentParamList;
    }

    public String replace(Matcher m)
    {
        int i = Integer.parseInt(m.group(1));
        ParamInfo paramInfo = null;
        if (i != -1)
        {
            paramInfo = (ParamInfo) this.currentParamList.get(i);
        }
        ReplaceParam rp = new ReplaceParamBySingle();
        return rp.replace(paramInfo, paramList, new SqlParamToken());
    }

    public List getParamList()
    {
        return paramList;
    }
}
