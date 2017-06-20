package com.lt.ticket.util;

public class Tools {

	public static String Q(String InpStr) {
		return "'" + InpStr.replaceAll("'", "''") + "'";
	}

	public static String ScanText(String Inp) {
		return Inp.replaceAll("'", "''");
	}

	/**
	 * 截取字符串内容， 内容为fullStr中位于  “第一个headStr”  和  “‘第一个headStr’之后第一个footStr”  之间的字符串
	 * 如fullStr为：省级文物保护专家组为中山区老建筑“把脉” headStr为：“文物保”  footStr为：“老建筑” 则返回值为：“护专家组为中山区”
	 * 
	 * @param fullStr
	 *            要处理的字符串
	 * @param headStr
	 *            位于前面的字符串
	 * @param footStr
	 *            位于后面的字符串
	 * @return headStr和footStr之间的字符串
	 */
	public static String subString(String fullStr, String headStr,
			String footStr) {
		try{
			return fullStr.substring(fullStr.indexOf(headStr)).substring(
					headStr.length(),
					fullStr.substring(fullStr.indexOf(headStr)).indexOf(footStr));
		}catch(Exception e){
			System.err.println(e);
			System.out.println("错误------------获取字符串内容失败，请检查输入！com.lt.util.Tools.subString");
			return null;
		}

	}
	
	/**
	 * yyyy-mm-dd	to	yyyymmdd
	 * @param date	yyyy-mm-dd、yyyy-m-dd、yyyy-mm-d、yyyy-m-d
	 * @return	yyyymmdd
	 */
	public static String dateFormat(String date){
		String yyyy = null; 
		String mm = null;
		String dd = null;
		if(date.indexOf('-')>0){
			yyyy = date.substring(0, date.indexOf('-'));
			mm = date.substring(date.indexOf('-')+1,date.lastIndexOf('-'));
			dd = date.substring(date.lastIndexOf('-')+1);
			if(mm.length()==1){
				mm="0"+mm;
			}
			if(dd.length()==1){
				dd="0"+dd;
			}
			System.out.println("转换成功："+date+"到"+yyyy+mm+dd);
		}
		
		
		
		if(yyyy==null||mm==null||dd==null||yyyy.equals("")||mm.equals("")||dd.equals("")){
			System.out.println("错误------------日期转换失败:");
			System.out.println(yyyy+mm+dd);
			return null;
		}
		return yyyy+mm+dd;
		
	}
	


	public static void main(String[] args) {
		String str = Tools.subString("省级文物保护专家组为中山区老建筑“把脉”", "文物保", "老建筑");
		System.out.println(str);
		dateFormat("12344568wgfd78");
	}
}
