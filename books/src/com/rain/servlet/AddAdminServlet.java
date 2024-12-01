package com.rain.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.dao.AdminDao;

@WebServlet("/AddAdminServlet")
public class AddAdminServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;//定序列号

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		AdminDao userdao = new AdminDao();
		userdao.Register2(username, password, name, email, phone);
		response.sendRedirect("/books/admin_admin.jsp");
	}
}
