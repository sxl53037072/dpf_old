package com.dpf.services.query;

import java.util.List;

public class ReplaceParamBySingle implements ReplaceParam
{

    public String replace(ParamInfo paramInfo, List paramList, ParamToken pt)
    {
        if (paramInfo == null)
        {
            paramInfo = new ParamInfo(null, ParamInfo.STRING_TYPE);
        }
        paramList.add(paramInfo);
        return pt.getToken();
    }
}
