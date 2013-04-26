package com.dpf.services.query;

import java.util.regex.Matcher;

public class FilterParserReplace extends ReplaceStr
{
    public String replace(Matcher m)
    {
        String filterOperator = m.group(1);
        String str = m.group(2);
        str = DelParamParser.parse(str);
        if (!str.equals(""))
        {
            StringBuffer buf = new StringBuffer();
            buf.append(filterOperator).append(str);
            str = buf.toString();
        }
        return str;
    }
}
