package com.dpf.util;

import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

/**
 * json数据转换工具类
 *
 */
public class JsonUtil {
	static final Logger LOGGER = Logger.getLogger(JsonUtil.class);	
	private static JsonUtil jsonUtil = new JsonUtil();
	public static JsonUtil getInstance(){
		return jsonUtil;
	}
	
	/**
	 * map转json
	 * @param map
	 * @return
	 */
	public static String mapToJson(Map map){
		JSONObject json = JSONObject.fromObject(map);
		return json.toString();
	}
	
	/**
	 * list转json
	 * @param list
	 * @return
	 */
	public static String listToJson(List list){
		JSONArray json = JSONArray.fromObject(list);
		return json.toString();
	}
	
	/**
	 * object转json
	 * @param list
	 * @return
	 */
	public static String objectToJson(Object obj){
		JSONArray json = JSONArray.fromObject(obj);
		return json.toString();
	}
	
}
