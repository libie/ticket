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

import com.lt.ticket.dao.impl.TicketDaoImpl;
import com.lt.ticket.entity.Ticket;
import com.lt.ticket.util.BaseDaojdbc;

public class SearchTicketByCID extends HttpServlet {

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

		//客户ID不可为汉字，否则会出现乱码，无法查询
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		
		String cid = request.getParameter("cid");
		//System.out.println(request.getParameter("cid"));
		
		BaseDaojdbc bd = new BaseDaojdbc();
		Connection myconn = bd.getConn();
		
		TicketDaoImpl tdi = new TicketDaoImpl();
		List<Ticket> tickets = new ArrayList<Ticket>();
		
		tickets = tdi.getByCID(cid,myconn);
		try {
			myconn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println("进入SearchAll Servlet");
		request.setAttribute("tickets", tickets);
		request.getRequestDispatcher("piaoju.jsp").forward(request, response);
		
	}

}
