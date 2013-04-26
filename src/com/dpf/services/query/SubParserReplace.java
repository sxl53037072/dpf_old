package com.dpf.services.query;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubParserReplace extends ReplaceStr
{
    static final String          SUB_TOKEN           = "#PARSER_TOKEN_SUB";

    static final String          DEL_PARAM_SUB_TOKEN = "#PARSER_TOKEN_DEL_PARAM_SUB";

    private static final Pattern P_OPERATOR          = Pattern
                                                             .compile(
                                                                     "(?:^|\\s+)(?:select|and|or)\\s+",
                                                                     Pattern.CASE_INSENSITIVE);

    private FilterParser         filterParser        = new FilterParser();

    private List                 subList             = new ArrayList();

    private List                 delParamSubList     = new ArrayList();

    public String replace(Matcher m)
    {
        String cache = m.group();
        String str = m.group(1);
        String sReturn = "";
        if (ParamParser.hasDelParam(str))
        {
            if (this.hasOperator(str))
            {
                str = filterParser.parse(str);
                if (!filterParser.isFind())
                {
                    str = DelParamParser.parse(str);
                    if (str.equals(""))
                    {
                        sReturn = ReplaceParamByToken.TOKEN;
                    }
                }
                if (!str.equals(""))
                {
                    StringBuffer buf = new StringBuffer("");
                    buf.append("(").append(str).append(")");
                    sReturn = this.getSubToken(buf.toString());
                }
            }
            else
            {
                sReturn = this.getDelParamSubToken(cache);
            }
        }
        else
        {
            sReturn = this.getSubToken(cache);
        }
        return sReturn;
    }

    public List getSubList()
    {
        return subList;
    }

    public List getDelParamSubList()
    {
        return delParamSubList;
    }

    private String getSubToken(String cache)
    {
        return ParserUtils.getReplaceToken(SUB_TOKEN, cache, this.subList);
    }

    private String getDelParamSubToken(String cache)
    {
        return ParserUtils.getReplaceToken(DEL_PARAM_SUB_TOKEN, cache,
                this.delParamSubList);
    }

    private boolean hasOperator(String str)
    {
        return ParserUtils.find(P_OPERATOR, str);
    }

}
