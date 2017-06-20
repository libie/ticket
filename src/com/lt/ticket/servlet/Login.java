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
		loginerr="用户名或密码错误。";
		
		//设置使用期限，
		String limit = "20141030";	//使用期限日期
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		String temp_str=sdf.format(date); 
	    if(Integer.parseInt(Tools.dateFormat(temp_str))>Integer.parseInt(limit)){
	    	request.setAttribute("err", "已超过使用期限：999002");
			System.out.println("----------已超过使用期限：999002");
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
			loginerr="数据库连接错误。";
			e.printStackTrace();
			System.out.println("实现类出错，请检查程序代码！");
		}
		if(user!=null){
			System.out.println("登陆成功！");
			request.getSession().setAttribute("logger", user);
			//request.getRequestDispatcher("index.html").forward(request, response);
			response.sendRedirect("index.html");
		}else{
			
			request.setAttribute("err", loginerr);
			System.out.println("用户名或密码错误。");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
