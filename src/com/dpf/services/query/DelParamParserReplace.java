package com.dpf.services.query;

import java.util.regex.Matcher;

public class DelParamParserReplace extends ReplaceStr
{
    private boolean isDel = false;

    private int     index = 0;

    public String replace(Matcher m)
    {
        String str = m.group();
        if (ParamParser.hasDelParam(str))
        {
            str = "";
            isDel = true;
        }
        else
        {
            if (index == 0 && isDel)
            {
                str = m.group(1);
            }
            index++;
            isDel = false;
        }
        return str;
    }

}