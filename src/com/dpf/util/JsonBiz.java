package com.dpf.util;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.dpf.domain.Paging;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/***
 * json相关处理类
 *
 */
public class JsonBiz {
	public static Logger log = Logger.getLogger(JsonBiz.class);
	/**
	 * 多条取数（分页）
	 * @param list
	 * @param iRecords
	 * @return
	 */
	public static JSONObject getJsonDataForPage(List<?> list,int iRecords,int pageSize){
		JSONObject result = new JSONObject();
		result.put("succeed", true);
		try{
			JSONObject dataObj = new JSONObject();
			dataObj.put("total", iRecords);
			dataObj.put("pagesize", pageSize);
			
			JSONArray dataArr = new JSONArray();
			if(list != null){
				for(Object o :list){
					if(o != null){
						Class  c=o.getClass();
						if(c.isArray() || o instanceof List){
							dataArr.add(JSONArray.fromObject(o));
						}else{
							dataArr.add(JSONObject.fromObject(o));
						}
					}
				}
			}
			dataObj.put("rows", dataArr);
			result.put("data", dataObj);
		}catch(Exception e){
			log.error(e.getMessage(),e);
			result.put("succeed", false);
			result.put("error","出错");
			result.put("error_detail",e.getMessage());
		}		
		return result;
	}
	/**
	 * 单条取数
	 * @param object
	 * @return
	 */
	public static JSONObject getJsonDataForOneRecord(Object object){

		JSONObject result = new JSONObject();
		result.put("succeed", true);
		try{
			if(object != null){
				Class  c=object.getClass();
				if(c.isArray() || object instanceof List){//数组类型
					result.put("data",JSONArray.fromObject(object));		
				}else{
					result.put("data",JSONObject.fromObject(object));	
				}
			}
		}catch(Exception e){
			log.error(e.getMessage(),e);
			result.put("succeed", false);
			result.put("error","出错");
			result.put("error_detail",e.getMessage());
		}
		return  result;
	}
	/**
	 * 多条数据（不分页）
	 * @param object
	 * @return
	 */
	public static JSONObject getJsonDataForOption(List<?>list){	
		JSONObject result = new JSONObject();
		result.put("succeed", true);
		try{
			JSONObject dataObj = new JSONObject();
			JSONArray dataArr = new JSONArray();
			
            if(list != null){	
				for(Object o :list){
					if(o != null){
						Class  c=o.getClass();
						if(c.isArray() || c.isInstance(List.class)){
							dataArr.add(JSONArray.fromObject(o));
						}else{
							dataArr.add(JSONObject.fromObject(o));
						}
					}
				}
			}
            dataObj.put("list", dataArr);
            result.put("data", dataObj);
            
		}catch(Exception e){
			log.error(e.getMessage(),e);
			result.put("succeed", false);
			result.put("error","出错");
			result.put("error_detail",e.getMessage());
		}		
		return  result;
	}
	/**
	 * 报错数据
	 * @param e
	 * @return
	 */
	public static JSONObject getJsonError(Exception e){
		return JSONObject.fromObject("{\"succeed\":false,\"error\":\"出错\",\"error_detail\":\""+e.getMessage()+"\"}");
	}
	/**
	 * 运行结果
	 * @param b
	 * @return
	 */
	public static JSONObject  isSucceed(boolean b,String msg){
		if(b==true)
		     return JSONObject.fromObject("{\"succeed\":true, \"data\":{\"succeed\":true,\"msg\":\""+msg+"\"}}");
		else{
			 return JSONObject.fromObject("{succeed:true, \"data\":{\"succeed\":false,\"msg\":\""+msg+"\"}}}");
		}
	}
	
	public static JSONObject returnJsonToPage() {
		return JSONObject.fromObject("{\"succeed\":true}");
	}
	/**
	 * 多条取数（easyui分页）
	 * @param list
	 * @param iRecords
	 * @return
	 */
	public static JSONObject getJsonDataForEasyUi(List<?> list,int iRecords){
		JSONObject result = new JSONObject();
		result.put("total", iRecords);
		try{
			JSONArray dataArr = new JSONArray();
			if(list != null){
				for(Object o :list){
					if(o != null){
						Class  c=o.getClass();
						if(c.isArray() || o instanceof List){
							dataArr.add(JSONArray.fromObject(o));
						}else{
							dataArr.add(JSONObject.fromObject(o));
						}
					}
				}
			}
			result.put("rows", dataArr);			
		}catch(Exception e){
			log.error(e.getMessage(),e);
			result.put("succeed", false);
			result.put("error","出错");
			result.put("error_detail",e.getMessage());
		}		
		return result;
	}
	
	/**
	 * 按jquery-ui-combogrid插件要求的格式返回数据
	 * @param list
	 * @param totalCount
	 * @param p
	 * @return
	 */
	public static JSONObject getJsonDataForCombogrid(List<?> list,int totalCount,Paging p){
		JSONObject returnData = getJsonDataForJQGridUi(list, totalCount, p);
		returnData.put("records", totalCount);
		returnData.put("total", returnData.get("totalPage"));
		return returnData;
	}
	
	/**
	 * 多条取数（JQGrid分页）
	 * @param list
	 * @param totalCount
	 * @param p
	 * @return
	 */
	public static JSONObject getJsonDataForJQGridUi(List<?> list,int totalCount,Paging p){
		int page = p.getPage();
		int rows = p.getRows();
		int total = 1;
		if (totalCount%rows != 0) {
			total = totalCount / rows + 1;
		}else {
			total = totalCount / rows ;
		}
		JSONObject result = new JSONObject();
		result.put("totalCount", totalCount);
		result.put("page", page);
		result.put("totalPage", total);
		try{
			JSONArray dataArr = new JSONArray();
			if(list != null){
				for(Object o :list){
					if(o != null){
						Class  c=o.getClass();
						if(c.isArray() || o instanceof List){
							dataArr.add(JSONArray.fromObject(o));
						}else{
							dataArr.add(JSONObject.fromObject(o));
						}
					}
				}
			}
			result.put("rows", dataArr);
		}catch(Exception e){
			log.error(e.getMessage(),e);
			result.put("succeed", false);
			result.put("error","出错");
			result.put("error_detail",e.getMessage());
		}		
		return result;
	}
	
	/**
	 * 多条取数（JQGrid分页）且有页脚
	 * @param list
	 * @param totalCount
	 * @param p
	 * @param listCountAll 页脚
	 * @return
	 */
	public static JSONObject getJsonDataForJQGridUi(List<?> list,int totalCount,Paging p,List<?> listCountAll){
		int page = p.getPage();
		int rows = p.getRows();
		int total = 1;
		if (totalCount%rows != 0) {
			total = totalCount / rows + 1;
		}else {
			total = totalCount / rows ;
		}
		JSONObject result = new JSONObject();
		result.put("totalCount", totalCount);
		result.put("page", page);
		result.put("totalPage", total);
		try{
			JSONArray dataArr = new JSONArray();
			if(list != null){
				for(Object o :list){
					if(o != null){
						Class  c=o.getClass();
						if(c.isArray() || o instanceof List){
							dataArr.add(JSONArray.fromObject(o));
						}else{
							dataArr.add(JSONObject.fromObject(o));
						}
					}
				}
			}
			result.put("rows", dataArr);
			if (null != listCountAll && !listCountAll.isEmpty()) {
				if (null != listCountAll.get(0)) {
					JSONObject jsonObject1 = JSONObject.fromObject(listCountAll.get(0));
					result.put("userdata", jsonObject1);
				}else {
					result.put("userdata", "");
				}
			}else {
				result.put("userdata", "");
			}
		}catch(Exception e){
			log.error(e.getMessage(),e);
			result.put("succeed", false);
			result.put("error","出错");
			result.put("error_detail",e.getMessage());
		}
		return result;
	}
}
