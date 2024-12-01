package com.rain.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rain.bean.AdminBean;
import com.rain.bean.BookBean;
import com.rain.dao.AdminDao;
import com.rain.dao.BookDao;

@WebServlet("/borrowServlet")
public class borrowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		BookDao bookdao = new BookDao();
		// tip为1，表示借书
		int tip = Integer.parseInt(request.getParameter("tip"));
		if (tip == 1)
		{
			int bid = Integer.parseInt(request.getParameter("bid"));
			int show = Integer.parseInt(request.getParameter("show"));
			HttpSession session = request.getSession();
			AdminBean admin;
			String aid = (String) session.getAttribute("aid");
			AdminDao admindao = new AdminDao();
			admin = admindao.get_AidInfo2(aid);
			BookBean bookBean;
			BookDao bookDao = new BookDao();
			bookBean = bookDao.get_BookInfo(bid);
			if(bookDao.borrowBook3(aid)>=admin.getMax_num())
			{
				out.write("<script type='text/javascript'>alert('The maximum number of borrowing has been reached');location.href='select.jsp'; </script>");
			}
			else if(bookBean.getNum()==0)
			{
				out.write("<script type='text/javascript'>alert('The book has been borrowed out');location.href='select.jsp'; </script>");
			}
			else {
				bookdao.borrowBook(bid, admin);
				if (show == 1) {
					response.sendRedirect("/books/select.jsp");
				} else {
					response.sendRedirect("/books/bdtimes.jsp");
				}
			}
		} else {
			int hid = Integer.parseInt(request.getParameter("hid"));
			int show = Integer.parseInt(request.getParameter("show"));
			bookdao.borrowBook2(hid);
			if (show == 1)
			{
				response.sendRedirect("/books/borrow.jsp");
			}
			else
			{
				response.sendRedirect("/books/admin_borrow.jsp");
			}

		}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
