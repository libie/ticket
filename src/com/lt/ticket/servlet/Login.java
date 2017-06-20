package com.lt.ticket.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lt.ticket.dao.impl.UsersDaoImpl;
import com.lt.ticket.entity.Users;
import com.lt.ticket.util.BaseDaojdbc;
import com.lt.ticket.util.Tools;

public class Login extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String loginerr ;
		loginerr="�û������������";
		
		//����ʹ�����ޣ�
		String limit = "20141030";	//ʹ����������
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		String temp_str=sdf.format(date); 
	    if(Integer.parseInt(Tools.dateFormat(temp_str))>Integer.parseInt(limit)){
	    	request.setAttribute("err", "�ѳ���ʹ�����ޣ�999002");
			System.out.println("----------�ѳ���ʹ�����ޣ�999002");
			request.getRequestDispatcher("login.jsp").forward(request, response);
	    	return;
	    }
		
		Users user = null;
		UsersDaoImpl udi = new UsersDaoImpl();
		String userid = request.getParameter("userName");
		String password = request.getParameter("passWord");
		
		BaseDaojdbc bd = new BaseDaojdbc();
		Connection myconn = bd.getConn();
		try {
			user = udi.login(userid, password, myconn);
		} catch (Exception e) {
			loginerr="���ݿ����Ӵ���";
			e.printStackTrace();
			System.out.println("ʵ����������������룡");
		}
		if(user!=null){
			System.out.println("��½�ɹ���");
			request.getSession().setAttribute("logger", user);
			//request.getRequestDispatcher("index.html").forward(request, response);
			response.sendRedirect("index.html");
		}else{
			
			request.setAttribute("err", loginerr);
			System.out.println("�û������������");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
