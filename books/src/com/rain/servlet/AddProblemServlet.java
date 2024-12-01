package com.rain.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rain.bean.AdminBean;
import com.rain.dao.AdminDao;
import com.rain.dao.ProblemDao;

@WebServlet("/AddProblemServlet")
public class AddProblemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		AdminBean adminbean;
		String aid = (String) session.getAttribute("aid");
		AdminDao admindao = new AdminDao();
		adminbean = admindao.get_AidInfo2(aid);
		String name = request.getParameter("name");
		String page = request.getParameter("page");
		String body = request.getParameter("body");
		String phone = request.getParameter("phone");
		ProblemDao problemdao = new ProblemDao();
		problemdao.addProblem(adminbean, name, page, body, phone);
		response.sendRedirect("/books/result.jsp");
	}

}
