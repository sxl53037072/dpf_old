package com.dpf.services.query;

import java.util.HashMap;
import java.util.Map;

public class SysVariableFactroy
{
    static Map ObjectMap = new HashMap();

    static
    {
        ObjectMap.put("CURRENT_STAFF_ID", new GetCurrentStaff());
//        ObjectMap.put("CURRENT_ORG_ID", new GetCurrentOrg());
        ObjectMap.put("TODAY", new GetToday());
    }

    static public synchronized SysVariableValue newInstance(String key)
    {
        return (SysVariableValue) ObjectMap.get(key.toUpperCase());
    }
}
