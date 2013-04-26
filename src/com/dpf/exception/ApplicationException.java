/**************************************************
 * Copyright (c) 2013.
 * 文件名称: ApplicationException.java
 * 摘　　要: 
 *
 * 当前版本: 1.0
 * 作　　者: 宋晓灵
 * 完成日期: 2013-3-26
 **************************************************/
package com.dpf.exception;

public class ApplicationException extends BaseException
{
    String[] errs = {};

    public ApplicationException(String errorCode)
    {
        super(errorCode);
    }

    public ApplicationException(String errorCode, Throwable ex)
    {
        super(errorCode, ex);
    }

    public ApplicationException(String errorCode, Throwable ex, String[] errs)
    {
        super(errorCode, ex);
        this.errs = errs;
    }

    public void outErrorMsg()
    {
        for (int i = 0, len = errs.length; i < len; i++)
        {
            System.out.println(errs[i]);
        }
    }
}
