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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// ��ȡ�˺ź�����
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String text = request.getParameter("code");
		String code = (String) request.getSession().getAttribute("code");
		AdminDao userdao = new AdminDao();
		// ���˺ź���������ж�
		boolean result = userdao.Login_verify(username, password);
		HttpSession session = request.getSession();
		if (code.equalsIgnoreCase(text)) {
			// �ж�������ȷ
			if (result) {
				AdminBean adminbean = new AdminBean();
				AdminDao admindao = new AdminDao();
				// �����˺ź�������ҳ����ߵ���Ϣ
				adminbean = admindao.getAdminInfo(username, password);
				// ��aid����session��
				session.setAttribute("aid", "" + adminbean.getAid());
				// ����session��ʧЧʱ��
				session.setMaxInactiveInterval(6000);
				// ����status��ֵ���ж��ǹ���Ա�����Ƕ��ߣ�status=1Ϊ����
				if (adminbean.getStatus() == 1) {
					response.sendRedirect("/books/index.jsp");
				} else {
					response.sendRedirect("/books/admin.jsp");
				}
			} else {
				// û���ҵ���Ӧ���˺ź����룬�������µ�¼
				session.setAttribute("state", "�������");
				response.sendRedirect("/books/login.jsp");
			}
		} else {
			session.setAttribute("state", "��֤�����");
			response.sendRedirect("/books/login.jsp");
		}
	}

}
