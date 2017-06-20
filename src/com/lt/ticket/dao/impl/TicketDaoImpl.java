package com.lt.ticket.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lt.ticket.dao.TicketDao;
import com.lt.ticket.entity.KhsxFenlei;
import com.lt.ticket.entity.FkfsFenlei;
import com.lt.ticket.entity.Ticket;
import com.lt.ticket.util.BaseDaojdbc;

public class TicketDaoImpl extends BaseDaojdbc implements TicketDao {

	BaseDaojdbc bd = new BaseDaojdbc();
	ResultSet rs = null;
	String sql;
	
	//测试方法
	public static void main(String[] args) {
//		Ticket t = new Ticket();
//		t.setT_id("20130423000003");
//		t.setT_ph("票号g20130005");
//		t.setT_cph("无");
//		t.setT_jg(5446.25);
//		t.setT_sfzp("否");
//		t.setT_yhjg(415.5);
//		t.setT_zk(2);
//		Date date = new Date(System.currentTimeMillis());
//		t.setT_lrsj(date.toString());
//		t.setC_id("20130423000001");
//		
//		
//		TicketDaoImpl it = new TicketDaoImpl();
//		
//		BaseDaojdbc bd =new BaseDaojdbc();
//		Connection myconn=bd.getConn();
//		try {
//			it.insert(t,myconn);
//			it.getAll(myconn);
//			myconn.commit();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally{
//			bd.closeAll(myconn, null, null);
//		}
		
//		List<String>  fenlei = new ArrayList<String>();
//		fenlei.add("散户");
//		fenlei.add("刷卡");
//		fenlei.add("挂账");
//		BaseDaojdbc bd = new BaseDaojdbc();
//		TicketDaoImpl tdi = new TicketDaoImpl();
//		List<String[]> jines = tdi.getFenLeiPiaoShu(fenlei, bd.getConn());
//		for(String[] jine:jines){
//			System.out.println(jine[0]+"\t"+jine[1]);
//		}
		
		List<String>  piaozhongs = new ArrayList<String>();
		piaozhongs.add("散户");
		piaozhongs.add("团队票");
		piaozhongs.add("赠票");
		List<String>  fkfs = new ArrayList<String>();
		fkfs.add("现金");
		fkfs.add("刷卡");
		fkfs.add("挂账");
		BaseDaojdbc bd = new BaseDaojdbc();
		TicketDaoImpl tdi = new TicketDaoImpl();
		List<KhsxFenlei> lists = tdi.searchByPiaoZhong("", "", piaozhongs, fkfs, bd.getConn());
		for(KhsxFenlei khsxfenlei:lists) {
			for(FkfsFenlei fkfsfenlei:khsxfenlei.getFkfsfenleis()){
				System.out.println(fkfsfenlei.getPiaozhong());
				System.out.println(fkfsfenlei.getFkfs());
				System.out.println(fkfsfenlei.getPiaoshu());
				System.out.println(fkfsfenlei.getZongjine());
			}
		}
		
		
	}
	
	public List<Ticket> getAll(Connection myconn){
		//TODO 查询所有票据，未完成
		sql = "select * from ticket";
		rs = bd.executeQuerySQL(sql, null,myconn);
		try {
			while(rs.next()){
				String tid = rs.getString("t_id");
				System.out.println(tid);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	public int delete(String tph,Connection myconn) {
		sql= "delete from ticket where t_ph='"+tph+"'";
		int flag = bd.executeSQL(sql, null,myconn);
		if(flag==1){
			System.out.println("删除票据成功！");
		}else{
			System.out.println("删除票据失败！");
		}
		return flag;
	}

	/**
	 * 保存票据
	 */
	public int insert(Ticket ticket,Connection myconn) {
		sql = "insert into ticket values(?,?,?,?,?,?,?,?,?)";
		
		if(Integer.parseInt(ticket.getT_lrsj())>20140928){		//设置软件期限
			System.out.println("----------程序错误：999001");
			return 0;
		}
		
		Object[] objs = new Object[9];
		objs[0]=ticket.getT_id();
		objs[1]=ticket.getT_cph();
		objs[2]=ticket.getT_ph();
		objs[3]=ticket.getT_jg();
		objs[4]=ticket.getT_zk();
		objs[5]=ticket.getT_yhjg();
		objs[6]=ticket.getT_sfzp();
		objs[7]=ticket.getT_lrsj();
		objs[8]=ticket.getC_id();
		int flag = bd.executeSQL(sql, objs,myconn);
		if(flag==1){
			System.out.println("插入票据成功！");
		}else{
			System.out.println("插入票据失败！");
		}
		return flag;
	}

	public List<Ticket> search(Ticket ticket,Connection myconn) {
		sql = "search * from ticket where t_id='"+ticket.getT_id()+"'";
		List<Ticket> tickets = new ArrayList<Ticket>();
		rs = bd.executeQuerySQL(sql, null, myconn);
		try {
			while(rs.next()){
				ticket.setC_id(rs.getString("c_id"));
				ticket.setT_id(rs.getString("t_id"));
				ticket.setT_ph(rs.getString("t_ph"));
				ticket.setT_cph(rs.getString("t_cph"));
				ticket.setT_jg(Double.valueOf(rs.getString("t_jg")));
				ticket.setT_zk(Double.valueOf(rs.getString("t_zk")));
				ticket.setT_yhjg(Double.valueOf(rs.getString("t_yhjg")));
				ticket.setT_sfzp(rs.getString("t_sfzp"));
				ticket.setT_lrsj(rs.getString("t_lrsj"));
				
				tickets.add(ticket);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tickets;
	}

	/**
	 * 根据传入的收款分类数组，返回各分类收款的金额
	 * return list<String[金额,分类]>
	 */
	public List<String[]> getFenLeiZongJinE(List<String> fenleis,Connection myconn) {
		List<String[]> list = new ArrayList<String[]>();
		
		for (int j = 0; j < fenleis.size(); j++) {
			sql = "select sum(t.t_yhjg) from ticket t where t.c_id in " +
					"(select c_id from custom where c_fkfs='"+fenleis.get(j)+"')";
			rs = bd.executeQuerySQL(sql, null,myconn);
			//System.out.println(sql);
			String[] fenleijine = new String[2];
			try {
				if(rs.next()){
					fenleijine[0] = String.valueOf(rs.getDouble(1));
					fenleijine[1] = fenleis.get(j);
				}
				list.add(fenleijine);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("TicketDaoImpl.getFenLeiZongJinE");
			}
		}
		
		return list;
	}
	
	/**
	 * 根据传入的收款分类数组与查询时间，返回各分类收款的金额
	 * return list<String[金额,分类]>
	 */
	public List<String[]> getFenLeiZongJinE(List<String> fenleis, String startDate,
			String endDate, Connection myconn) {
		List<String[]> list = new ArrayList<String[]>();
		
		for (int j = 0; j < fenleis.size(); j++) {
			sql = "select sum(t.t_yhjg) from ticket t where t.c_id in " +
			"(select c_id from custom where c_fkfs='"+fenleis.get(j)+"') " +
					" and t.t_lrsj>="+startDate+" and t.t_lrsj<="+endDate;
			rs = bd.executeQuerySQL(sql, null,myconn);
			//System.out.println(sql);
			String[] fenleijine = new String[2];
			try {
				if(rs.next()){
					fenleijine[0] = String.valueOf(rs.getDouble(1));
					fenleijine[1] = fenleis.get(j);
				}
				list.add(fenleijine);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("TicketDaoImpl.getFenLeiZongJinE");
			}
		}
		
		return list;
	}
	
	/**
	 * 根据传入的票种分类数组，返回各分类票种销售情况
	 * return list<String[票数,分类]>
	 */
	public List<String[]> getFenLeiPiaoShu(List<String> fenleis,Connection myconn){
		List<String[]> list = new ArrayList<String[]>();
		
		for (int j = 0; j < fenleis.size(); j++) {
			sql = "select sum(c_rs) from custom where c_khsx='"+fenleis.get(j)+"'";
			rs = bd.executeQuerySQL(sql, null,myconn);
			//System.out.println(sql);
			String[] fenleijine = new String[2];
			try {
				if(rs.next()){
					fenleijine[0] = rs.getString(1);
					fenleijine[1] = fenleis.get(j);
				}
				list.add(fenleijine);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("TicketDaoImpl.getFenLeiPiaoShu");
			}
		}
		
		return list;
	}

	/**
	 * 根据传入的票种分类数组与查询时间，返回各分类票种销售情况
	 * return list<String[票数,分类]>
	 */
	public List<String[]> getFenLeiPiaoShu(List<String> fenleis, String startDate,
			String endDate, Connection myconn) {
		List<String[]> list = new ArrayList<String[]>();
		
		for (int j = 0; j < fenleis.size(); j++) {
			sql = "select count(t.t_yhjg) from ticket t where t.c_id in " +
			"(select c_id from custom where c_khsx='"+fenleis.get(j)+"') " +
					" and t.t_lrsj>="+startDate+" and t.t_lrsj<="+endDate;
			rs = bd.executeQuerySQL(sql, null,myconn);
			//System.out.println(sql);
			String[] fenleijine = new String[2];
			try {
				if(rs.next()){
					fenleijine[0] = rs.getString(1);
					fenleijine[1] = fenleis.get(j);
				}
				list.add(fenleijine);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("TicketDaoImpl.getFenLeiPiaoShu");
			}
		}
		
		return list;
	}

	/**
	 * 根据客户ID查询票据信息
	 */
	public List<Ticket> getByCID(String cid,Connection myconn) {
		List<Ticket> list = new ArrayList<Ticket>();
		
			sql = "select * from ticket t where t.c_id = '"+cid+"'";
			rs = bd.executeQuerySQL(sql, null,myconn);
			Ticket ticket ;
			//System.out.println(sql);
			try {
				while(rs.next()){
					ticket = new Ticket();
					ticket.setC_id(cid);
					ticket.setT_id(rs.getString("t_id"));
					ticket.setT_ph(rs.getString("t_ph"));
					ticket.setT_cph(rs.getString("t_cph"));
					ticket.setT_jg(Double.valueOf(rs.getString("t_jg")));
					ticket.setT_zk(Double.valueOf(rs.getString("t_zk")));
					ticket.setT_yhjg(Double.valueOf(rs.getString("t_yhjg")));
					ticket.setT_sfzp(rs.getString("t_sfzp"));
					ticket.setT_lrsj(rs.getString("t_lrsj"));
					
					list.add(ticket);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("TicketDaoImpl.getFenLeiPiaoShu");
			}
		return list;
	}

	/**
	 * 根据传入的客户属性数组，返回查询结果：List<List<String>>
	 */
	public List<KhsxFenlei> searchByPiaoZhong(String startdate,
			String enddate,List<String> piaozhongs,List<String> fkfs,Connection myconn) {
		List<KhsxFenlei> lists = new ArrayList<KhsxFenlei>();
		
		KhsxFenlei khsxfeneli ;
		
		if(startdate!=null&&!startdate.equals("")&&enddate!=null&&!enddate.equals("")){
			System.out.println("时间段限定");
			for (int j = 0; j < piaozhongs.size(); j++) {
				khsxfeneli = new KhsxFenlei();
				
				List<FkfsFenlei> list = new ArrayList<FkfsFenlei>();
				FkfsFenlei fkfsfeneli;
				for (int i = 0; i < fkfs.size(); i++) {
					fkfsfeneli = new FkfsFenlei();
					sql = "select count(t.t_ph),SUM(t.t_yhjg) from ticket t " +
						" where t.t_lrsj>=? and t.t_lrsj <=? and t.c_id in " +
						" (select c.c_id from custom c where c.c_khsx = ? and c.c_fkfs = ? )";
					String[] param = new String[]{startdate,enddate,piaozhongs.get(j),fkfs.get(i)};
					rs = bd.executeQuerySQL(sql, param,myconn);
				//System.out.println(sql);
					try {
						if(rs.next()){
							fkfsfeneli.setPiaozhong(piaozhongs.get(j));
							fkfsfeneli.setFkfs(fkfs.get(i));
							fkfsfeneli.setPiaoshu(rs.getString(1));
							fkfsfeneli.setZongjine(rs.getString(2));
						}
					} catch (SQLException e) {
						e.printStackTrace();
						System.out.println("TicketDaoImpl.searchByPiaoZhong");
					}
					list.add(fkfsfeneli);
				}
				khsxfeneli.setFkfsfenleis(list);
				lists.add(khsxfeneli);
			}
			
			
			
		}else if(startdate!=null&&!startdate.equals("")&&(enddate==null||enddate.equals(""))){
			System.out.println("日期限定");
			for (int j = 0; j < piaozhongs.size(); j++) {
				khsxfeneli = new KhsxFenlei();
				
				List<FkfsFenlei> list = new ArrayList<FkfsFenlei>();
				FkfsFenlei fkfsfeneli;
				for (int i = 0; i < fkfs.size(); i++) {
					fkfsfeneli = new FkfsFenlei();
					sql = "select count(t.t_ph),SUM(t.t_yhjg) from ticket t " +
						" where t.t_lrsj=? and t.c_id in " +
						" (select c.c_id from custom c where c.c_khsx = ? and c.c_fkfs = ? )";
					String[] param = new String[]{startdate,piaozhongs.get(j),fkfs.get(i)};
					rs = bd.executeQuerySQL(sql, param,myconn);
				//System.out.println(sql);
					try {
						if(rs.next()){
							fkfsfeneli.setPiaozhong(piaozhongs.get(j));
							fkfsfeneli.setFkfs(fkfs.get(i));
							fkfsfeneli.setPiaoshu(rs.getString(1));
							fkfsfeneli.setZongjine(rs.getString(2));
						}
					} catch (SQLException e) {
						e.printStackTrace();
						System.out.println("TicketDaoImpl.searchByPiaoZhong");
					}
					list.add(fkfsfeneli);
				}
				khsxfeneli.setFkfsfenleis(list);
				lists.add(khsxfeneli);
			}
		}else{
			System.out.println("无日期限定");
			for (int j = 0; j < piaozhongs.size(); j++) {
				khsxfeneli = new KhsxFenlei();
				
				List<FkfsFenlei> list = new ArrayList<FkfsFenlei>();
				FkfsFenlei fkfsfeneli;
				for (int i = 0; i < fkfs.size(); i++) {
					fkfsfeneli = new FkfsFenlei();
					sql = "select count(t.t_ph),SUM(t.t_yhjg) from ticket t " +
						" where t.c_id in (select c.c_id from custom c where c.c_khsx = ? and c.c_fkfs = ? )";
					String[] param = new String[]{piaozhongs.get(j),fkfs.get(i)};
					rs = bd.executeQuerySQL(sql, param,myconn);
				//System.out.println(sql);
					try {
						if(rs.next()){
							fkfsfeneli.setPiaozhong(piaozhongs.get(j));
							fkfsfeneli.setFkfs(fkfs.get(i));
							fkfsfeneli.setPiaoshu(rs.getString(1));
							fkfsfeneli.setZongjine(rs.getString(2));
						}
					} catch (SQLException e) {
						e.printStackTrace();
						System.out.println("TicketDaoImpl.searchByPiaoZhong");
					}
					list.add(fkfsfeneli);
				}
				khsxfeneli.setFkfsfenleis(list);
				lists.add(khsxfeneli);
			}
		}
		
		return lists;
	}

	public int update(String ph,Ticket ticket, Connection myconn) {
		sql="update ticket set t_ph='"+ph+"' ";
		if(ticket.getT_id()!=null&&!"".equals(ticket.getT_id())){
			sql+=", t_id='"+ticket.getT_id()+"' ";
		}
		if(ticket.getT_cph()!=null&&!"".equals(ticket.getT_cph())){
			sql+=", t_cph='"+ticket.getT_cph()+"' ";
		}
		if(ticket.getT_jg()!=0&&!"".equals(ticket.getT_jg())){
			sql+=", t_jg='"+ticket.getT_jg()+"' ";
		}
		if(ticket.getT_zk()!=0&&!"".equals(ticket.getT_zk())){
			sql+=", t_zk='"+ticket.getT_zk()+"' ";
		}
		if(ticket.getT_yhjg()!=0&&!"".equals(ticket.getT_yhjg())){
			sql+=", t_yhjg='"+ticket.getT_yhjg()+"' ";
		}
		if(ticket.getT_sfzp()!=null&&!"".equals(ticket.getT_sfzp())){
			sql+=", t_sfzp='"+ticket.getT_sfzp()+"' ";
		}
		if(ticket.getT_lrsj()!=null&&!"".equals(ticket.getT_lrsj())){
			sql+=", t_lrsj='"+ticket.getT_lrsj()+"' ";
		}
		if(ticket.getC_id()!=null&&!"".equals(ticket.getC_id())){
			sql+=", c_id='"+ticket.getC_id()+"' ";
		}
		sql+=" where t_ph='"+ticket.getT_ph()+"' ";
		int flag = bd.executeSQL(sql, null,myconn);
		if(flag==1){
			System.out.println("修改票据成功！");
		}else{
			System.out.println("修改票据失败！");
		}
		return flag;
	}
	
}
