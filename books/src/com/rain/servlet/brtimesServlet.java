package com.rain.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rain.bean.AdminBean;
import com.rain.dao.AdminDao;

@WebServlet("/brtimesServlet")
public class brtimesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//tip=1表示管理员界面
		int tip = Integer.parseInt(request.getParameter("tip"));
		String name = request.getParameter("name");
		AdminDao admindao = new AdminDao();
		ArrayList<AdminBean> data = admindao.getLikeList(name);
		request.setAttribute("data", data);
		String url = "";
		if (tip == 1)
		{
			url = response.encodeURL("admin_brtimes.jsp");
		}
		else
		{
			url = response.encodeURL("brtimes.jsp");
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}
