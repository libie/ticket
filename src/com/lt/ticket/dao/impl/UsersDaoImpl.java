package com.lt.ticket.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lt.ticket.dao.UsersDao;
import com.lt.ticket.entity.Users;
import com.lt.ticket.util.BaseDaojdbc;

public class UsersDaoImpl extends BaseDaojdbc implements UsersDao {

	BaseDaojdbc bd = new BaseDaojdbc();
	ResultSet rs = null;
	String sql;
	
	public Users login(String userid,String password,Connection myconn) throws SQLException {
		// TODO Auto-generated method stub
		sql="select * from users where u_id='"+userid+"' and u_password='"+password+"'";
		rs = bd.executeQuerySQL(sql, null,myconn);
		Users user =null;
		
		while(rs.next()){
			user = new Users();
			user.setU_id(rs.getString("u_id"));
			user.setU_password(rs.getString("u_password"));
		}
		
		return user;
	}

	public int update(String userid, String password, Connection myconn) {
		sql="update users set u_password='"+password+"' where u_id='"+userid+"'";
		int flag = bd.executeSQL(sql, null,myconn);
		if(flag==1){
			System.out.println("–ﬁ∏ƒ√‹¬Î≥…π¶£°");
		}else{
			System.out.println("–ﬁ∏ƒ√‹¬Î ß∞‹£°");
		}
		return flag;
	}

}
