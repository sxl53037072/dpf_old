package com.dpf.services.query;

import javax.servlet.http.HttpSession;

public abstract class SysVariableValue
{
    protected abstract String getValue(HttpSession session);

    protected abstract String getType(HttpSession session);

    public ParamInfo getSysVariableValue(HttpSession session)
    {
        ParamInfo info = new ParamInfo();
        info.setValue(this.getValue(session));
        info.setType(this.getType(session));
        return info;
    }
}
