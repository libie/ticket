package com.lt.ticket.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lt.ticket.entity.Custom;

public class CustomFenye extends HttpServlet {

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
		
		List<Custom> customs = (List<Custom>) request.getSession().getAttribute("customs");
		String page = request.getParameter("page");
		List<Custom> customsfenye = new ArrayList<Custom>();
		if(page!=null&&!"".equals(page)){
			int p = Integer.parseInt(page);
			int pagestart = (p-1)*15+1;
			int pageend = ((p*15)<customs.size())?(p*15):customs.size();
			for (int i = pagestart; i <= pageend; i++) {
				if(customs.get(i-1)!=null)
					//System.out.println(i-1);
					customsfenye.add(customs.get(i-1));
			}
		}else{
			page="1";
			int p = Integer.parseInt(page);
			int pagestart = (p-1)*15+1;
			int pageend = ((p*15)<customs.size())?(p*15):customs.size();
			for (int i = pagestart; i <= pageend; i++) {
				if(customs.get(i-1)!=null)
					//System.out.println(i-1);
					customsfenye.add(customs.get(i-1));
			}
		}
		request.setAttribute("page", page);
		request.setAttribute("count", customs.size());
		request.setAttribute("customsfenye", customsfenye);
		System.out.println("当前页面"+customsfenye.size()+"条数据。");
		System.out.println("共"+customs.size()+"条。");
		System.out.println("当前第"+page+"页。");
		request.getRequestDispatcher("custom.jsp").forward(request, response);
	}

}
