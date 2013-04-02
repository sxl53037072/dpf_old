/**************************************************
 * Copyright (c) 2013.
 * 文件名称: TestDBCtrl.java
 * 摘　　要: 
 *
 * 当前版本: 1.0
 * 作　　者: 宋晓灵
 * 完成日期: 2013-4-2
 **************************************************/
package com.dpf.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebRoot/WEB-INF/junit.xml" })
public class TestDBCtrl {
//	@Autowired
//	private DBCtrl DBCtrl;
	
	/*@Test
	public void testGetSpringConnect(){
		Connection conn = DBCtrl.getSpringConnect();
		try {
			PreparedStatement pstm = conn.prepareStatement("select a.product_name from crmii.t_product_sell_all a");
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				System.out.println("name="+rs.getString("PRODUCT_NAME"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}*/
	@Test
	public void testGetConnect(){
		Connection conn = DBCtrl.getConnection();
		try {
			PreparedStatement pstm = conn.prepareStatement("select a.product_name from crmii.t_product_sell_all a");
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				System.out.println("name="+rs.getString("PRODUCT_NAME"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
