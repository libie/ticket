package com.lt.ticket.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDaoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BaseDaoTest bdt = new BaseDaoTest();
		bdt.getConn();
	}
	
	public final static String DRIVER = BaseDaojdbc.DRIVER; // 数据库驱动
	public final static String URL = BaseDaojdbc.URL; // url
	public final static String DBNAME = BaseDaojdbc.DBNAME; // 数据库用户名
	public final static String DBPASS = BaseDaojdbc.DBPASS; // 数据库密码
	
	public Connection getConn() {
		Connection conn = null;
		try {
			Class.forName(DRIVER); // 注册驱动
			conn = DriverManager.getConnection(URL, DBNAME, DBPASS); // 获得数据库连接
			//conn.setAutoCommit(false);
			System.out.println(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn; // 返回连接
	}

	/**
	 * 释放资源
	 * 
	 * @param conn		数据库连接
	 * @param pstmt		PreparedStatement对象
	 * @param rs		结果集
	 */
	public void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {

		/* 如果rs不空，关闭rs */
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		/* 如果pstmt不空，关闭pstmt */
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		/* 如果conn不空，关闭conn */
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
