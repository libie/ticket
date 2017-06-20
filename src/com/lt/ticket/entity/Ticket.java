package com.lt.ticket.entity;

public class Ticket {

	private String t_id;
	
	private String c_id;
	
	/**
	 * 车牌号
	 */
	private String t_cph;
	
	/**
	 * 票号
	 */
	private String t_ph;
	
	/**
	 * 价格
	 */
	private double t_jg;
	
	/**
	 * 折扣
	 */
	private double t_zk;
	
	/**
	 * 优惠价格
	 */
	private double t_yhjg;
	
	
	/**
	 * 是否为赠票
	 */
	private String t_sfzp;
	
	
	/**
	 * 录入时间
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
	 * 车牌号
	 */
	public String getT_cph() {
		return t_cph;
	}
	/**
	 * 车牌号
	 */
	public void setT_cph(String tCph) {
		t_cph = tCph;
	}
	/**
	 * 票号
	 */
	public String getT_ph() {
		return t_ph;
	}
	/**
	 * 票号
	 */
	public void setT_ph(String tPh) {
		t_ph = tPh;
	}
	/**
	 * 价格
	 */
	public double getT_jg() {
		return t_jg;
	}
	/**
	 * 价格
	 */
	public void setT_jg(double tJg) {
		t_jg = tJg;
	}
	/**
	 * 折扣
	 */
	public double getT_zk() {
		return t_zk;
	}
	/**
	 * 折扣
	 */
	public void setT_zk(double tZk) {
		t_zk = tZk;
	}
	/**
	 * 优惠价格
	 */
	public double getT_yhjg() {
		return t_yhjg;
	}
	/**
	 * 优惠价格
	 */
	public void setT_yhjg(double tYhjg) {
		t_yhjg = tYhjg;
	}
	/**
	 * 是否为赠票
	 */
	public String getT_sfzp() {
		return t_sfzp;
	}
	/**
	 * 是否为赠票
	 */
	public void setT_sfzp(String tSfzp) {
		t_sfzp = tSfzp;
	}

	/**
	 * 录入时间
	 */
	public String getT_lrsj() {
		return t_lrsj;
	}

	/**
	 * 录入时间
	 */
	public void setT_lrsj(String tLrsj) {
		t_lrsj = tLrsj;
	}
}
