package com.lt.ticket.entity;

public class Ticket {

	private String t_id;
	
	private String c_id;
	
	/**
	 * ���ƺ�
	 */
	private String t_cph;
	
	/**
	 * Ʊ��
	 */
	private String t_ph;
	
	/**
	 * �۸�
	 */
	private double t_jg;
	
	/**
	 * �ۿ�
	 */
	private double t_zk;
	
	/**
	 * �Żݼ۸�
	 */
	private double t_yhjg;
	
	
	/**
	 * �Ƿ�Ϊ��Ʊ
	 */
	private String t_sfzp;
	
	
	/**
	 * ¼��ʱ��
	 */
	private String t_lrsj;

	
	public String getT_id() {
		return t_id;
	}
	public void setT_id(String tId) {
		t_id = tId;
	}
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String cId) {
		c_id = cId;
	}
	/**
	 * ���ƺ�
	 */
	public String getT_cph() {
		return t_cph;
	}
	/**
	 * ���ƺ�
	 */
	public void setT_cph(String tCph) {
		t_cph = tCph;
	}
	/**
	 * Ʊ��
	 */
	public String getT_ph() {
		return t_ph;
	}
	/**
	 * Ʊ��
	 */
	public void setT_ph(String tPh) {
		t_ph = tPh;
	}
	/**
	 * �۸�
	 */
	public double getT_jg() {
		return t_jg;
	}
	/**
	 * �۸�
	 */
	public void setT_jg(double tJg) {
		t_jg = tJg;
	}
	/**
	 * �ۿ�
	 */
	public double getT_zk() {
		return t_zk;
	}
	/**
	 * �ۿ�
	 */
	public void setT_zk(double tZk) {
		t_zk = tZk;
	}
	/**
	 * �Żݼ۸�
	 */
	public double getT_yhjg() {
		return t_yhjg;
	}
	/**
	 * �Żݼ۸�
	 */
	public void setT_yhjg(double tYhjg) {
		t_yhjg = tYhjg;
	}
	/**
	 * �Ƿ�Ϊ��Ʊ
	 */
	public String getT_sfzp() {
		return t_sfzp;
	}
	/**
	 * �Ƿ�Ϊ��Ʊ
	 */
	public void setT_sfzp(String tSfzp) {
		t_sfzp = tSfzp;
	}

	/**
	 * ¼��ʱ��
	 */
	public String getT_lrsj() {
		return t_lrsj;
	}

	/**
	 * ¼��ʱ��
	 */
	public void setT_lrsj(String tLrsj) {
		t_lrsj = tLrsj;
	}
}
