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

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		AdminBean adminbean;
		AdminDao adminDao = new AdminDao();
		adminbean = adminDao.get_admin_admin();
		//System.out.println(adminbean.getUsername());
		//System.out.println(adminbean.getPassword());
		if (username.equals(adminbean.getUsername())&&password.equals(adminbean.getPassword()))//最高管理
		{
			response.sendRedirect("/books/admin_admin.jsp");
		}
		else
		{
			HttpSession session = request.getSession();
			session.setAttribute("state", "密码错误");
			response.sendRedirect("/books/admin_login.jsp");
		}
	}

}
