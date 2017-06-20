package com.lt.ticket.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lt.ticket.dao.CustomDao;
import com.lt.ticket.dao.impl.CustomDaoImpl;
import com.lt.ticket.entity.Custom;
import com.lt.ticket.util.BaseDaojdbc;

public class UpdateCustom extends HttpServlet {

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
		CustomDao cd = new CustomDaoImpl() ;
		String msg = null;
		BaseDaojdbc bd = new BaseDaojdbc();
		Connection myconn = bd.getConn();
		
		Custom custom = new Custom();
		String cid = request.getParameter("cid");
		try {
			custom.setC_id(cid);
			custom.setC_khsx(request.getParameter("khsx"));
			custom.setC_rs(Integer.parseInt(request.getParameter("rs")));
			custom.setC_fkfs(request.getParameter("fkfs"));
			custom.setC_gpdd(request.getParameter("gpdd"));
			custom.setC_xm(request.getParameter("xm"));
			custom.setC_dw(request.getParameter("dw"));
			int flag = cd.update(cid, custom, myconn);
			if(flag==1){
				myconn.commit();
				System.out.println("数据已提交。");
				msg = "修改成功。";
			}else if(flag==0){
				myconn.rollback();
				System.out.println("数据已回滚！");
				msg = "修改失败！000001";
			}else{
				myconn.rollback();
				System.out.println("实现类出错，请检查程序代码！");
				msg = "修改失败！000002";
			}
		} catch (Exception e) {
			try {
				myconn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("实现类出错，请检查程序代码！");
			msg = "修改失败！000003";
		}finally{
			bd.closeAll(myconn, null, null);
		}
		System.out.println("进入UpdateCustom");
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("result.jsp").forward(request, response);
		System.out.println(msg);
	}

}
