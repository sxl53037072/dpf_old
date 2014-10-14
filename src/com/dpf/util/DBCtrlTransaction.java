package com.dpf.util;

import java.sql.Connection;


/**
 * <p>
 * company：
 * </p>
 * <Description>描述:执行事务接口</p>
 * <p>
 * create_time： 2013-6-17 下午3:50:01
 * </p>
 * 
 * @author songxl
 * @version ver 1.0
 * @since jdk1.6
 */
public interface DBCtrlTransaction {
	/**
	 * 回调函数
	 */
	public void callback(Connection conn) throws Exception;
}
