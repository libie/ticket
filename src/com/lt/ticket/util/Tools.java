package com.lt.ticket.util;

public class Tools {

	public static String Q(String InpStr) {
		return "'" + InpStr.replaceAll("'", "''") + "'";
	}

	public static String ScanText(String Inp) {
		return Inp.replaceAll("'", "''");
	}

	/**
	 * ��ȡ�ַ������ݣ� ����ΪfullStr��λ��  ����һ��headStr��  ��  ������һ��headStr��֮���һ��footStr��  ֮����ַ���
	 * ��fullStrΪ��ʡ�����ﱣ��ר����Ϊ��ɽ���Ͻ����������� headStrΪ�������ﱣ��  footStrΪ�����Ͻ����� �򷵻�ֵΪ������ר����Ϊ��ɽ����
	 * 
	 * @param fullStr
	 *            Ҫ������ַ���
	 * @param headStr
	 *            λ��ǰ����ַ���
	 * @param footStr
	 *            λ�ں�����ַ���
	 * @return headStr��footStr֮����ַ���
	 */
	public static String subString(String fullStr, String headStr,
			String footStr) {
		try{
			return fullStr.substring(fullStr.indexOf(headStr)).substring(
					headStr.length(),
					fullStr.substring(fullStr.indexOf(headStr)).indexOf(footStr));
		}catch(Exception e){
			System.err.println(e);
			System.out.println("����------------��ȡ�ַ�������ʧ�ܣ��������룡com.lt.util.Tools.subString");
			return null;
		}

	}
	
	/**
	 * yyyy-mm-dd	to	yyyymmdd
	 * @param date	yyyy-mm-dd��yyyy-m-dd��yyyy-mm-d��yyyy-m-d
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
			System.out.println("ת���ɹ���"+date+"��"+yyyy+mm+dd);
		}
		
		
		
		if(yyyy==null||mm==null||dd==null||yyyy.equals("")||mm.equals("")||dd.equals("")){
			System.out.println("����------------����ת��ʧ��:");
			System.out.println(yyyy+mm+dd);
			return null;
		}
		return yyyy+mm+dd;
		
	}
	


	public static void main(String[] args) {
		String str = Tools.subString("ʡ�����ﱣ��ר����Ϊ��ɽ���Ͻ�����������", "���ﱣ", "�Ͻ���");
		System.out.println(str);
		dateFormat("12344568wgfd78");
	}
}
