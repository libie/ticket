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

import com.lt.ticket.dao.CustomDao;
import com.lt.ticket.dao.TicketDao;
import com.lt.ticket.dao.impl.CustomDaoImpl;
import com.lt.ticket.dao.impl.TicketDaoImpl;
import com.lt.ticket.entity.Custom;
import com.lt.ticket.entity.Ticket;
import com.lt.ticket.util.BaseDaojdbc;
import com.lt.ticket.util.Tools;

public class InsertTicket extends HttpServlet {


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

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		//response.setCharacterEncoding("utf-8");
		TicketDao td = new TicketDaoImpl();
		CustomDao cd = new CustomDaoImpl() ;
		String msg = null;
		BaseDaojdbc bd = new BaseDaojdbc();
		Connection myconn = bd.getConn();
		try {
			//��ȡ���ύ��Ϣ
			String khid=request.getParameter("khid");
			String khsx=request.getParameter("khsx");
			String jg=request.getParameter("jg");
			String rs=request.getParameter("rs");
			String zk=request.getParameter("zk0");
			String fkfs=request.getParameter("fkfs");
			String gpdd=request.getParameter("gpdd");
			String xm=request.getParameter("xm");
			String dw=request.getParameter("dw");
			String sfzp="��";
			if("��Ʊ".equals(khsx)){
				sfzp="��";
			}else{
				sfzp="��";
			}
			List<String> phs = new ArrayList<String>();
			List<String> cphs = new ArrayList<String>();
			List<Double> yhjgs = new ArrayList<Double>();
			List<String> lrsjs = new ArrayList<String>();
			//System.out.println(rs);
			for(int i=0;i<Integer.parseInt(rs);i++){
				phs.add(request.getParameter("ph"+i));
				cphs.add(request.getParameter("cph"+i));
				lrsjs.add(Tools.dateFormat(request.getParameter("jlsj"+i)));
				yhjgs.add(Double.valueOf(request.getParameter("yhjg"+i)));
			}
			
			//��������Ʊ����Ϣ
			//List<Ticket> tickets = new ArrayList<Ticket>();
			Ticket ticket;
			int ticketflag = 0;
			for(int i =0; i<Integer.parseInt(rs);i++){
				ticket = new Ticket();
				ticket.setT_ph(phs.get(i));
				ticket.setT_cph(cphs.get(i));
				ticket.setC_id(khid);
				ticket.setT_jg(Double.valueOf(jg));
				ticket.setT_zk(Double.valueOf(zk));
				ticket.setT_yhjg(Double.valueOf(yhjgs.get(i)));
				ticket.setT_sfzp(sfzp);
				ticket.setT_lrsj(lrsjs.get(i));
				//tickets.add(ticket);
				
				ticketflag+=td.insert(ticket,myconn);
			}
			
			//����ͻ���Ϣ
			Custom custom = new Custom();
			custom.setC_id(khid);
			custom.setC_khsx(khsx);
			custom.setC_rs(Integer.parseInt(rs));
			custom.setC_fkfs(fkfs);
			custom.setC_gpdd(gpdd);
			custom.setC_xm(xm);
			custom.setC_dw(dw);
			int customflag=0;
			customflag = cd.insert(custom,myconn);
			if(ticketflag==Integer.parseInt(rs)&&customflag==1){
				myconn.commit();
				System.out.println("�������ύ��");
				msg = "����ɹ���\n��"+customflag+"λ�ͻ���"+ticketflag+"��Ʊ�ݡ�";
			}else if(customflag==1&&ticketflag<Integer.parseInt(rs)){
				msg = "����Ʊ�ݱ���ʧ�ܣ�����Ʊ�ݺ��Ƿ��ظ���";
				myconn.commit();
				System.out.println("����Ʊ�ݱ���ʧ�ܣ�����Ʊ�ݺ��Ƿ��ظ���");
			}else{
				msg = "����ʧ�ܣ��������롣";
				myconn.rollback();
				System.out.println("�����ѻع���");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				myconn.rollback();
				System.out.println("�����ѻع���");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			msg = "�ύʧ�ܣ��������롣\n�����������������Ա��ϵ";
		}finally{
			bd.closeAll(myconn, null, null);
		}
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("result.jsp").forward(request, response);
		System.out.println(msg);
		//response.sendRedirect("result.jsp");
	}

}
