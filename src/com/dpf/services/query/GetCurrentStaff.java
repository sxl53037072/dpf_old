package com.dpf.services.query;

import javax.servlet.http.HttpSession;

public class GetCurrentStaff extends SysVariableValue
{

    protected String getType(HttpSession session)
    {
        return ParamInfo.INT_TYPE;
    }

    protected String getValue(HttpSession session)
    {
        //StaffInfo info = (StaffInfo) session.getAttribute("staffInfo");
        //return Integer.toString(info.getStaffId());
    	return "111";
    }

}
