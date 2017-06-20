package com.lt.ticket.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lt.ticket.dao.impl.TicketDaoImpl;
import com.lt.ticket.entity.Ticket;
import com.lt.ticket.util.BaseDaojdbc;

public class UpdateTicket extends HttpServlet {

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
		
		String msg = null;
		BaseDaojdbc bd = new BaseDaojdbc();
		Connection myconn = bd.getConn();
		TicketDaoImpl tdi = new TicketDaoImpl();
		
		Ticket ticket = new Ticket();
		String tph = request.getParameter("ph");
		try {
			ticket.setT_ph(tph);
			ticket.setT_cph(request.getParameter("cph"));
			ticket.setT_jg(Double.valueOf(request.getParameter("jg")));
			ticket.setT_zk(Double.valueOf(request.getParameter("zk")));
			ticket.setT_yhjg(Double.valueOf(request.getParameter("yhjg")));
			ticket.setT_sfzp(request.getParameter("sfzp"));
			ticket.setT_lrsj(request.getParameter("lrsj"));
			ticket.setC_id(request.getParameter("cid"));
			int flag = tdi.update(tph, ticket, myconn);
			if(flag==1){
				myconn.commit();
				System.out.println("数据已提交。");
				msg = "修改成功。";
			}else if(flag==0){
				myconn.rollback();
				System.out.println("数据已回滚！");
				msg = "修改失败！000004";
			}else{
				myconn.rollback();
				System.out.println("实现类出错，请检查程序代码！");
				msg = "修改失败！000005";
			}
		} catch (Exception e) {
			try {
				myconn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("实现类出错，请检查程序代码！");
			msg = "修改失败！000006";
		}finally{
			bd.closeAll(myconn, null, null);
		}
		System.out.println("进入UpdateTicket");
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("result.jsp").forward(request, response);
		System.out.println(msg);
	}

}
