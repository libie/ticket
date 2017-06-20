package com.lt.ticket.dao;

import java.sql.Connection;
import java.util.List;

import com.lt.ticket.entity.Custom;

public interface CustomDao {

	public int insert(Custom custom,Connection myconn);
	
	public List<Custom> getAll(Connection myconn);
	
	public int delete(String id,Connection myconn);
	
	public int update(String cid,Custom custom,Connection myconn);
	
	public List<Custom> search(Custom custom,Connection myconn);
	
	public List<Custom> searchByDate(String startdate,String enddate,Connection myconn);
	
	public int getCount();
}
