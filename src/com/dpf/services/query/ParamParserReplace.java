package com.dpf.services.query;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import org.dom4j.Element;

public class ParamParserReplace extends ReplaceStr
{
    private List            paramList;

    private Element         paramElement;

    private SysVariableCtrl sVCtrl;

    private boolean         isParserToSql;

    private ParamToken      pt;

    ParamParserReplace(Element paramElement,
            SysVariableCtrl sVCtrl,
            List paramList)
    {
        this.paramElement = paramElement;
        this.sVCtrl = sVCtrl;
        this.paramList = paramList;
        this.isParserToSql = true;
        this.pt = new SqlParamToken();
    }

    ParamParserReplace(Element paramElement, SysVariableCtrl sVCtrl)
    {
        this.paramElement = paramElement;
        this.sVCtrl = sVCtrl;
        this.paramList = new ArrayList();
        this.isParserToSql = false;
        this.pt = new ParserParamToken(0);
    }

    public String replace(Matcher m)
    {
        String paramTag, paramName;
        ParamInfo paramInfo;
        ReplaceParam rp;
        paramTag = m.group(1);
        paramName = m.group(2);
        if (paramTag.equals(ParamParser.PARAM))
        {
            paramInfo = this.getParamVal(paramName);
        }
        else
        {
            paramInfo = this.sVCtrl.getVariableValueByName(paramName);
        }
        if (paramInfo != null)
        {
            if (paramInfo.isMultiple())
            {
                rp = new ReplaceParamByMultiple();
            }
            else
            {
                rp = new ReplaceParamBySingle();
            }
        }
        else
        {
            if (this.isParserToSql)
            {
                rp = new ReplaceParamBySingle();
            }
            else
            {
                rp = new ReplaceParamByToken();
            }
        }
        return rp.replace(paramInfo, this.paramList, this.pt);
    }

    private ParamInfo getParamVal(String paramName)
    {
        Element paramElement = (Element) this.paramElement
                .selectSingleNode("param[translate(@name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')='"
                    + paramName.toLowerCase() + "']");
        ParamInfo paramInfo = null;
        if (paramElement != null && !paramElement.getText().equals(""))
        {
            paramInfo = new ParamInfo();
            paramInfo.setValue(paramElement.getText());
            paramInfo.setType(paramElement.attributeValue("type"));
            paramInfo.setMultiple(paramElement.attributeValue("isMultiple"));
        }
        return paramInfo;
    }

    public List getParamList()
    {
        return paramList;
    }

}
