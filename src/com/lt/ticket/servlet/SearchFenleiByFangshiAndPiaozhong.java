package com.lt.ticket.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lt.ticket.dao.impl.TicketDaoImpl;
import com.lt.ticket.entity.KhsxFenlei;
import com.lt.ticket.entity.Table2;
import com.lt.ticket.util.BaseDaojdbc;
import com.lt.ticket.util.Tools;

public class SearchFenleiByFangshiAndPiaozhong extends HttpServlet {

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
		
		String url = "cxbyday.jsp";
		//url = request.getRequestURI();
		url = request.getParameter("url");
		//页面表单数据
		String startDate = null;
		if(!"".equals(request.getParameter("startdate"))&&request.getParameter("startdate")!=null){
			request.setAttribute("riqis", request.getParameter("startdate"));
			startDate = Tools.dateFormat(request.getParameter("startdate"));
		}
		String endDate = null;
		if(!"".equals(request.getParameter("enddate"))&&request.getParameter("enddate")!=null)
		{
			request.setAttribute("riqie", request.getParameter("enddate"));
			endDate = Tools.dateFormat(request.getParameter("enddate"));
		}
		
		//临时用到的变量
		BaseDaojdbc bd = new BaseDaojdbc();
		Connection myconn = bd.getConn();
		TicketDaoImpl tdi = new TicketDaoImpl();
		
		//设置要查询的字符串数组
			//收款类型----------内层循环参数，用于内层list分类
		List<String> fkfs= new ArrayList<String>();
		fkfs.add("现金");
		fkfs.add("刷卡");
		fkfs.add("挂账");
			//票据类型----------外层循环参数，用于外层list分类
		List<String> piaozhongs= new ArrayList<String>();
		piaozhongs.add("散户");
		piaozhongs.add("团队票");
		piaozhongs.add("赠票");
		
		//用来传值的变量
			//客户属性、付款方式分类：票数、金额统计
		List<KhsxFenlei> quanfenleijine;
		
		//与数据库交互,得到数据
		quanfenleijine=tdi.searchByPiaoZhong(startDate, endDate, piaozhongs, fkfs, myconn);
		bd.closeAll(myconn, null, null);
		
		//传值
		List<Table2> table2s = new ArrayList<Table2>();
		Table2 table2;
		for(int i =0; i <quanfenleijine.size();i++){
			table2 = new Table2();
			int xianjicount = Integer.valueOf(quanfenleijine.get(i).getFkfsfenleis().get(0).getPiaoshu());
			int shuakacount = Integer.valueOf(quanfenleijine.get(i).getFkfsfenleis().get(1).getPiaoshu());
			int guazhangcount = Integer.valueOf(quanfenleijine.get(i).getFkfsfenleis().get(2).getPiaoshu());
			table2.setPiaoshu(xianjicount+shuakacount+guazhangcount);
			table2.setPiaozhong(piaozhongs.get(i));
			table2.setXianjin(quanfenleijine.get(i).getFkfsfenleis().get(0).getZongjine());
			table2.setShuaka(quanfenleijine.get(i).getFkfsfenleis().get(1).getZongjine());
			table2.setGuazhang(quanfenleijine.get(i).getFkfsfenleis().get(2).getZongjine());
			table2s.add(table2);
		}
		
//		for(int i =0; i<table2s.size();i++){
//			Table2 ta = table2s.get(i);
//			System.out.println(ta.getPiaozhong());
//		}
		
		System.out.println("进入Servlet");
		request.setAttribute("table2s", table2s);
		
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
