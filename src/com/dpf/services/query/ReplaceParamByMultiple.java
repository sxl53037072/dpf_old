package com.dpf.services.query;

import java.util.List;

public class ReplaceParamByMultiple implements ReplaceParam
{
    public String replace(ParamInfo paramInfo, List paramList, ParamToken pt)
    {
        StringBuffer sbuf = new StringBuffer();
        String paramVal = (String) paramInfo.getValue();
        String[] paramVals = paramVal.split(paramInfo.getSplitChar());
        for (int i = 0; i < paramVals.length; i++)
        {
            sbuf.append(",").append(pt.getToken());
            ParamInfo newInfo = new ParamInfo();
            newInfo.setValue(paramVals[i]);
            newInfo.setType(paramInfo.getType());
            paramList.add(newInfo);
        }
        return sbuf.substring(1);
    }

}
