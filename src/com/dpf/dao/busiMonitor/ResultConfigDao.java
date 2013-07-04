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

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dpf.util.CommonOutBiz;
import com.dpf.util.DBCtrl;
import com.dpf.util.DBCtrlTransaction;

public class ResultConfigDao {
	/**
	 *==========================基本配置信息CRUD=================================   
	 */
	private static final String CONFIG_SELECT_SQL = 
		"SELECT B.SQL_ID          as \"sqlId\",\n" +
		"       B.SQL_TEXT        as \"sqlText\",\n" + 
		"       B.SQL_TYPE        as \"sqlType\",\n" + 
		"       B.CURSOR_INDEX    as \"cursorIndex\",\n" + 
		"       B.REMARK          as \"remark\",\n" + 
		"       c.toolbar_menu_id as \"toolbarMenuId\",\n" +
		"       (SELECT REMARK FROM SYS_FUNC_MENU_GROUP WHERE SYS_FUNC_MENU_GROUP_ID = c.toolbar_menu_id) as \"toolbarMenuName\"\n" + 
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
	
	private static final String SHOW_CFG_COUNT_SQL =
			"SELECT COUNT(1) COUNT FROM GET_VALUE_SHOW_CFG WHERE GET_VALUE_CFG_ID = ?";
	private static final String SHOW_CFG_INSERT_SQL = 
			"INSERT INTO GET_VALUE_SHOW_CFG(GET_VALUE_CFG_ID,TOOLBAR_MENU_ID)VALUES(?, ?)";
	private static final String SHOW_CFG_UPDATE_SQL = 
			"UPDATE GET_VALUE_SHOW_CFG SET TOOLBAR_MENU_ID = ? WHERE GET_VALUE_CFG_ID = ?";
	
	
	//删除配置
	private static final String SQL_PARAM_CFG_DELETE_SQL = "DELETE FROM SQL_PARAM_CFG WHERE SQL_ID IN";
	private static final String GET_VALUE_CFG_FIELD_DELETE_SQL = "DELETE FROM GET_VALUE_CFG_FIELD WHERE GET_VALUE_CFG_ID IN";
	private static final String GET_VALUE_SHOW_CFG_DELETE_SQL = "DELETE FROM GET_VALUE_SHOW_CFG WHERE GET_VALUE_CFG_ID IN";
	private static final String SQL_CFG_DELETE_SQL = "DELETE FROM SQL_CFG WHERE SQL_ID IN";
	private static final String GET_VALUE_CFG_DELETE_SQL = "DELETE FROM GET_VALUE_CFG WHERE GET_VALUE_CFG_ID IN";

	/**
	 *==========================工具栏配置信息CRUD=================================   
	 */
	private static final String TOOLBAR_SELECT_SQL = 
			"SELECT A.SYS_FUNC_MENU_GROUP_ID, A.REMARK FROM SYS_FUNC_MENU_GROUP A " +
					"   WHERE ((0 = nvl(length(?), 0) and 1 = 1) or\n" + 
					"       (0 < nvl(length(?), 0) and a.SYS_FUNC_MENU_GROUP_ID || replace(replace(A.REMARK,CHR(13),''),CHR(10),'') like '%'||?||'%'))"+
					"   AND ((0 = nvl(length(?), 0) and 1 = 1) or\n" + 
					"       (0 < nvl(length(?), 0) and a.SYS_FUNC_MENU_GROUP_ID = ?))"+
			"order by SYS_FUNC_MENU_GROUP_ID";
	
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
					"   AND B.FUNC_MENU_GROUP_ID = C.FUNC_MENU_GROUP_ID order by a.SYS_FUNC_MENU_GROUP_ID,c.sort_id";
	
	/**
	 *==========================搜索栏配置信息CRUD=================================   
	 */
	private static final String SEARCH_SELECT_SQL = 	
					"SELECT T.SQL_ID,\n" +
					"       T.SQL_PARAM_ID,\n "+		
					"       T.PARAM_NAME,\n" + 
					"       T.COMP_ID,\n" + 
					"       (SELECT COMP_LABEL FROM COMPONENT WHERE COMP_ID = T.COMP_ID) COMP_NAME,\n" +
					"       T.PARAM_TYPE,\n" + 
					"       T.DATA_TYPE,\n" + 
					"       T.IS_MULTIPLE,\n" + 
					"       T.COMP_DS,\n" + 
					"       T.COMP_CFG,\n" + 
					"       T.PARAM_LABEL,\n" + 
					"       T.SORT_ID\n" + 
					"  FROM SQL_PARAM_CFG T\n" + 
					" where t.sql_id = ?\n" + 
					" order by sort_id";
	private static final String SEARCH_INSERT_SQL = 	
					"INSERT INTO SQL_PARAM_CFG\n" +
					"  (SQL_PARAM_ID, SQL_ID, PARAM_NAME, COMP_ID, COMP_DS, COMP_CFG, PARAM_LABEL, SORT_ID)\n" + 
					"VALUES\n" + 
					"  (SEQ_SQL_PARAM_CFG.nextval, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SEARCH_UPDATE_SQL = 	
					"UPDATE SQL_PARAM_CFG\n" +
					"   SET SQL_ID      = ?,\n" + 
					"       PARAM_NAME  = ?,\n" + 
					"       COMP_ID     = ?,\n" + 
					"       COMP_DS     = ?,\n" + 
					"       COMP_CFG    = ?,\n" + 
					"       PARAM_LABEL = ?,\n" +
					"       SORT_ID = ?\n" + 
					" WHERE SQL_PARAM_ID = ?";
	private static final String SEARCH_DELETE_SQL = "DELETE FROM SQL_PARAM_CFG WHERE SQL_PARAM_ID IN "; 	


	public HashMap<String, Object> getConfig(HttpServletRequest request){
		return CommonOutBiz.commonOutJqGrid(request, CONFIG_SELECT_SQL, null);
	}
	
	private boolean toolbarIsExist(HttpServletRequest request) throws Exception{
		boolean flag = false;
		List<HashMap<String, String>> list = CommonOutBiz.queryResult(SHOW_CFG_COUNT_SQL, new Object[]{request.getParameter("sqlId")});
		if(!list.isEmpty()){
			HashMap<String, String> map = list.get(0);
			flag = map.get("COUNT").equals("1");
		}
		return flag;
	}
	
	public void addConfig(HttpServletRequest req) throws Exception {
		final HttpServletRequest request = req;
		DBCtrl.transactionConn(new DBCtrlTransaction() {
			@Override
			public void callback(Connection conn) {
				CommonOutBiz.execStatement(
						conn,
						CONFIG_INSERT_SQL,
						new Object[] { request.getParameter("sqlId"), "SQL",
								request.getParameter("sqlId"),
								request.getParameter("remark") });
				CommonOutBiz.execStatement(
						conn,
						SQL_CONFIG_INSERT_SQL,
						new Object[] { request.getParameter("sqlId"),
								request.getParameter("sqlText"),
								request.getParameter("sqlType"),
								request.getParameter("cursorIndex"),
								request.getParameter("idCol"),
								request.getParameter("remark") });
				CommonOutBiz.execStatement(
						conn,
						SHOW_CFG_INSERT_SQL,
						new Object[] { request.getParameter("sqlId"),
								request.getParameter("toolbar_menu_id") });
			}

		});
	}
	
	public void editConfig(HttpServletRequest req) throws Exception{
		final HttpServletRequest request = req;
		DBCtrl.transactionConn(new DBCtrlTransaction() {
			@Override
			public void callback(Connection conn) throws Exception {
				CommonOutBiz.execStatement(conn, CONFIG_UPDATE_SQL, new Object[]{
					request.getParameter("sqlId"),
					"SQL",
					request.getParameter("sqlId"),
					request.getParameter("remark"),
					request.getParameter("sqlId")
				});
				CommonOutBiz.execStatement(conn,SQL_CONFIG_UPDATE_SQL, new Object[]{
						request.getParameter("sqlId"),
						request.getParameter("sqlText"),
						request.getParameter("sqlType"),
						request.getParameter("cursorIndex"),
						request.getParameter("idCol"),
						request.getParameter("remark"),
						request.getParameter("sqlId")
					});
				boolean isExist = toolbarIsExist(request);
				if(isExist){
					CommonOutBiz.execStatement(conn,SHOW_CFG_UPDATE_SQL, new Object[]{				
							request.getParameter("toolbar_menu_id"),
							request.getParameter("sqlId")
						});
				}else{
					CommonOutBiz.execStatement(conn,SHOW_CFG_INSERT_SQL, new Object[]{
							request.getParameter("sqlId"),
							request.getParameter("toolbar_menu_id")
						});
				}
			}
		});
		
	}
	
	public void delConfig(HttpServletRequest req) throws Exception{
		final HttpServletRequest request = req;
		DBCtrl.transactionConn(new DBCtrlTransaction() {
			@Override
			public void callback(Connection conn) throws Exception {
				CommonOutBiz.execStatement(conn, SQL_PARAM_CFG_DELETE_SQL + "(" + request.getParameter("delIds") + ")", null );
				CommonOutBiz.execStatement(conn, GET_VALUE_CFG_FIELD_DELETE_SQL + "(" + request.getParameter("delIds") + ")", null );
				CommonOutBiz.execStatement(conn, GET_VALUE_SHOW_CFG_DELETE_SQL + "(" + request.getParameter("delIds") + ")", null );
				CommonOutBiz.execStatement(conn, SQL_CFG_DELETE_SQL + "(" + request.getParameter("delIds") + ")", null );
				CommonOutBiz.execStatement(conn, GET_VALUE_CFG_DELETE_SQL + "(" + request.getParameter("delIds") + ")", null );
			}
		});
		
	}
	
	
	public HashMap<String, Object> toolbarList(HttpServletRequest request){
		String groupId = request.getParameter("groupId");
		String searchName = request.getParameter("searchName");
		return CommonOutBiz.commonOutJqGrid(request, TOOLBAR_SELECT_SQL,  new Object[]{
				searchName,searchName,searchName,
				groupId,groupId,groupId
				
		});
	}
	
	public HashMap<String, Object> toolbarDetail(HttpServletRequest request){
		return CommonOutBiz.commonOutJqGrid(request, TOOLBAR_DETAIL_SELECT_SQL, null);
	}
	
	public HashMap<String, Object> searchList(HttpServletRequest request){
		return CommonOutBiz.commonOutJqGrid(request, SEARCH_SELECT_SQL,  new Object[]{request.getParameter("sqlId")});
	}
	public void addSearch(HttpServletRequest request) throws Exception{
		CommonOutBiz.execStatement(SEARCH_INSERT_SQL,  new Object[]{
				request.getParameter("sqlId"),
				request.getParameter("PARAM_NAME"),
				request.getParameter("COMP_ID"),
				request.getParameter("COMP_DS"),
				request.getParameter("COMP_CFG"),
				request.getParameter("PARAM_LABEL"),
				request.getParameter("SORT_ID")
		});
	}
	public void editSearch(HttpServletRequest request) throws Exception{
		CommonOutBiz.execStatement(SEARCH_UPDATE_SQL,  new Object[]{
				request.getParameter("sqlId"),
				request.getParameter("PARAM_NAME"),
				request.getParameter("COMP_ID"),
				request.getParameter("COMP_DS"),
				request.getParameter("COMP_CFG"),
				request.getParameter("PARAM_LABEL"),
				request.getParameter("SORT_ID"),
				request.getParameter("SQL_PARAM_ID")
		});
	}
	public void deleteSearch(HttpServletRequest request) throws Exception{
		CommonOutBiz.execStatement(SEARCH_DELETE_SQL + "(" + request.getParameter("delIds") +")",  null);
	}
	
	
	public List<HashMap<String, String>> selectDs(HttpServletRequest request) throws Exception{
		return CommonOutBiz.queryResult(request.getParameter("sql"), null);
	}
}
