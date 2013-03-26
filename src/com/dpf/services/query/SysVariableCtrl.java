package com.dpf.services.query;

import javax.servlet.http.HttpSession;

public class SysVariableCtrl
{
    private HttpSession session;

    public SysVariableCtrl(HttpSession session)
    {
        this.session = session;
    }

    public ParamInfo getVariableValueByName(String name)
    {
        SysVariableValue sv = SysVariableFactroy.newInstance(name);
        return sv.getSysVariableValue(session);
    }
}
