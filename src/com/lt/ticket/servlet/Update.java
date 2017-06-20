package com.lt.ticket.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lt.ticket.dao.impl.CustomDaoImpl;
import com.lt.ticket.dao.impl.TicketDaoImpl;
import com.lt.ticket.entity.Custom;
import com.lt.ticket.entity.Ticket;
import com.lt.ticket.util.BaseDaojdbc;

public class Update extends HttpServlet {

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
		BaseDaojdbc bd = new BaseDaojdbc();
		Connection myconn = bd.getConn();
		String url = "insert.jsp";
		
		String cid=request.getParameter("cid");
		String tid=request.getParameter("tid");
		
		CustomDaoImpl cdi = new CustomDaoImpl();
		TicketDaoImpl tdi = new TicketDaoImpl();
		if(cid!=null&&!"".equals(cid)){
			Custom custom = new Custom();
			custom.setC_id(cid);
			custom = cdi.search(custom, myconn).get(0);//客户ID有汉字时会出现错误
			request.setAttribute("custom", custom);
			url = "updatecustom.jsp";
		}else if (tid!=null&&!"".equals(tid)){
			Ticket ticket = new Ticket();
			ticket.setT_cph(tid);
			ticket = tdi.search(ticket, myconn).get(0);
			request.setAttribute("ticket", ticket);
			url = "updateticket.jsp";
		}
		
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
