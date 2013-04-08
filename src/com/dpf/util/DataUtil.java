package com.dpf.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.dpf.domain.Paging;

/**
 * <p>
 * company：
 * </p>
 * <Description>描述:数据加工类</p>
 * <p>
 * create_time： 2013-3-20 下午5:33:09
 * </p>
 * 
 * @author songxl
 * @version ver 1.0
 * @since jdk1.6
 */
public class DataUtil {
	
	/**
	 * 功能:	判断是否为空值
	 *
	 * @param sourceStr  字符串
	 * @return  true/false 是否为空值
	 */
	public static boolean isNullOrEmpty(String sourceStr){
		boolean isNull = false;
		if(null == sourceStr || "".equals(sourceStr.trim()) || "null".equals(sourceStr))
			isNull = true;
		return isNull;
	}

	/**
	 * 功能:	空值null 返回 ""
	 *
	 * @param sourceStr
	 * @return 空值返回""
	 */
	public static String nullToStr(String sourceStr) {
		if(null != sourceStr && !sourceStr.equals("")){
			sourceStr = sourceStr.trim();
		}
		if ((sourceStr == null) || sourceStr.equals("")
				|| sourceStr.equals("null")) {
			sourceStr = "";
		}
		return sourceStr;
	}
	
	/**
	 * 功能:	request参数集转Map
	 *
	 * @param request
	 * @return
	 */
	public static Map requestToMap(HttpServletRequest request){
		Map map = new HashMap();
		Enumeration pNames = request.getParameterNames();
		while (pNames.hasMoreElements()) {
			String name = (String) pNames.nextElement();
			String value = request.getParameter(name);
			map.put(name, value);
		}
		return map;
	}
	public static HashMap<String, String> rsToMap(ResultSet rs){
		HashMap<String, String> returnMap = new HashMap<String, String>();
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			for (int i = 0; i < columnCount; i++) {
				returnMap.put(rsmd.getColumnName(i + 1).toUpperCase(), DataUtil.nullToStr(rs.getString(i + 1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			DBCtrl.close(null, rs);			
		}
		return returnMap;	
	}
}
