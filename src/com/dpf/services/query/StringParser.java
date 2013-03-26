package com.dpf.services.query;

import java.util.List;
import java.util.regex.Pattern;

public class StringParser
{
    private static final Pattern REG = Pattern.compile("(?:'.*?')|(?:\".*?\")");

    private List                 list;

    public String parse(String str)
    {
        StringParserReplace replaceStr = new StringParserReplace();
        str = ParserUtils.replace(REG, str, replaceStr);
        list = replaceStr.getList();
        return str;
    }

    public String revert(String str)
    {
        return ParserUtils.revert(StringParserReplace.TOKEN, str, list);
    }

}
