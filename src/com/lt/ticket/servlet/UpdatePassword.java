package com.lt.ticket.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lt.ticket.dao.impl.UsersDaoImpl;
import com.lt.ticket.entity.Users;
import com.lt.ticket.util.BaseDaojdbc;

public class UpdatePassword extends HttpServlet {

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
		UsersDaoImpl udi = new UsersDaoImpl();
		
		try {
			Users user = (Users) request.getSession().getAttribute("logger");
			if(user==null){
				System.out.println("用户未登录！");
			}
			request.getSession().removeAttribute("logger");
			String userid = user.getU_id();
			String password = request.getParameter("npassword");
			int flag = udi.update(userid, password, myconn);
			if(flag==1){
				myconn.commit();
				System.out.println("数据已提交。");
				msg = "修改密码成功。";
			}else if(flag==0){
				myconn.rollback();
				System.out.println("数据已回滚！");
				msg = "修改密码失败！000013";
			}else{
				myconn.rollback();
				System.out.println("实现类出错，请检查程序代码！");
				msg = "修改密码失败！000014";
			}
		} catch (Exception e) {
			try {
				myconn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("实现类出错，请检查程序代码！");
			msg = "修改密码失败！000015";
		}finally{
			bd.closeAll(myconn, null, null);
		}
		System.out.println("进入UpdatePassword");
		request.getSession().setAttribute("npas", msg);
		response.sendRedirect("login.jsp");
		//request.getRequestDispatcher("index.html").forward(request, response);
		System.out.println(msg);
		
	}

}
