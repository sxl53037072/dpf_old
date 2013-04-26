package com.dpf.services.query;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dpf.util.StringTemplate;

public class ParserUtils
{
    static Pattern getPatternByToken(String token)
    {
        StringBuffer sbuf = new StringBuffer(token);
        sbuf.append("(-?\\d+)");
        return Pattern.compile(sbuf.toString());
    }

    static boolean find(Pattern p, String str)
    {
        Matcher m = p.matcher(str);
        return m.find();
    }

    static String getReplaceToken(String token, String cache, List cacheList)
    {
        int index = cacheList.size();
        StringBuffer sbuf = new StringBuffer(token);
        sbuf.append(index);
        cacheList.add(cache);
        return sbuf.toString();
    }

    public static String replace(Pattern p, String str, ReplaceStr replaceStr)
    {
        Matcher m = p.matcher(str);
        StringBuffer sbuf = new StringBuffer("");
        String token;
        replaceStr.onBeforeReplace();
        while (m.find())
        {
            token = replaceStr.replaceStr(m);
            StringTemplate.appendReplacement(m, sbuf, token);
        }
        m.appendTail(sbuf);
        return sbuf.toString();
    }

    static String revert(String token, String str, List list)
    {
        Pattern p = getPatternByToken(token);
        RevertReplace replaceStr = new RevertReplace(list);
        return revertByRecursion(p, replaceStr, str);
    }

    private static String revertByRecursion(Pattern p,
            RevertReplace replaceStr, String str)
    {
        str = replace(p, str, replaceStr);
        if (replaceStr.isFind())
        {
            str = revertByRecursion(p, replaceStr, str);
        }
        return str;
    }
}