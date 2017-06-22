package com.lt.ticket.util;

import java.sql.*;

/**
 * 数据库操作基类
 * @author 李振虎 
 */
public class BaseDaojdbc {
	//	sqlserver2005/2008
//	public final static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // 数据库驱动
//	public final static String URL = "jdbc:sqlserver://192.168.1.44:39947;databaseName=ticket"; // url
//	public final static String DBNAME = "XX"; // 数据库用户名
//	public final static String DBPASS = "XX"; // 数据库密码
	
	//	sqlserver2000
//	public final static String DRIVER = "com.microsoft.jdbc.sqlserver.SQLServerDriver"; // 数据库驱动
//	public final static String URL = "jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=ticket"; // url
//	public final static String DBNAME = "sa"; // 数据库用户名
//	public final static String DBPASS = "XX"; // 数据库密码
	//	mysql
//	public final static String DRIVER = "com.mysql.jdbc.Driver"; // 数据库驱动
//	public final static String URL = "jdbc:mysql://localhost:3306/ticket"; // url
//	public final static String DBNAME = "root"; // 数据库用户名
//	public final static String DBPASS = ""; // 数据库密码
	
	//oracle
	public final static String DRIVER = "oracle.jdbc.driver.OracleDriver"; // 数据库驱动
	public final static String URL = "jdbc:oracle:thin:@localhost:1521:ORCL"; // url
	public final static String DBNAME = "XX"; // 数据库用户名
	public final static String DBPASS = "XX"; // 数据库密码

	/**
	 * 得到数据库连接
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @return 数据库连接
	 */
	public Connection getConn() {
		Connection conn = null;
		try {
			Class.forName(DRIVER); // 注册驱动
			conn = DriverManager.getConnection(URL, DBNAME, DBPASS); // 获得数据库连接
			conn.setAutoCommit(false);
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

	/**
	 * 执行SQL语句，可以执行查询。ResultSet,PreparedStatement,Connection未关闭
	 * 
	 * @param sql	预编译的 SQL 语句
	 * @param param	预编译的 SQL 语句中的‘？’参数的字符串数组
	 * @return		结果集
	 */
	public ResultSet executeQuerySQL(String preparedSql, String[] param) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// int num = 0;

		/* 处理SQL,执行SQL */
		try {
			conn = getConn(); // 得到数据库连接
			pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setString(i + 1, param[i]); // 为预编译sql设置参数
				}
			}
			rs = pstmt.executeQuery(); // 执行SQL语句
		} catch (SQLException e) {
			e.printStackTrace(); // 处理SQLException异常
		}
		return rs;
	}
	/**
	 * 执行SQL语句，可以执行查询。自定义数据库连接,ResultSet,PreparedStatement,Connection未关闭。
	 * 
	 * @param sql	预编译的 SQL 语句
	 * @param param	预编译的 SQL 语句中的‘？’参数的字符串数组
	 * @param myconn自定义可控数据库连接
	 * @return		结果集
	 */
	public ResultSet executeQuerySQL(String preparedSql, String[] param,Connection myconn) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// int num = 0;

		/* 处理SQL,执行SQL */
		try {
			conn = myconn; // 得到数据库连接
			pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setString(i + 1, param[i]); // 为预编译sql设置参数
				}
			}
			rs = pstmt.executeQuery(); // 执行SQL语句
		} catch (SQLException e) {
			e.printStackTrace(); // 处理SQLException异常
		}
		return rs;
	}

	
	/**
	 * 执行SQL语句，可以进行增、删、改的操作，不能执行查询。 自动提交事务，自动关闭数据库连接
	 * 
	 * @param sql  		预编译的 SQL 语句
	 * @param param  	预编译的 SQL 语句中的‘？’参数的字符串数组
	 * @return  		影响的条数
	 */
	public int executeSQL(String preparedSql, Object[] param) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int num = 0;

		/* 处理SQL,执行SQL */
		try {
			conn = getConn(); // 得到数据库连接
			pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]); // 为预编译sql设置参数
				}
			}
			// System.out.println(preparedSql);
			num = pstmt.executeUpdate(); // 执行SQL语句
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace(); // 处理SQLException异常
		} finally {
			this.closeAll(conn, pstmt, null);
		}
		return num;
	}
	/**
	 * 执行SQL语句，可以进行增、删、改的操作，不能执行查询。 手动提交事务，手动关闭数据库连接
	 * 
	 * @param sql  		预编译的 SQL 语句
	 * @param param  	预编译的 SQL 语句中的‘？’参数的字符串数组
	 * @param myconn	自定义可控数据库连接
	 * @return  		影响的条数
	 */
	public int executeSQL(String preparedSql, Object[] param,Connection myconn) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int num = 0;

		/* 处理SQL,执行SQL */
		try {
			conn = myconn; // 得到数据库连接
			pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]); // 为预编译sql设置参数
				}
			}
			// System.out.println(preparedSql);
			num = pstmt.executeUpdate(); // 执行SQL语句
		} catch (SQLException e) {
			e.printStackTrace(); // 处理SQLException异常
		} finally {
			this.closeAll(null, pstmt, null);
			//this.closeAll(conn, pstmt, null);
		}
		return num;
	}
}
