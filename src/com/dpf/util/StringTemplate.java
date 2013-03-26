package com.dpf.util;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * <p>
 * company：
 * </p>
 * <Description>描述:</p>
 * <p>
 * create_time： 2013-3-25 下午4:00:04
 * </p>
 * 
 * @author songxl
 * @version ver 1.0
 * @since jdk1.6
 */
public class StringTemplate
{
    private final String         template;

    private static final Pattern REG = Pattern.compile("\\{\\$(\\w+)\\}");

    private final boolean        isReplaceNull;

    public static void appendReplacement(Matcher m, StringBuffer sbuf,
            String replacement)
    {
        m.appendReplacement(sbuf, "");
        sbuf.append(replacement);
    }

    public StringTemplate(String template, boolean isReplaceNull)
    {
        this.template = template;
        this.isReplaceNull = isReplaceNull;
    }

    public StringTemplate(String[] template, boolean isReplaceNull)
    {
        StringBuffer sbuf = new StringBuffer("");
        for (int i = 0, len = template.length; i < len; i++)
        {
            sbuf.append(template[i]);
        }
        this.template = sbuf.toString();
        this.isReplaceNull = isReplaceNull;
    }

    public StringTemplate(String template)
    {
        this(template, true);
    }

    public StringTemplate(String[] template)
    {
        this(template, false);
    }

    public String apply(Map attrs)
    {
        Matcher m = REG.matcher(this.template);
        StringBuffer sbuf = new StringBuffer("");
        String attr;
        while (m.find())
        {
            attr = (String) attrs.get(m.group(1));
            if (attr != null)
            {
                appendReplacement(m, sbuf, attr);
            }
            else if (this.isReplaceNull)
            {
                m.appendReplacement(sbuf, "");
            }
        }
        m.appendTail(sbuf);
        return sbuf.toString();
    }

    public static String getToken(String name)
    {
        StringBuffer sbuf = new StringBuffer("{$");
        sbuf.append(name).append("}");
        return sbuf.toString();
    }
}
