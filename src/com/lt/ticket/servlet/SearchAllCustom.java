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

public class SearchAllCustom extends HttpServlet {

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
		
		CustomDaoImpl cdi = new CustomDaoImpl();
//		TicketDaoImpl tdi = new TicketDaoImpl();
		List<Custom> customs = new ArrayList<Custom>();
//		List<Ticket> tickets = new ArrayList<Ticket>();
		
		customs = cdi.getAll(myconn);
//		tickets = tdi.getAll(myconn);
		try {
			myconn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println("Ω¯»ÎSearchAll Servlet");
		request.setAttribute("customs", customs);
//		request.setAttribute("tickets", tickets);
		request.getRequestDispatcher("custom.jsp").forward(request, response);
		
		//request.getSession().setAttribute("customs", customs);
		//request.getSession().setAttribute("tickets", tickets);
		//response.sendRedirect("xiangqing.jsp");
	}

}
