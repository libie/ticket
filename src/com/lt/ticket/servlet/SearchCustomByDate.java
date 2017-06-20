package com.lt.ticket.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lt.ticket.dao.impl.CustomDaoImpl;
import com.lt.ticket.entity.Custom;
import com.lt.ticket.util.BaseDaojdbc;
import com.lt.ticket.util.Tools;

public class SearchCustomByDate extends HttpServlet {

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
		
		String startdatetemp = request.getParameter("sdate");
		String enddatetemp = request.getParameter("edate");
		String startdate;
		String enddate;
		if(startdatetemp==null||"".equals(startdatetemp)){
			startdate="00000000";
		}else{
			startdate=Tools.dateFormat(startdatetemp);
		}
		if(enddatetemp==null||"".equals(enddatetemp)){
			enddate="99999999";
		}else{
			enddate=Tools.dateFormat(enddatetemp);
		}
		String xm = request.getParameter("xm");
		String dw = request.getParameter("dw");
		String khsx = request.getParameter("khsx");
		String fkfs = request.getParameter("fkfs");
		String gpdd = request.getParameter("gpdd");
		int pagestart = 1;
		int pageend = 15;
		String page = request.getParameter("page");
		if(page!=null&&!"".equals(page)){
			int p = Integer.parseInt(page);
			pagestart = (p-1)*15+1;
			pageend = p*15;
		}else{
			page="1";
		}
		//TODO 分页处理
		pagestart=0;	//返回10000条以内的数据，在页面分页
		pageend=10000;
		
		BaseDaojdbc bd = new BaseDaojdbc();
		Connection myconn = bd.getConn();
		
		CustomDaoImpl cdi = new CustomDaoImpl();
		List<Custom> customs = new ArrayList<Custom>();
		
		customs = cdi.searchByDate(startdate, enddate,xm,dw,khsx,fkfs,gpdd,pagestart,pageend,myconn);//只支持10000条数据同时查询
		try {
			myconn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("customs", customs);
		request.setAttribute("page", page);
		//System.out.println("数据库查出数量："+customs.size());
		request.getRequestDispatcher("CustomFenye").forward(request, response);
	}

}
