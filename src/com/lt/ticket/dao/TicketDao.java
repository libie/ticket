package com.lt.ticket.dao;

import java.sql.Connection;
import java.util.List;

import com.lt.ticket.entity.KhsxFenlei;
import com.lt.ticket.entity.Ticket;

public interface TicketDao {

	/**
	 * ����Ʊ��
	 */
	public int insert(Ticket ticket,Connection myconn);
	
	public List<Ticket> getAll(Connection myconn);
	
	public int delete(String tph,Connection myconn);
	
	public int update(String ph,Ticket ticket,Connection myconn);
	
	public List<Ticket> search(Ticket ticket,Connection myconn);
	
	/**
	 * ���ݴ�����տ�������飬���ظ������տ�Ľ��
	 * return list<String[���,����]>
	 */
	public List<String[]> getFenLeiZongJinE(List<String> fenleis,Connection myconn);
	
	/**
	 * ���ݴ����Ʊ�ַ������飬���ظ�����Ʊ���������
	 * return list<String[Ʊ��,����]>
	 */
	public List<String[]> getFenLeiPiaoShu(List<String> fenleis,Connection myconn);
	
	
	/**
	 * ���ݴ�����տ�����������ѯʱ�䣬���ظ������տ�Ľ��
	 * return list<String[���,����]>
	 */
	public List<String[]> getFenLeiZongJinE(List<String> fenleis,String startDate,String endDate,Connection myconn);
	
	/**
	 * ���ݴ����Ʊ�ַ����������ѯʱ�䣬���ظ�����Ʊ���������
	 * return list<String[Ʊ��,����]>
	 */
	public List<String[]> getFenLeiPiaoShu(List<String> fenleis,String startDate,String endDate,Connection myconn);
	
	/**
	 * ���ݿͻ�ID��ѯƱ����Ϣ
	 * @param cid	�ͻ�ID
	 * @return	Ʊ����Ϣ�б�
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
