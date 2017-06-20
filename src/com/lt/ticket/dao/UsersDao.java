package com.lt.ticket.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface UsersDao {

	public com.lt.ticket.entity.Users login(String userid,String password,Connection myconn) throws SQLException;
	
	public int update(String userid,String password,Connection myconn);
}
