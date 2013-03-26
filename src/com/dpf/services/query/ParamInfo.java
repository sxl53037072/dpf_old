package com.dpf.services.query;

import oracle.jdbc.OracleTypes;


public class ParamInfo
{
    public static final String INT_TYPE    = "INTEGER";

    public static final String FLOAT_TYPE  = "FLOAT";

    public static final String STRING_TYPE = "STRING";

    private Object             value;

    private int                type;

    private boolean            isMultiple  = false;

    private String             splitChar   = ",";

    public ParamInfo()
    {}

    public ParamInfo(Object value, String type)
    {
        setValue(value);
        setType(type);
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public void setType(String type)
    {
        this.type = toOracleType(type);
    }

    public Object getValue()
    {
        return value;
    }

    public void setValue(Object value)
    {
        this.value = value;
    }

    public boolean isMultiple()
    {
        return isMultiple;
    }

    public void setMultiple(String str)
    {
        if (this.value != null
            && str != null
            && (str.equals("-1") || str.equalsIgnoreCase("true") || str
                    .equalsIgnoreCase("0BT")))
        {
            this.isMultiple = true;
        }
        else
        {
            this.isMultiple = false;
        }
    }

    public void setMultiple(boolean isMultiple)
    {
        this.isMultiple = isMultiple;
    }

    public String getSplitChar()
    {
        return splitChar;
    }

    public void setSplitChar(String splitChar)
    {
        this.splitChar = splitChar;
    }

    public boolean isNull()
    {
        return (this.value == null || this.value == "");
    }

    private int toOracleType(String type)
    {
        int iReturn;
        if (type.equalsIgnoreCase(INT_TYPE))
        {
            iReturn = OracleTypes.INTEGER;
        }
        else if (type.equalsIgnoreCase(FLOAT_TYPE))
        {
            iReturn = OracleTypes.FLOAT;
        }
        else if (type.equalsIgnoreCase(STRING_TYPE))
        {
            iReturn = OracleTypes.VARCHAR;
        }
        else
        {
            iReturn = OracleTypes.VARCHAR;
        }
        return iReturn;
    }
}
