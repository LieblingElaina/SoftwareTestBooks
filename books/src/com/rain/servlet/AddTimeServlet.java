package com.rain.servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.bean.HistoryBean;
import com.rain.dao.BookDao;

@WebServlet("/AddTimeServlet")
public class AddTimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String endtime = request.getParameter("endtime");
		int hid = Integer.parseInt(request.getParameter("hid"));
		BookDao bookdao = new BookDao();
		bookdao.AddTime(hid, endtime);
		response.sendRedirect("/books/admin_borrow.jsp");
	}

}
