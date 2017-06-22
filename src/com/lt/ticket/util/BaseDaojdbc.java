package com.lt.ticket.util;

import java.sql.*;

/**
 * ���ݿ��������
 * @author ���� 
 */
public class BaseDaojdbc {
	//	sqlserver2005/2008
//	public final static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // ���ݿ�����
//	public final static String URL = "jdbc:sqlserver://192.168.1.44:39947;databaseName=ticket"; // url
//	public final static String DBNAME = "XX"; // ���ݿ��û���
//	public final static String DBPASS = "XX"; // ���ݿ�����
	
	//	sqlserver2000
//	public final static String DRIVER = "com.microsoft.jdbc.sqlserver.SQLServerDriver"; // ���ݿ�����
//	public final static String URL = "jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=ticket"; // url
//	public final static String DBNAME = "sa"; // ���ݿ��û���
//	public final static String DBPASS = "XX"; // ���ݿ�����
	//	mysql
//	public final static String DRIVER = "com.mysql.jdbc.Driver"; // ���ݿ�����
//	public final static String URL = "jdbc:mysql://localhost:3306/ticket"; // url
//	public final static String DBNAME = "root"; // ���ݿ��û���
//	public final static String DBPASS = ""; // ���ݿ�����
	
	//oracle
	public final static String DRIVER = "oracle.jdbc.driver.OracleDriver"; // ���ݿ�����
	public final static String URL = "jdbc:oracle:thin:@localhost:1521:ORCL"; // url
	public final static String DBNAME = "XX"; // ���ݿ��û���
	public final static String DBPASS = "XX"; // ���ݿ�����

	/**
	 * �õ����ݿ�����
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @return ���ݿ�����
	 */
	public Connection getConn() {
		Connection conn = null;
		try {
			Class.forName(DRIVER); // ע������
			conn = DriverManager.getConnection(URL, DBNAME, DBPASS); // ������ݿ�����
			conn.setAutoCommit(false);
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

	/**
	 * ִ��SQL��䣬����ִ�в�ѯ��ResultSet,PreparedStatement,Connectionδ�ر�
	 * 
	 * @param sql	Ԥ����� SQL ���
	 * @param param	Ԥ����� SQL ����еġ������������ַ�������
	 * @return		�����
	 */
	public ResultSet executeQuerySQL(String preparedSql, String[] param) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// int num = 0;

		/* ����SQL,ִ��SQL */
		try {
			conn = getConn(); // �õ����ݿ�����
			pstmt = conn.prepareStatement(preparedSql); // �õ�PreparedStatement����
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setString(i + 1, param[i]); // ΪԤ����sql���ò���
				}
			}
			rs = pstmt.executeQuery(); // ִ��SQL���
		} catch (SQLException e) {
			e.printStackTrace(); // ����SQLException�쳣
		}
		return rs;
	}
	/**
	 * ִ��SQL��䣬����ִ�в�ѯ���Զ������ݿ�����,ResultSet,PreparedStatement,Connectionδ�رա�
	 * 
	 * @param sql	Ԥ����� SQL ���
	 * @param param	Ԥ����� SQL ����еġ������������ַ�������
	 * @param myconn�Զ���ɿ����ݿ�����
	 * @return		�����
	 */
	public ResultSet executeQuerySQL(String preparedSql, String[] param,Connection myconn) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// int num = 0;

		/* ����SQL,ִ��SQL */
		try {
			conn = myconn; // �õ����ݿ�����
			pstmt = conn.prepareStatement(preparedSql); // �õ�PreparedStatement����
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setString(i + 1, param[i]); // ΪԤ����sql���ò���
				}
			}
			rs = pstmt.executeQuery(); // ִ��SQL���
		} catch (SQLException e) {
			e.printStackTrace(); // ����SQLException�쳣
		}
		return rs;
	}

	
	/**
	 * ִ��SQL��䣬���Խ�������ɾ���ĵĲ���������ִ�в�ѯ�� �Զ��ύ�����Զ��ر����ݿ�����
	 * 
	 * @param sql  		Ԥ����� SQL ���
	 * @param param  	Ԥ����� SQL ����еġ������������ַ�������
	 * @return  		Ӱ�������
	 */
	public int executeSQL(String preparedSql, Object[] param) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int num = 0;

		/* ����SQL,ִ��SQL */
		try {
			conn = getConn(); // �õ����ݿ�����
			pstmt = conn.prepareStatement(preparedSql); // �õ�PreparedStatement����
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]); // ΪԤ����sql���ò���
				}
			}
			// System.out.println(preparedSql);
			num = pstmt.executeUpdate(); // ִ��SQL���
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace(); // ����SQLException�쳣
		} finally {
			this.closeAll(conn, pstmt, null);
		}
		return num;
	}
	/**
	 * ִ��SQL��䣬���Խ�������ɾ���ĵĲ���������ִ�в�ѯ�� �ֶ��ύ�����ֶ��ر����ݿ�����
	 * 
	 * @param sql  		Ԥ����� SQL ���
	 * @param param  	Ԥ����� SQL ����еġ������������ַ�������
	 * @param myconn	�Զ���ɿ����ݿ�����
	 * @return  		Ӱ�������
	 */
	public int executeSQL(String preparedSql, Object[] param,Connection myconn) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int num = 0;

		/* ����SQL,ִ��SQL */
		try {
			conn = myconn; // �õ����ݿ�����
			pstmt = conn.prepareStatement(preparedSql); // �õ�PreparedStatement����
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]); // ΪԤ����sql���ò���
				}
			}
			// System.out.println(preparedSql);
			num = pstmt.executeUpdate(); // ִ��SQL���
		} catch (SQLException e) {
			e.printStackTrace(); // ����SQLException�쳣
		} finally {
			this.closeAll(null, pstmt, null);
			//this.closeAll(conn, pstmt, null);
		}
		return num;
	}
}
