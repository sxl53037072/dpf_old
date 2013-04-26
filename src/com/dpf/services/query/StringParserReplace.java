package com.dpf.services.query;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class StringParserReplace extends ReplaceStr
{

    static final String TOKEN = "#PARSER_TOKEN_STR";

    private List        list  = new ArrayList();

    public String replace(Matcher m)
    {
        return ParserUtils.getReplaceToken(TOKEN, m.group(), list);
    }

    public List getList()
    {
        return list;
    }
}
