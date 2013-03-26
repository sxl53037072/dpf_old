package com.dpf.services.query;

import java.util.List;
import java.util.regex.Pattern;

public class SubParser
{
    private static final Pattern P = Pattern.compile("\\(([^(]*?)\\)");

    private List                 subList;

    private List                 delParamSubList;

    public String parse(String str)
    {
        SubParserReplace replaceStr = new SubParserReplace();
        return this.parseByRecursion(str, replaceStr);
    }

    public String parseByRecursion(String str, SubParserReplace replaceStr)
    {
        str = ParserUtils.replace(P, str, replaceStr);
        if (replaceStr.isFind())
        {
            str = this.parseByRecursion(str, replaceStr);
        }
        else
        {
            subList = replaceStr.getSubList();
            delParamSubList = replaceStr.getDelParamSubList();
        }
        return str;
    }

    public String revert(String str)
    {
        str = ParserUtils.revert(SubParserReplace.SUB_TOKEN, str, this.subList);
        str = ParserUtils.revert(SubParserReplace.DEL_PARAM_SUB_TOKEN, str,
                this.delParamSubList);
        return str;
    }
}
