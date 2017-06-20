package com.lt.ticket.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lt.ticket.dao.impl.CustomDaoImpl;
import com.lt.ticket.util.BaseDaojdbc;

public class DeleteCustom extends HttpServlet {

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
		
		String msg = null;
		BaseDaojdbc bd = new BaseDaojdbc();
		Connection myconn = bd.getConn();
		CustomDaoImpl cdi = new CustomDaoImpl();
		
		String cid = request.getParameter("cid");
		try{
			int flag = cdi.delete(cid, myconn);
			if(flag==1){
				myconn.commit();
				System.out.println("�������ύ��");
				msg = "ɾ���ɹ���";
			}else if(flag==0){
				myconn.rollback();
				System.out.println("�����ѻع���");
				msg = "ɾ��ʧ�ܣ�000010";
			}else{
				myconn.rollback();
				System.out.println("ʵ����������������룡");
				msg = "ɾ��ʧ�ܣ�000011";
			}
		} catch (Exception e) {
			try {
				myconn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("ʵ����������������룡");
			msg = "ɾ��ʧ�ܣ�000012";
		}finally{
			bd.closeAll(myconn, null, null);
		}
		System.out.println("����DeleteCustom");
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("result.jsp").forward(request, response);
		System.out.println(msg);
	}

}
