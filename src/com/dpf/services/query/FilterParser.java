package com.dpf.services.query;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.dpf.util.StringTemplate;

public class FilterParser
{
    private static final String         START_FILTER = "where|having|(?:left\\s+join)";

    private static final String         END_FILTER   = "order|group|Connect";

    private static final StringTemplate regText      = new StringTemplate(
                                                             "(\\s+(?:{$start})\\s+)(.*?)(?=\\s+(?:{$start}|{$end})\\s+|$)");

    private static final Pattern        REG;

    private boolean                     isFind;

    static
    {
        Map attrs = new HashMap();
        attrs.put("start", START_FILTER);
        attrs.put("end", END_FILTER);
        REG = Pattern.compile(regText.apply(attrs), Pattern.CASE_INSENSITIVE
            | Pattern.DOTALL);
    }

    public String parse(String str)
    {
        FilterParserReplace replaceStr = new FilterParserReplace();
        str = ParserUtils.replace(REG, str, replaceStr);
        this.isFind = replaceStr.isFind();
        return str;
    }

    public boolean isFind()
    {
        return isFind;
    }
}
