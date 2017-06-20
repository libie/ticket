package com.lt.ticket.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lt.ticket.dao.impl.TicketDaoImpl;
import com.lt.ticket.util.BaseDaojdbc;
import com.lt.ticket.util.Tools;

public class SearchByDate extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		
		//页面表单数据
		String startDate = Tools.dateFormat(request.getParameter("sDate"));
		String endDate = Tools.dateFormat(request.getParameter("eDate"));
		
		
		//临时用到的变量
		BaseDaojdbc bd = new BaseDaojdbc();
		Connection myconn = bd.getConn();
		TicketDaoImpl td = new TicketDaoImpl();
		List<String[]> zongjine = new ArrayList<String[]>();
		List<String[]> zongpiaoshu = new ArrayList<String[]>();
		
		//设置要查询的字符串数组
			//收款类型
		List<String> jinefenlei= new ArrayList<String>();
		jinefenlei.add("现金");
		jinefenlei.add("刷卡");
		jinefenlei.add("挂账");
			//票据类型
		List<String> piaoshufenlei= new ArrayList<String>();
		piaoshufenlei.add("散户");
		piaoshufenlei.add("团队票");
		piaoshufenlei.add("赠票");
		
		//与数据库交互,得到数据
		zongjine = td.getFenLeiZongJinE(jinefenlei,startDate,endDate, myconn);
		zongpiaoshu = td.getFenLeiPiaoShu(piaoshufenlei,startDate,endDate, myconn);
		bd.closeAll(myconn, null, null);
		
		//用来传值的变量
		List<String> kuanfenlei = new ArrayList<String>();
		List<String> piaofenlei = new ArrayList<String>();
		List<String> kuan = new ArrayList<String>();
		List<String> piao = new ArrayList<String>();
		
		//为传值变量赋值
		for(String[] jine:zongjine){
			kuan.add(jine[0]);
			kuanfenlei.add(jine[1]);
		}
		for(String[] piaoshu:zongpiaoshu){
			piao.add(piaoshu[0]);
			piaofenlei.add(piaoshu[1]);
		}
		
		//传值
		request.setAttribute("kuan", kuan);
		request.setAttribute("piao", piao);
		request.setAttribute("kuanfenlei", kuanfenlei);
		request.setAttribute("piaofenlei", piaofenlei);
		request.getRequestDispatcher("tickets.jsp").forward(request, response);
	}

}
