package com.dpf.services.query;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.http.HttpSession;

public class GetToday extends SysVariableValue
{
    static final SimpleDateFormat DATE_FORAMT = new SimpleDateFormat(
                                                      "yyyy-MM-dd");

    protected String getType(HttpSession session)
    {
        return ParamInfo.STRING_TYPE;
    }

    protected String getValue(HttpSession session)
    {
        Calendar cal = Calendar.getInstance();
        return DATE_FORAMT.format(cal.getTime());
    }
}
