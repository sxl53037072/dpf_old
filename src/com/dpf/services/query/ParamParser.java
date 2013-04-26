package com.dpf.services.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.dom4j.Element;

import com.dpf.util.StringTemplate;

public class ParamParser
{
    static final String                 PARAM              = ":";

    static final String                 VARIABLE           = "&";

    private static final StringTemplate REG_TEXT           = new StringTemplate(
                                                                   "([{$param}{$variable}])(\\w+)");

    private static final StringTemplate DEL_PARAM_REG_TEXT = new StringTemplate(
                                                                   "{$del_param_token}|{$del_param_sub_token}\\d+");

    private static final Pattern        PARSE_REG;

    private static final Pattern        PARAM_TOKEN_PARSE_REG;

    private static final Pattern        DEL_PARAM_REG;

    static
    {
        Map attrs = new HashMap();
        attrs.put("param", PARAM);
        attrs.put("variable", VARIABLE);
        PARSE_REG = Pattern.compile(REG_TEXT.apply(attrs));
        PARAM_TOKEN_PARSE_REG = ParserUtils
                .getPatternByToken(ParserParamToken.TOKEN);
        attrs = new HashMap();
        attrs.put("del_param_token", ReplaceParamByToken.TOKEN);
        attrs.put("del_param_sub_token", SubParserReplace.DEL_PARAM_SUB_TOKEN);
        DEL_PARAM_REG = Pattern.compile(DEL_PARAM_REG_TEXT.apply(attrs));
    }

    private Element                     paramElement;

    private SysVariableCtrl             sVCtrl;

    private List                        paramList;

    private List                        currentParamList;

    public String parseToToken(String str)
    {
        ParamParserReplace replaceStr = new ParamParserReplace(paramElement,
                sVCtrl);
        str = ParserUtils.replace(PARSE_REG, str, replaceStr);
        this.currentParamList = replaceStr.getParamList();
        return str;
    }

    public String parseToSQL(String str)
    {
        ParamParserReplace replaceStr = new ParamParserReplace(paramElement,
                sVCtrl, this.getParamList());
        str = ParserUtils.replace(PARSE_REG, str, replaceStr);
        this.paramList = replaceStr.getParamList();
        return str;
    }

    public String paramTokenParse(String str)
    {
        ParamTokenParserReplace replaceStr = new ParamTokenParserReplace(this
                .getParamList(), this.currentParamList);
        str = ParserUtils.replace(PARAM_TOKEN_PARSE_REG, str, replaceStr);
        this.paramList = replaceStr.getParamList();
        return str;
    }

    public static boolean hasDelParam(String str)
    {
        return ParserUtils.find(DEL_PARAM_REG, str);
    }

    public List getParamList()
    {
        if (this.paramList == null)
        {
            this.paramList = new ArrayList();
        }
        return this.paramList;
    }

    public void setParamList(List paramList)
    {
        this.paramList = paramList;
    }

    public Element getParamElement()
    {
        return paramElement;
    }

    public void setParamElement(Element paramElement)
    {
        this.paramElement = paramElement;
    }

    public SysVariableCtrl getSVCtrl()
    {
        return sVCtrl;
    }

    public void setSVCtrl(SysVariableCtrl ctrl)
    {
        sVCtrl = ctrl;
    }
}
