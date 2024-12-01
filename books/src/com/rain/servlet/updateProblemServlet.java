package com.rain.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.dao.ProblemDao;

@WebServlet("/updateProblemServlet")
public class updateProblemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String status = request.getParameter("status");
		int pid = Integer.parseInt(request.getParameter("pid"));
		ProblemDao problemdao = new ProblemDao();
		problemdao.updateProblem(pid, status);
		response.sendRedirect("/books/admin_feedback.jsp");
	}

}
