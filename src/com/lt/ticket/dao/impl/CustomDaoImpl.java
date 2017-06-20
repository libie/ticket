package com.lt.ticket.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lt.ticket.dao.CustomDao;
import com.lt.ticket.entity.Custom;
import com.lt.ticket.util.BaseDaojdbc;

public class CustomDaoImpl extends BaseDaojdbc implements CustomDao {

	BaseDaojdbc bd = new BaseDaojdbc();
	ResultSet rs = null;
	String sql;
	
	//测试方法
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		Custom c = new Custom();
//		c.setC_id("20130423000001");
//		c.setC_khsx("票号g20130005");
//		c.setC_rs(2);
//		c.setC_fkfs("现金");
//		c.setC_gpdd("否");
//		c.setC_xm("");
//		c.setC_dw("");
//		
//		
//		CustomDaoImpl it = new CustomDaoImpl();
//		try {
//			Connection myconn = new BaseDaojdbc().getConn();
//			it.insert(c,myconn);
//			it.getAll(myconn);
//			myconn.commit();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
		
//		BaseDaojdbc bd2 = new BaseDaojdbc();
//		CustomDaoImpl cdi = new CustomDaoImpl();
//		List<Custom> customs =  cdi.searchByDate("20130101","20130530",bd2.getConn());
//		for(Custom custom : customs){
//			System.out.println(custom.getC_id());
//		}
		
	}
	
	public int delete(String cid,Connection myconn) {
		sql="delete from custom where c_id='"+cid+"'";
		int flag = bd.executeSQL(sql, null,myconn);
		if(flag==1){
			System.out.println("删除客户成功！");
		}else{
			System.out.println("删除客户失败！");
		}
		return flag;
	}

	/**
	 * 查询所有客户信息
	 */
	public List<Custom> getAll(Connection myconn) {
		List<Custom> customs = new ArrayList<Custom>();
		sql = "select * from custom order by c_id desc";
		rs = bd.executeQuerySQL(sql, null,myconn);
		Custom custom = null;
		try {
			while(rs.next()){
				custom = new Custom();
				custom.setC_id(rs.getString("c_id"));
				custom.setC_khsx(rs.getString("c_khsx"));
				custom.setC_rs(rs.getInt("c_rs"));
				custom.setC_fkfs(rs.getString("c_fkfs"));
				custom.setC_gpdd(rs.getString("c_gpdd"));
				custom.setC_xm(rs.getString("c_xm"));
				custom.setC_dw(rs.getString("c_dw"));
				customs.add(custom);				
				
				//String cid = rs.getString("c_id");
				//System.out.println(cid);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customs;
	}

	/**
	 * 插入一天客户记录
	 */
	public int insert(Custom custom,Connection myconn) {
		sql = "insert into custom values(?,?,?,?,?,?,?)";
		Object[] objs = new Object[7];
		objs[0]=custom.getC_id();
		objs[1]=custom.getC_khsx();
		objs[2]=custom.getC_rs();
		objs[3]=custom.getC_fkfs();
		objs[4]=custom.getC_gpdd();
		objs[5]=custom.getC_xm();
		objs[6]=custom.getC_dw();
		int flag = bd.executeSQL(sql, objs,myconn);
		if(flag==1){
			System.out.println("插入客户成功！");
		}else{
			System.out.println("插入客户失败！");
		}
		return flag;
	}

	public List<Custom> search(Custom custom,Connection myconn) {
		List<Custom> customs = new ArrayList<Custom>();
		sql = "select * from custom where c_id='"+custom.getC_id()+"'";
		rs = bd.executeQuerySQL(sql, null, myconn);
		try {
			while(rs.next()){
				custom.setC_id(rs.getString("c_id"));
				custom.setC_khsx(rs.getString("c_khsx"));
				custom.setC_rs(rs.getInt("c_rs"));
				custom.setC_fkfs(rs.getString("c_fkfs"));
				custom.setC_gpdd(rs.getString("c_gpdd"));
				custom.setC_xm(rs.getString("c_xm"));
				custom.setC_dw(rs.getString("c_dw"));
				customs.add(custom);				
				
				//String cid = rs.getString("c_id");
				//System.out.println(cid);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customs;
	}
	
	/**
	 * 根据日期查询客户信息
	 */
	public List<Custom> searchByDate(String startdate,String enddate,Connection myconn){
		List<Custom> customs = new ArrayList<Custom>();
		sql = "select * from custom where c_id in (select t.c_id from ticket t where t.t_lrsj>=? and t.t_lrsj <=?)  order by c_id desc";
		rs = bd.executeQuerySQL(sql, new String[]{startdate,enddate},myconn);
		Custom custom = null;
		try {
			while(rs.next()){
				custom = new Custom();
				custom.setC_id(rs.getString("c_id"));
				custom.setC_khsx(rs.getString("c_khsx"));
				custom.setC_rs(rs.getInt("c_rs"));
				custom.setC_fkfs(rs.getString("c_fkfs"));
				custom.setC_gpdd(rs.getString("c_gpdd"));
				custom.setC_xm(rs.getString("c_xm"));
				custom.setC_dw(rs.getString("c_dw"));
				customs.add(custom);				
				
				//String cid = rs.getString("c_id");
				//System.out.println(cid);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customs;
		
	}

	public int update(String cid, Custom custom, Connection myconn) {
		sql = "update custom set c_id='"+custom.getC_id()+"' ";
		if(custom.getC_khsx()!=null&&!"".equals(custom.getC_khsx())){
			sql+=" ,c_khsx='"+custom.getC_khsx()+"' ";
		}
		if(custom.getC_rs()!=0&&!"".equals(custom.getC_rs())){
			sql+=" ,c_rs='"+custom.getC_rs()+"' ";
		}
		if(custom.getC_fkfs()!=null&&!"".equals(custom.getC_fkfs())){
			sql+=" ,c_fkfs='"+custom.getC_fkfs()+"' ";
		}
		if(custom.getC_gpdd()!=null&&!"".equals(custom.getC_gpdd())){
			sql+=" ,c_gpdd='"+custom.getC_gpdd()+"' ";
		}
		if(custom.getC_xm()!=null&&!"".equals(custom.getC_xm())){
			sql+=" ,c_xm='"+custom.getC_xm()+"' ";
		}
		if(custom.getC_dw()!=null&&!"".equals(custom.getC_dw())){
			sql+=" ,c_dw='"+custom.getC_dw()+"' ";
		}
		sql+=" where c_id='"+custom.getC_id()+"' ";
		int flag = bd.executeSQL(sql, null,myconn);
		if(flag==1){
			System.out.println("修改客户成功！");
		}else{
			System.out.println("修改客户失败！");
		}
		return flag;
	}

	public int getCount() {
		sql = "select count(c_id) from custom ";
		rs = bd.executeQuerySQL(sql, null);
		int count=0;
		try {
			if(rs.next()){
				count = rs.getInt(1);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("统计总客户数失败！");
		}
		return count;
	}
	
	
	/**
	 * 根据多条件查询客户信息,分页查询
	 */
	public List<Custom> searchByDate(String startdate,String enddate,String xm,String dw,String khsx,String fkfs,String gpdd,int pagestart,int pageend,Connection myconn){
		List<Custom> customs = new ArrayList<Custom>();
		sql = "select * from " +
				" (select ROW_NUMBER() OVER( order by c_id desc) as rowid ,c.* from custom c where c_id in " +
				" 	(select t.c_id from ticket t where 1=1 ";
				if(startdate!=null&&!"".equals(startdate)){
					sql+="	and t.t_lrsj>="+startdate;
				}
				if(enddate!=null&&!"".equals(enddate)){
					sql+="	and t.t_lrsj<="+enddate;
				}
		sql+=	" 	) ";
				 
				if(xm!=null&&!"".equals(xm)){
					sql+="	and c.c_xm like '%"+xm+"%'";
				}
				if(dw!=null&&!"".equals(dw)){
					sql+="	and c.c_dw like '%"+dw+"%'";
				}
				if(khsx!=null&&!"".equals(khsx)){
					sql+="	and c.c_khsx='"+khsx+"'";
				}
				if(fkfs!=null&&!"".equals(fkfs)){
					sql+="	and c.c_fkfs='"+fkfs+"'";
				}
				if(gpdd!=null&&!"".equals(gpdd)){
					sql+="	and c.c_gpdd='"+gpdd+"'";
				}
		
		sql+=	" ) as tomp WHERE rowid >= "+pagestart+" AND rowid <= "+pageend;
		rs = bd.executeQuerySQL(sql, null,myconn);
		Custom custom = null;
		try {
			while(rs.next()){
				custom = new Custom();
				custom.setC_id(rs.getString("c_id"));
				custom.setC_khsx(rs.getString("c_khsx"));
				custom.setC_rs(rs.getInt("c_rs"));
				custom.setC_fkfs(rs.getString("c_fkfs"));
				custom.setC_gpdd(rs.getString("c_gpdd"));
				custom.setC_xm(rs.getString("c_xm"));
				custom.setC_dw(rs.getString("c_dw"));
				customs.add(custom);				
				
				//String cid = rs.getString("c_id");
				//System.out.println(cid);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customs;
		
	}

}
