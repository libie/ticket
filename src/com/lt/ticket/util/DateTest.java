package com.lt.ticket.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		java.sql.Date date = java.sql.Date.valueOf("2013-06-05");
		System.out.println(date.toString());
		
		java.sql.Date date2 = new java.sql.Date(0);
		System.out.println(date2.toString());
		
		
		
		Date date3 = new Date();
		System.out.println(date3);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss aa");   
	    String temp_str=sdf.format(date3); 
	    System.out.println(temp_str);
	    
	    java.util.Calendar c=java.util.Calendar.getInstance();
	    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日hh时mm分ss秒");
	    System.out.println(c);
	    System.out.println(c.getTime());
	    System.out.println(sdf2.format(c.getTime()));
	}

}
