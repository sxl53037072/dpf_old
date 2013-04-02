/**************************************************
 * Copyright (c) 2013.
 * 文件名称: ReleaseIoResoureUtil.java
 * 摘　　要: 日志初始化工具
 *
 * 当前版本: 1.0
 * 作　　者: 宋晓灵
 * 完成日期: 2013-4-2
 **************************************************/
package com.dpf.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

public class PropertiesUtil {
	private static Properties pro;
	private static Logger logger = Logger.getLogger(PropertiesUtil.class
			.getName());

	public PropertiesUtil() {
	}

	/**
	 * @param key
	 *            <P>
	 *            从配置文件中获取键值
	 */
	public static String getProperty(String key) {
		String property = null;
		// 统一配置文件地址
		FileInputStream in = null;
		try {
			in = new FileInputStream(PropertiesUtil.class.getClassLoader()
					.getResource("config.properties").getPath());
			pro = new Properties();
			pro.load(in);
			property = pro.getProperty(key);
			if (property != null) {
				return new String(property.getBytes("ISO-8859-1"), "UTF-8");
			} else {
				return null;
			}
		} catch (FileNotFoundException e) {
			// TODO 自动生成 catch
			logger.error("没有找到config.properties配置文件", e);

		} catch (IOException e) {
			// TODO 错误日志
			logger.error("读取config.properties配置文件异常", e);
		} finally {
			ReleaseIoResoureUtil.closeInputStream(logger, in);
		}
		return property;

	}

}
