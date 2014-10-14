
package com.dpf.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

/**
 * 
 * JSON对象输出操作类
 * 
 */
public class CommonOutBiz { 
	private static final Logger LOGGER = Logger.getLogger(CommonOutBiz.class);

	public static final List<String> colorList = new ArrayList<String>();
	
	public static final String base_color = "ff0000";
	public static final String blue_base_color = "0000ff";
	
	static{
		colorList.add("#ff8400");
		colorList.add("#bd00e0");
		colorList.add("#ffd800");
		colorList.add("#00baff");
		colorList.add("#d72229");
		colorList.add("#a16600");
		colorList.add("#560099");
		colorList.add("#d4ff78");
		colorList.add("#ef0097");
//		colorList.add("ff1493");
//		colorList.add("41690");
//		colorList.add("c71585");
//		colorList.add("ffd700");
//		colorList.add("483d8b");
//		colorList.add("ff00ff");
//		colorList.add("0000cd");
//		colorList.add("f4a460");
//		colorList.add("6a5acd"); 
//		colorList.add("8b0000");
//		colorList.add("ff6347");
//		colorList.add("4b0082");
//		colorList.add("ff8c00");
	}
	
	
	/**
	 * 把封装bean的list转换成数据形式
	 * 
	 * @param list
	 * @return
	 */
	public String[][] beanListToArray(List list, Class clazz) {
		String[][] array = new String[0][0];
		if (null != list && !list.isEmpty()) {
			Field[] field = clazz.getDeclaredFields();
			array = new String[list.size()][field.length];
			int i = 0;
			for (Iterator it = list.iterator(); it.hasNext();) {
				Object obj = it.next();
				for (int j = 0; j < field.length; j++) {
					field[j].setAccessible(true);
					try {
						array[i][j] = field[j].get(obj).toString();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				i++;
			}
		}
		return array;
	}

	public JSONArray beanListToJSONArray(List list, Class clazz) {
		JSONArray array = new JSONArray();
		if (null != list && !list.isEmpty()) {
			Field[] field = clazz.getDeclaredFields();
			for (Iterator it = list.iterator(); it.hasNext();) {
				JSONArray jsonArr = new JSONArray();
				Object obj = it.next();
				for (int j = 0; j < field.length; j++) {
					field[j].setAccessible(true);
					try {
						jsonArr.add(null == field[j].get(obj) ? "" : field[j]
								.get(obj).toString());
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				array.add(jsonArr);
			}
		}
		return array;
	}

	/**
	 * 以object形式输出到前端页面
	 * 
	 * @param response
	 * @param map
	 */
	public void commonOutObject(HttpServletResponse response, Object object) {
		try {
			response.setContentType("text/html;charset=GBK");
			PrintWriter out = response.getWriter();
			String json = JsonUtil.objectToJson(object);
			out.print(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			LOGGER.error("以object形式输出到前端页面出错,错误信息：" + e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * 以map形式输出到前端页面
	 * 
	 * @param response
	 * @param map
	 */
	public void commonOutMap(HttpServletResponse response,Map map) {
		try {
			response.setContentType("text/html;charset=GBK");
			PrintWriter out = response.getWriter();
			String json = JsonUtil.mapToJson(map);
			out.print(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			LOGGER.error("以map形式输出到前端页面出错,错误信息：" + e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * 以list形式输出到前端页面
	 * 
	 * @param response
	 * @param list
	 */
	public void commonOutList(HttpServletResponse response, List list) {
		try {
			response.setContentType("text/html;charset=GBK");
			PrintWriter out = response.getWriter();
			String json = JsonUtil.listToJson(list);
			out.print(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			LOGGER.error("以list形式输出到前端页面,错误信息：" + e.getMessage());
			e.printStackTrace();
		}
	}


	/**
	 * 
	 * @Title: commonOutExcel
	 * @Description: TODO 把查询结果导出至EXCEL
	 * @param @param response
	 * @param @param querySelect
	 * @param @param queryParam
	 * @param @param excelName
	 * @param @param columnName 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void commonOutExcel(HttpServletResponse response,
			String querySelect, Object[] queryParam, String excelFile) {
		DBCtrl dbUtils = new DBCtrl();
		Connection con = dbUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			// 统计总条数
			int rows = queryResultCount(con, querySelect, queryParam);
			if (rows > 0) {
				// 查询结果集
				pst = con.prepareStatement(querySelect);
				if (queryParam != null && queryParam.length > 0) {
					for (int i = 0; i < queryParam.length; i++) {
						pst.setObject(i + 1, queryParam[i]);
					}
				}
				rs = pst.executeQuery();

				OutputStream os = response.getOutputStream();// 取得输出流
				response.reset();// 清空输出流
				response.setHeader("Content-disposition",
						"attachment; filename=" + excelFile + ".xls");// 设定输出文件头
				response.setContentType("application/msexcel");// 定义输出类型
				WritableWorkbook wbook = Workbook.createWorkbook(os); // 建立excel文件
				// 开始生成主体内容
				int page = rows > 0 && rows % 65536 == 0 ? rows / 65536
						: rows / 65536 + 1;// 计算总sheet页签数
				for (int i = 0; i < page; i++) {
					String tmptitle = excelFile + i; // 标题
					WritableSheet wsheet = wbook.createSheet(tmptitle, i); // sheet名称
					// 标题行
					ResultSetMetaData rsmd = rs.getMetaData();
					int columnCount = rsmd.getColumnCount();
					for (int column = 0; column < columnCount; column++) {
						wsheet.addCell(new Label(column, 0, rsmd
								.getColumnName(column + 1)));
					}
					// 数据行
					int j = 1;
					while (rs.next() && j < 65536) {
						for (int column = 0; column < columnCount; column++) {
							wsheet.addCell(new Label(column, j, rs
									.getString(column + 1)));
						}
						j++;
					}
				}
				// 主体内容生成结束
				wbook.write(); // 写入文件
				wbook.close();
				rs.close();
				os.flush();
				os.close(); // 关闭流
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @Title: execStatement
	 * @Description: TODO 执行增，删，改语句
	 * @param @param con
	 * @param @param statement
	 * @param @param statementParam
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	public static boolean execStatement(Connection con, String statement,
			Object[] statementParam) {
		boolean result = false;
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(statement);
			if (statementParam != null && statementParam.length > 0) {
				for (int i = 0; i < statementParam.length; i++) {
					pst.setObject(i + 1, statementParam[i]);
				}
			}
			pst.execute();
			result = true;
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 
	 * @Title: execCall
	 * @Description: TODO 执行存储过程
	 * @param @param con
	 * @param @param callSelect
	 * @param @param callParam
	 * @param @return 设定文件
	 * @return List<HashMap<String,Object>> 返回类型
	 * @throws
	 */
	public static HashMap<String, Object> execCall(Connection con, String callName,
			Object[] callParam) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		CallableStatement proc = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String selectCallParam = "SELECT ARGUMENT_NAME,DATA_TYPE,IN_OUT FROM ALL_ARGUMENTS T WHERE T.OBJECT_NAME=? ORDER BY POSITION";
			pst = con.prepareStatement(selectCallParam);
			pst.setString(1, callName.toUpperCase());
			rs = pst.executeQuery();
			String call = "call " + callName + "(";
			if (null != callParam && callParam.length > 0) {
				int paramCount = callParam.length;
				for (int i = 0; i < paramCount; i++) {
					call += "?,";
				}
				call = call.substring(0, call.length() - 1);
			}
			call += ")";
			proc = con.prepareCall(call);
			int index = 0;
			List<String> returnParams = new ArrayList<String>();
			while (rs.next()) {
				if ("OUT".equals(rs.getString("IN_OUT"))) {
					int sqlType = oracle.jdbc.OracleTypes.VARCHAR;
					if (rs.getString("DATA_TYPE").indexOf("CURSOR") > -1) {
						sqlType = oracle.jdbc.OracleTypes.CURSOR;
					}
					if (rs.getString("DATA_TYPE").indexOf("NUMBER") > -1) {
						sqlType = oracle.jdbc.OracleTypes.NUMBER;
					}
					if (rs.getString("DATA_TYPE").indexOf("INTEGER") > -1) {
						sqlType = oracle.jdbc.OracleTypes.INTEGER;
					}
					if (rs.getString("DATA_TYPE").indexOf("DOUBLE") > -1) {
						sqlType = oracle.jdbc.OracleTypes.DOUBLE;
					}
					if (rs.getString("DATA_TYPE").indexOf("FLOAT") > -1) {
						sqlType = oracle.jdbc.OracleTypes.FLOAT;
					}
					proc.registerOutParameter(index + 1, sqlType);
					returnParams.add(rs.getString("ARGUMENT_NAME") + ","
							+ sqlType + "," + (index + 1));
				} else {
					proc.setObject(index + 1, callParam[index]);
				}
				index++;
			}
			proc.execute();
			if (!returnParams.isEmpty()) {
				for (Iterator<String> it = returnParams.iterator(); it
						.hasNext();) {
					String[] returnParam = it.next().split(",");
					if (returnParam[1].equals(oracle.jdbc.OracleTypes.CURSOR
							+ "")) {
						rs = (ResultSet) proc.getObject(Integer
								.parseInt(returnParam[2]));
						ResultSetMetaData rsmd = rs.getMetaData();
						int columnCount = rsmd.getColumnCount();
						List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
						while (rs.next()) {
							HashMap<String, String> map = new HashMap<String, String>();
							boolean flag = true;
							for (int i = 0; i < columnCount; i++) {
								if (rs.getString(i + 1) != null
										&& rs.getString(i + 1).indexOf(
												"没有相关数据！ORA-01403") != -1) {
									flag = false;
									break;
								} else {
									flag = true;
									map.put(rsmd.getColumnName(i + 1),
											rs.getString(i + 1));
								}
							}
							if (flag)
								list.add(map);
						}
						resultMap.put(returnParam[0], list);
					} else {
						resultMap.put(returnParam[0], proc.getObject(Integer
								.parseInt(returnParam[2])));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
				if (proc != null) {
					proc.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultMap;
	}

	/**
	 * 
	 * @Title: queryResult
	 * @Description: TODO 获取查询结果
	 * @param @param response
	 * @param @param querySelect
	 * @param @param queryParam 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static List<HashMap<String, String>> queryResult(Connection con,
			String querySelect, Object[] queryParam) {
		List<HashMap<String, String>> rows = new ArrayList<HashMap<String, String>>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(querySelect);
			if (queryParam != null && queryParam.length > 0) {
				for (int i = 0; i < queryParam.length; i++) {
					pst.setObject(i + 1, queryParam[i]);
				}
			}
			rs = pst.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<String, String>();
				for (int i = 0; i < columnCount; i++) {
					map.put(rsmd.getColumnName(i + 1), rs.getString(i + 1));
				}
				rows.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rows;
	}

	/**
	 * 
	 * @Title: queryResultCount
	 * @Description: TODO 统计查询记录条数
	 * @param @param querySelect
	 * @param @param queryParam
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws
	 */
	public static int queryResultCount(Connection con, String querySelect,
			Object[] queryParam) {
		int count = 0;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String countSql = "select count(1) from (" + querySelect + ") t";
			pst = con.prepareStatement(countSql);
			if (queryParam != null && queryParam.length > 0) {
				for (int i = 0; i < queryParam.length; i++) {
					pst.setObject(i + 1, queryParam[i]);
				}
			}
			rs = pst.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public static HashMap<String, Object> commonOutJqGrid(HttpServletRequest request, String querySelect, Object[] queryParam) {
		// 获取JqGrid的基本参数
		String page = request.getParameter("page");// 第几页
		page = DataUtil.isNullOrEmpty(page) ? "1" : page;
		String rows = request.getParameter("rows");// 第页显示几行
		rows = DataUtil.isNullOrEmpty(rows) ? "30" : rows;
		int startIndex = (Integer.parseInt(page) - 1) * Integer.parseInt(rows)
				+ 1;// 查询开始行
		int endIndex = Integer.parseInt(page) * Integer.parseInt(rows);// 查询结束行
		String sort = request.getParameter("sort");// 排序字段
		String order = request.getParameter("order");// 排序类型
		String sortOrder = "";
		if (!DataUtil.isNullOrEmpty(sort) && !DataUtil.isNullOrEmpty(order)) {
			sortOrder = " order by " + sort + " " + order;
		}

		Connection con = null;				
		String total = "0";
		List<HashMap<String, String>> dataRows = null;
		try {
			con = DBCtrl.getConnection();
			// 查询总记录条数
			total = request.getParameter("total");
			if (DataUtil.isNullOrEmpty(total)) {
				total = String.valueOf(queryResultCount(con, querySelect,
						queryParam));
			}
			// 查询记录结果集
			String querySql = "select * from (select t_1.*,rownum rn from ("
					+ querySelect + sortOrder + ") t_1 where rownum <="
					+ endIndex + ") t_2 where rn>=" + startIndex;
			dataRows = queryResult(con, querySql, queryParam);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 转化成JSON串格式
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("totalCount", total);
		resultMap.put("page", page);
		int totalPage = Integer.parseInt(total) % Integer.parseInt(rows) == 0 ? Integer
				.parseInt(total) / Integer.parseInt(rows)
				: Integer.parseInt(total) / Integer.parseInt(rows) + 1;
		if (total.equals("0"))
			totalPage = 0;
		resultMap.put("totalPage", totalPage);
		resultMap.put("rows", dataRows);
		return resultMap;
	}
	/**
	 * 
	 * @Title: commonOutJqGrid 查询SQL
	 * @Description: TODO 把查询结果组装成jqGrid的JSON格式
	 * @param @param response
	 * @param @param request
	 * @param @param querySelect
	 * @param @param queryParam 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static void commonOutJqGrid(HttpServletResponse response,
			HttpServletRequest request, String querySelect, Object[] queryParam) {
		// 获取JqGrid的基本参数
		String page = request.getParameter("page");// 第几页
		page = DataUtil.isNullOrEmpty(page) ? "1" : page;
		String rows = request.getParameter("rows");// 第页显示几行
		rows = DataUtil.isNullOrEmpty(rows) ? "30" : rows;
		int startIndex = (Integer.parseInt(page) - 1) * Integer.parseInt(rows)
				+ 1;// 查询开始行
		int endIndex = Integer.parseInt(page) * Integer.parseInt(rows);// 查询结束行
		String sort = request.getParameter("sort");// 排序字段
		String order = request.getParameter("order");// 排序类型
		String sortOrder = "";
		if (!DataUtil.isNullOrEmpty(sort) && !DataUtil.isNullOrEmpty(order)) {
			sortOrder = " order by " + sort + " " + order;
		}

		Connection con = null;
		String total = "0";
		List<HashMap<String, String>> dataRows = null;
		try {
			con = DBCtrl.getConnection();
			// 查询总记录条数
			total = request.getParameter("total");
			if (DataUtil.isNullOrEmpty(total)) {
				total = String.valueOf(queryResultCount(con, querySelect,
						queryParam));
			}
			// 查询记录结果集
			String querySql = "select * from (select t_1.*,rownum rn from ("
					+ querySelect + sortOrder + ") t_1 where rownum <="
					+ endIndex + ") t_2 where rn>=" + startIndex;
			dataRows = queryResult(con, querySql, queryParam);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 转化成JSON串格式
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("totalCount", total);
		resultMap.put("page", page);
		int totalPage = Integer.parseInt(total) % Integer.parseInt(rows) == 0 ? Integer
				.parseInt(total) / Integer.parseInt(rows)
				: Integer.parseInt(total) / Integer.parseInt(rows) + 1;
		if (total.equals("0"))
			totalPage = 0;
		resultMap.put("totalPage", totalPage);
		resultMap.put("rows", dataRows);
		try {
			response.setContentType("text/html;charset=GBK");
			PrintWriter out = response.getWriter();
			String json = JsonUtil.mapToJson(resultMap);
			out.print(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: commonOutJqGridByPro 查询过程
	 * @Description: TODO 把查询结果组装成jqGrid的JSON格式
	 * @param @param response
	 * @param @param request
	 * @param @param querySelect
	 * @param @param queryParam 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static void commonOutJqGridByPro(HttpServletResponse response,
			HttpServletRequest request, String pro, Object[] queryParam) {
		// 获取JqGrid的基本参数
		String page = request.getParameter("page");// 第几页
		page = DataUtil.isNullOrEmpty(page) ? "1" : page;
		String rows = request.getParameter("rows");// 第页显示几行
		rows = DataUtil.isNullOrEmpty(rows) ? "30" : rows;
		int startIndex = (Integer.parseInt(page) - 1) * Integer.parseInt(rows)
				+ 1;// 查询开始行
		int endIndex = Integer.parseInt(page) * Integer.parseInt(rows);// 查询结束行
		String sort = request.getParameter("sort");// 排序字段
		String order = request.getParameter("order");// 排序类型
		String sortOrder = "";
		if (!DataUtil.isNullOrEmpty(sort) && !DataUtil.isNullOrEmpty(order)) {
			sortOrder = " order by " + sort + " " + order;
		}

		Connection con = null;
		String total = "0";
		List<HashMap<String, String>> dataRows = new ArrayList<HashMap<String, String>>();
		try {
			con = DBCtrl.getConnection();
			// 查询总记录条数
			total = request.getParameter("total");
			// 查询记录结果集
			HashMap<String, Object> hm = execCall(con, pro, queryParam);

			Iterator it = hm.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				if (entry.getKey().equals("O_CUR")) {
					dataRows = (List<HashMap<String, String>>) entry.getValue();
				} else if (entry.getKey().equals("O_OUTCOUNT")) {
					if (DataUtil.isNullOrEmpty(total)) {
						total = String.valueOf(entry.getValue());
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (DataUtil.isNullOrEmpty(total)) {
			total = "0";
		}
		// 转化成JSON串格式
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("totalCount", total);
		resultMap.put("page", page);
		int totalPage = Integer.parseInt(total) % Integer.parseInt(rows) == 0 ? Integer
				.parseInt(total) / Integer.parseInt(rows)
				: Integer.parseInt(total) / Integer.parseInt(rows) + 1;
		if (total.equals("0"))
			totalPage = 0;
		resultMap.put("totalPage", totalPage);
		resultMap.put("rows", dataRows);
		try {
			response.setContentType("text/html;charset=GBK");
			PrintWriter out = response.getWriter();
			String json = JsonUtil.mapToJson(resultMap);
			out.print(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static String getAxisB(String name,String numberSuffix) {
		return "<axis title=\"" + name + "\" titlePos=\"left\" tickWidth=\"10\" divlineisdashed=\"1\" minValue=\"-10\" maxValue=\"12\"  numberSuffix=\""+numberSuffix+"\">\r\n";
	}
	
//	public static String getBaseAxisB(String name,String numberSuffix) {
//		return "<axis title=\"" + name + "\" titlePos=\"right\" tickWidth=\"10\" divlineisdashed=\"1\" numberSuffix=\""+numberSuffix+"\">\r\n";
//	}
	
	public static String getCategories(List<String> xData) {
		StringBuffer sb = new StringBuffer();
		sb.append("<categories>\t\r");
		for (String xValue : xData) {
			sb.append("<category label=").append("\"").append(xValue).append("\"").append("/>\r\n");
		}
		sb.append("</categories>\t\r");
		return sb.toString();
	}
	
	public static String getDataValue(Map<String, List<Map<String, String>>> idAndDataMap,
			String valueKey, List<String> cList,String hz) {
		StringBuffer sb = new StringBuffer();
		List<String> idSet = new ArrayList<String>(idAndDataMap.keySet());
		Collections.sort(idSet);
		int colorIndex = 0;
		for (String productName : idSet) {
			List<Map<String, String>> valueList = idAndDataMap.get(productName);
			sb.append("<dataset seriesName=\"" + productName + hz + "\" lineThickness=\"3\" color=\"" + colorList.get(colorIndex%colorList.size()) + "\">\t\r");
			DecimalFormat fnum = new DecimalFormat("##0.00"); 
			for (int i = 0; i < cList.size(); i++) {
				float ft =0.00f;
				for (int j = 0; j < valueList.size(); j++) {
					if(cList.get(i).equals(valueList.get(j).get("month")) && valueList.get(j).get(valueKey) != null){
						ft = Float.valueOf(valueList.get(j).get(valueKey));
						break;
					}
				}
				sb.append("<set value=").append("\"").append(fnum.format(ft)).append("\"/>\r\n");
			}
			sb.append("</dataset>\t\r");
			colorIndex++;
		}
		return sb.toString();
	}
	
	public static String getBaseDataValue(
			List<HashMap<String, String>> baseDataList, String baseName,
			String valueKey, List<String> cList) {
		StringBuffer sb = new StringBuffer();
		sb.append("<dataset seriesName=\"" + baseName + "\" lineThickness=\"3\"  color=\"" + base_color + "\">\t\r");
		DecimalFormat fnum = new DecimalFormat("##0.00"); 
		for (int i = 0; i < cList.size(); i++) {
			float ft =0.00f;
			for (int j = 0; j < baseDataList.size(); j++) {
				if(cList.get(i).equals(baseDataList.get(j).get("month")) && baseDataList.get(j).get(valueKey) != null){
					ft = Float.valueOf(baseDataList.get(j).get(valueKey));
					break;
				}
			}
			sb.append("<set value=").append("\"").append(fnum.format(ft)).append("\"/>\r\n");
		}
		sb.append("</dataset>\t\r");	
		return sb.toString();
	}
	
	public static String getClientDataValue(
			List<HashMap<String, String>> baseDataList, String baseName,
			String valueKey, List<String> cList) {
		StringBuffer sb = new StringBuffer();
		sb.append("<dataset seriesName=\"" + baseName + "\" lineThickness=\"3\"  color=\"" + blue_base_color + "\">\t\r");
		DecimalFormat fnum = new DecimalFormat("##0.00"); 
		for (int i = 0; i < cList.size(); i++) {
			StringBuffer toolText = new StringBuffer();
			float ft =0.00f;
			String monthlyProfitAndLoss = "0";
			for (int j = 0; j < baseDataList.size(); j++) {
				if(cList.get(i).equals(baseDataList.get(j).get("month")) && baseDataList.get(j).get(valueKey) != null){
					ft = Float.valueOf(baseDataList.get(j).get(valueKey));
					monthlyProfitAndLoss = baseDataList.get(j).get("monthlyProfitAndLoss") == null?"0":baseDataList.get(j).get("monthlyProfitAndLoss");
					break;
				}
			}
			toolText.append(cList.get(i)).append("客户盈亏额为")
				.append(monthlyProfitAndLoss).append("元,盈亏率为").append(ft).append("%");
			sb.append("<set value=").append("\"").append(fnum.format(ft)).append("\"").append(" toolText=\"").append(toolText).append("\"/>\r\n");
		}
		sb.append("</dataset>\t\r");
		return sb.toString();
	}
	
	
	
	/**
	 * 
	 * @Title: execStatement
	 * @Description: TODO 执行增，删，改语句
	 * @param @param con
	 * @param @param statement
	 * @param @param statementParam
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	public static boolean execStatement(String statement, Object[] statementParam)
			throws Exception {
		boolean result = false;
		PreparedStatement pst = null;
		Connection con = null;
		try {
			con = DBCtrl.getConnection();
			pst = con.prepareStatement(statement);
			if (statementParam != null && statementParam.length > 0) {
				for (int i = 0; i < statementParam.length; i++) {
					pst.setObject(i + 1, statementParam[i]);
				}
			}
			pst.execute();
			result = true;
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 
	 * @Title: queryResult 
	 * @Description: TODO 获取查询结果
	 * @param querySelect
	 * @param queryParam 设定文件
	 * @return List<HashMap<String, String>>返回类型
	 * @throws
	 */
	public static List<HashMap<String, String>> queryResult(String querySelect,
			Object[] queryParam) throws Exception {
		List<HashMap<String, String>> rows = new ArrayList<HashMap<String, String>>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = DBCtrl.getConnection();
			pst = con.prepareStatement(querySelect);
			if (queryParam != null && queryParam.length > 0) {
				for (int i = 0; i < queryParam.length; i++) {
					pst.setObject(i + 1, queryParam[i]);
				}
			}
			rs = pst.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<String, String>();
				for (int i = 0; i < columnCount; i++) {
					map.put(rsmd.getColumnName(i + 1), rs.getString(i + 1));
				}
				rows.add(map);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rows;
	}
	/**
	 * 
	 * @Title: queryResult 
	 * @Description: TODO 获取查询结果
	 * @param request 请求
	 * @param response 返回
	 * @param querySelect sql语句
	 * @param queryParam 设定文件
	 * @return List<HashMap<String, String>>返回类型
	 * @throws
	 */
	public static void queryResult(HttpServletRequest request,
			HttpServletResponse response, String querySelect, Object[] queryParam)
			throws Exception {
		List<HashMap<String, String>> list = queryResult(querySelect,
				queryParam);
    	response.setContentType("text/html;charset=GBK");  
		PrintWriter out = response.getWriter();
		out.print(JsonUtil.listToJson(list));
		out.flush();
		out.close();
	}
	/**
	 * @throws Exception 
	 * 
	 * @Title: execCall
	 * @Description: TODO 执行存储过程
	 * @param @param con
	 * @param @param callSelect
	 * @param @param callParam
	 * @param @return 设定文件
	 * @return List<HashMap<String,Object>> 返回类型
	 * @throws
	 */
	public static HashMap<String, Object> execCall(String callName,
			Object[] callParam) throws Exception {
		Connection con = null;
		try {
			con = DBCtrl.getConnection();
			return execCall(con, callName, callParam);
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			try {				
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		
	}
	public static String getErrorJson(String message) {
		if (message == null || "".equals(message))
			message = "执行操作失败!";
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("error", message);
		return JsonUtil.mapToJson(map);
	}

	public static void getErrorJson(String message, HttpServletResponse response) {
		response.setContentType("text/html;charset=GBK");
		PrintWriter out;
		try {
			out = response.getWriter();
			String json = getErrorJson(message);
			out.print(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	* @Title: TransactSQLInjection 
	* @Description: TODO 过滤掉SQL语句的';--
	* @param @param str
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String TransactSQLInjection(String str){
	    return str.replaceAll(".*([';]+|(--)+).*", " ");
	}
}
