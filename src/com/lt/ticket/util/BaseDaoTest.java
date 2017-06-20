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
	
	public final static String DRIVER = BaseDaojdbc.DRIVER; // ���ݿ�����
	public final static String URL = BaseDaojdbc.URL; // url
	public final static String DBNAME = BaseDaojdbc.DBNAME; // ���ݿ��û���
	public final static String DBPASS = BaseDaojdbc.DBPASS; // ���ݿ�����
	
	public Connection getConn() {
		Connection conn = null;
		try {
			Class.forName(DRIVER); // ע������
			conn = DriverManager.getConnection(URL, DBNAME, DBPASS); // ������ݿ�����
			//conn.setAutoCommit(false);
			System.out.println(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn; // ��������
	}

	/**
	 * �ͷ���Դ
	 * 
	 * @param conn		���ݿ�����
	 * @param pstmt		PreparedStatement����
	 * @param rs		�����
	 */
	public void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {

		/* ���rs���գ��ر�rs */
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		/* ���pstmt���գ��ر�pstmt */
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		/* ���conn���գ��ر�conn */
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
