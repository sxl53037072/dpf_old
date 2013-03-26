package com.dpf.services.query;
import java.util.List;
import java.util.regex.Matcher;

public class RevertReplace extends ReplaceStr
{
    private List list;

    RevertReplace(List list)
    {
        this.list = list;
    }

    public String replace(Matcher m)
    {
        int i = Integer.parseInt(m.group(1));
        return (String) list.get(i);
    }

}