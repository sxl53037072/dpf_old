/**************************************************
 * Copyright (c) 2013.
 * 文件名称: ResultConfigDao.java
 * 摘　　要: 
 *
 * 当前版本: 1.0
 * 作　　者: 宋晓灵
 * 完成日期: 2013-4-25
 **************************************************/
package com.dpf.dao.busiMonitor;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.dpf.util.CommonOutBiz;

public class ResultConfigDao {
	private static final String CONFIG_SELECT_SQL = 
		"SELECT B.SQL_ID          as \"sqlId\",\n" +
		"       B.SQL_TEXT        as \"sqlText\",\n" + 
		"       B.SQL_TYPE        as \"sqlType\",\n" + 
		"       B.CURSOR_INDEX    as \"cursorIndex\",\n" + 
		"       B.REMARK          as \"remark\",\n" + 
		"       c.toolbar_menu_id as \"toolbarMenuId\"\n" + 
		"  FROM GET_VALUE_CFG A, SQL_CFG B, GET_VALUE_SHOW_CFG C\n" + 
		" WHERE A.GET_VALUE_ID = B.SQL_ID\n" + 
		"   and a.get_value_id = c.get_value_cfg_id(+)\n" + 
		" order by sql_id";
	private static final String CONFIG_INSERT_SQL = 
			"INSERT INTO GET_VALUE_CFG(GET_VALUE_CFG_ID,GET_VALUE_TYPE,GET_VALUE_ID,REMARK)VALUES(?,?,?,?)";
	private static final String CONFIG_UPDATE_SQL = 
			"UPDATE GET_VALUE_CFG\n" +
			"SET GET_VALUE_CFG_ID = ?,GET_VALUE_TYPE = ?,GET_VALUE_ID = ?,REMARK = ?\n" + 
			"WHERE GET_VALUE_CFG_ID = ?";

	private static final String SQL_CONFIG_INSERT_SQL = 
			"INSERT INTO SQL_CFG(SQL_ID,SQL_TEXT,SQL_TYPE,CURSOR_INDEX,ID_COL,REMARK)VALUES(?,?,?,?,?,?)";	
	private static final String SQL_CONFIG_UPDATE_SQL = 
			"UPDATE SQL_CFG\n" +
			"SET SQL_ID = ?,SQL_TEXT = ?,SQL_TYPE = ?,CURSOR_INDEX = ?,ID_COL = ?,REMARK = ?\n" + 
			"WHERE SQL_ID = ?";
	
	private static final String TOOLBAR_SELECT_SQL = 
			"SELECT A.SYS_FUNC_MENU_GROUP_ID, A.REMARK FROM SYS_FUNC_MENU_GROUP A order by SYS_FUNC_MENU_GROUP_ID";
	
	private static final String TOOLBAR_DETAIL_SELECT_SQL = 
			"SELECT A.SYS_FUNC_MENU_GROUP_ID AS \"groupId\",\n" +
					"       C.ITEM_LABEL AS \"itemLabel\",\n" + 
					"       C.EVENT AS \"event\",\n" + 
					"       C.Ico as \"ico\",\n" + 
					"       decode(c.is_line, '0BT', '是', '否') as \"isLineName\",\n" + 
					"       c.is_line as \"isLine\",\n" + 
					"       c.sort_id as \"sortId\",\n" + 
					"       c.remark as \"remark\",\n" + 
					"       a.import_js as \"importJs\",\n" + 
					"       b.menu_name_cn as \"menuNameCn\"\n" + 
					"  FROM SYS_FUNC_MENU_GROUP A, SYS_FUNC_MENU B, SYS_FUNC_MENU_ITEM C\n" + 
					" WHERE A.SYS_FUNC_MENU_GROUP_ID = B.FUNC_MENU_GROUP_ID\n" + 
					"   AND B.FUNC_MENU_GROUP_ID = C.FUNC_MENU_GROUP_ID AND A.SYS_FUNC_MENU_GROUP_ID = ? order by c.sort_id";
	

	public HashMap<String, Object> getConfig(HttpServletRequest request){
		return CommonOutBiz.commonOutJqGrid(request, CONFIG_SELECT_SQL, null);
	}
	
	public void addConfig(HttpServletRequest request) throws Exception{
		CommonOutBiz.execStatement(CONFIG_INSERT_SQL, new Object[]{
			request.getParameter("sqlId"),
			"SQL",
			request.getParameter("sqlId"),
			request.getParameter("remark")
		});
		CommonOutBiz.execStatement(SQL_CONFIG_INSERT_SQL, new Object[]{
				request.getParameter("sqlId"),
				request.getParameter("sqlText"),
				request.getParameter("sqlType"),
				request.getParameter("cursorIndex"),
				request.getParameter("idCol"),
				request.getParameter("remark")
			});
	}
	
	public void editConfig(HttpServletRequest request) throws Exception{
		CommonOutBiz.execStatement(CONFIG_UPDATE_SQL, new Object[]{
			request.getParameter("sqlId"),
			"SQL",
			request.getParameter("sqlId"),
			request.getParameter("remark"),
			request.getParameter("sqlId")
		});
		CommonOutBiz.execStatement(SQL_CONFIG_UPDATE_SQL, new Object[]{
				request.getParameter("sqlId"),
				request.getParameter("sqlText"),
				request.getParameter("sqlType"),
				request.getParameter("cursorIndex"),
				request.getParameter("idCol"),
				request.getParameter("remark"),
				request.getParameter("sqlId")
			});
	}
	
	public HashMap<String, Object> toolbarList(HttpServletRequest request){
		return CommonOutBiz.commonOutJqGrid(request, TOOLBAR_SELECT_SQL, null);
	}
	
	public HashMap<String, Object> toolbarDetail(HttpServletRequest request){
		return CommonOutBiz.commonOutJqGrid(request, TOOLBAR_DETAIL_SELECT_SQL, new Object[]{request.getParameter("groupId")});
	}
}
