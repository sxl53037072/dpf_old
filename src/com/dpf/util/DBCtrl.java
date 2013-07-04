/**************************************************
 * Copyright (c) 2013. 文件名称: DBCtrl.java 摘　　要:数据连接获取
 * 
 * 当前版本: 1.0 作　　者: 宋晓灵 完成日期: 2013-4-2
 **************************************************/
package com.dpf.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import oracle.jdbc.OracleDriver;

import org.apache.log4j.Logger;

public class DBCtrl {
	private static String DB_NAME = "ds-dpf";
	private static String DB_TYPE = "ORACLE";

	private static boolean isConnectByTomcat = true;

	private static Logger logger = Logger.getLogger(DBCtrl.class.getName());
	private static String url = null;
	private static String username = null;
	private static String password = null;
	static{
		String is = PropertiesUtil.getProperty("isConnectByTomcat");
		if (!DataUtil.isNullOrEmpty(is)) {
			isConnectByTomcat = Boolean.parseBoolean(is);
		}
		String db = PropertiesUtil.getProperty("DB_NAME");
		if (!DataUtil.isNullOrEmpty(db)) {
			DB_NAME = db;
		}
	}

	// @Autowired
	// private JdbcTemplate jdbcTemplate;

	/**
	 * 功能: 获取spring本地连接
	 * 
	 * @return
	 */
	// public OracleConnection getSpringConnect(){
	// try {
	// Connection conn =
	// DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
	// conn = jdbcTemplate.getNativeJdbcExtractor().getNativeConnection(conn);
	// OracleConnection oconn = (OracleConnection)conn;
	// return oconn;
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// return null;
	// }
	/**
	 * 构造函数
	 */
	public DBCtrl() {
		
	}

	/**
	 * 获得数据库连接
	 * 
	 * @param name
	 *            String //数据库连接名
	 * @return Connection
	 * @throw java.sql.SQLException 数据库连接出错异常
	 */
	public static Connection getConnection() {
		Connection conn = null;
		if (isConnectByTomcat) {
			conn = DBCtrl.dbConnection();
			logger.debug("连接数据库成功");
		} else {

			url = PropertiesUtil.getProperty("db.url");
			username = PropertiesUtil.getProperty("db.username");
			password = PropertiesUtil.getProperty("db.password");

			try {
				if(DB_TYPE.equals("ORACLE")){
					DriverManager.registerDriver(new OracleDriver());
				}else if(DB_TYPE.equals("ACCESS")){
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  
				}
				conn = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("连接数据库失败", e);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return conn;
	}

	/**
	 * 从tomcat创建数据库连接
	 * 
	 * @return
	 */
	private static Connection dbConnection() {
		Connection myConn = null;
		try {
			Properties props = System.getProperties(); // 获得系统属性集
			String osName = props.getProperty("os.name"); // 操作系统名称
			// System.out.println("Current OS :["+osName+"]");
			// if("Windows XP".equals(osName)||"Windows 7".equals(osName)||"Windows Vista".equals(osName)){
			Context ctx = new InitialContext();
			InitialContext jndiCntx = new InitialContext();
			ctx = (Context) jndiCntx.lookup("java:comp/env");
			javax.sql.DataSource ds = (javax.sql.DataSource) ctx
					.lookup(DB_NAME);
			myConn = ds.getConnection();
			// }else{
			// javax.sql.DataSource ds = DataAccess.getDataSource();
			// //com.apex.form.DataAccess.getDataSource();
			// //DataAccess.getDataSource();
			// myConn = ds.getConnection();
			// }

			// myConn.setAutoCommit(false);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取连接失败");
			myConn = null;
		}
		return myConn;
	}

	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("关闭连接失败");
				e.printStackTrace();
			}
		}
	}

	public static void close(Connection conn, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.error("关闭连接失败");
				e.printStackTrace();
			}
		}
		close(conn);
	}
	
	public static void close(Connection conn, ResultSet rs, PreparedStatement pstmt) {
		if(pstmt != null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				logger.error("关闭连接失败");
				e.printStackTrace();
			}
		}
		close(conn, rs);
	}
	/**
	 * 功能:	事务回调接口， 可实现DBCtrlTransaction接口完成事务的处理
	 *
	 * @param transaction
	 * @throws Exception 
	 */
	public static void transactionConn(DBCtrlTransaction transaction) throws Exception{
		Connection con = null;
		try{
			con = DBCtrl.getConnection();
			con.setAutoCommit(false);
			transaction.callback(con);
			con.commit();
		}catch(SQLException e){
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBCtrl.close(con);
		}
	}
}
