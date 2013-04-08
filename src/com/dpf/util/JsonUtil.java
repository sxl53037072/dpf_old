package com.dpf.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.dpf.domain.Paging;

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
	
	public static Element jsonToXml(Map map){
		JSONObject json = JSONObject.fromObject(map);
		Iterator it = json.entrySet().iterator();
		Element paramElement = DocumentHelper.createElement("params");
		while(it.hasNext()){
			Map.Entry entry = (Map.Entry) it.next(); 
			Element param = paramElement.addElement("param");
	        param.addAttribute("name", String.valueOf(entry.getKey()));
	        param.addAttribute("type", "STRING");
	        param.setText(String.valueOf(entry.getValue()));	        
		}
		return paramElement;
	}
	public static String rsToJqGrid(ResultSet rs,int totalCount,Paging p){
		List<HashMap<String, String>> rows = new ArrayList<HashMap<String, String>>();
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<String, String>();
				for (int i = 0; i < columnCount; i++) {
					map.put(rsmd.getColumnName(i + 1), DataUtil.nullToStr(rs.getString(i + 1)));
				}
				rows.add(map);
			}
			JSONObject jsonObject = JsonBiz.getJsonDataForJQGridUi(rows, totalCount, p);
			return jsonObject.toString();			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCtrl.close(null, rs);
		}
		return null;
	}
	public static String rsToColMode(ResultSet rs){
		List<HashMap<String, String>> colMode = new ArrayList<HashMap<String, String>>();
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();  
			for (int i = 1; i <= count; i++){  
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("name", rsmd.getColumnName(i).toUpperCase());
				map.put("label", rsmd.getColumnName(i).toUpperCase());
				map.put("index", rsmd.getColumnName(i).toUpperCase());
				colMode.add(map);
			} 
			JSONObject jsonObject = JsonBiz.getJsonDataForOption(colMode);
			return jsonObject.toString();			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCtrl.close(null, rs);
		}
		return null;
	}
	
	
	public static void main(String[] args){
		HashMap map = new HashMap();
		map.put("kkk", "ee");
		map.put("oo", "bb");
		Element p = jsonUtil.jsonToXml(map);
		System.out.println("xml="+p.asXML());
	}
	
}
