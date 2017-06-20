package com.lt.ticket.dao;

import java.sql.Connection;
import java.util.List;

import com.lt.ticket.entity.KhsxFenlei;
import com.lt.ticket.entity.Ticket;

public interface TicketDao {

	/**
	 * 保存票据
	 */
	public int insert(Ticket ticket,Connection myconn);
	
	public List<Ticket> getAll(Connection myconn);
	
	public int delete(String tph,Connection myconn);
	
	public int update(String ph,Ticket ticket,Connection myconn);
	
	public List<Ticket> search(Ticket ticket,Connection myconn);
	
	/**
	 * 根据传入的收款分类数组，返回各分类收款的金额
	 * return list<String[金额,分类]>
	 */
	public List<String[]> getFenLeiZongJinE(List<String> fenleis,Connection myconn);
	
	/**
	 * 根据传入的票种分类数组，返回各分类票种销售情况
	 * return list<String[票数,分类]>
	 */
	public List<String[]> getFenLeiPiaoShu(List<String> fenleis,Connection myconn);
	
	
	/**
	 * 根据传入的收款分类数组与查询时间，返回各分类收款的金额
	 * return list<String[金额,分类]>
	 */
	public List<String[]> getFenLeiZongJinE(List<String> fenleis,String startDate,String endDate,Connection myconn);
	
	/**
	 * 根据传入的票种分类数组与查询时间，返回各分类票种销售情况
	 * return list<String[票数,分类]>
	 */
	public List<String[]> getFenLeiPiaoShu(List<String> fenleis,String startDate,String endDate,Connection myconn);
	
	/**
	 * 根据客户ID查询票据信息
	 * @param cid	客户ID
	 * @return	票据信息列表
	 */
	public List<Ticket> getByCID(String cid,Connection myconn);
	
	/**
	 * 
	 * @param startdate
	 * @param enddate
	 * @param piaozhongs
	 * @param myconn
	 * @return
	 */
	public List<KhsxFenlei> searchByPiaoZhong(String startdate,String enddate,List<String> piaozhongs,List<String> fkfs,Connection myconn);
}
