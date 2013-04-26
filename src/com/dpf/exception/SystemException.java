package com.dpf.exception;
/**************************************************
 * Copyright (c) 2013.
 * 文件名称: SystemException.java
 * 摘　　要: 
 *
 * 当前版本: 1.0
 * 作　　者: 宋晓灵
 * 完成日期: 2013-3-27
 **************************************************/
import java.sql.SQLException;

public class SystemException extends BaseException
{
    public SystemException(String errorCode, Throwable ex)
    {
        super(errorCode, ex);
    }

    public SystemException(String errorCode)
    {
        super(errorCode);
    }

    public SystemException(SQLException ex)
    {
        super(ex);
    }
}
