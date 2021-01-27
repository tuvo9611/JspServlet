package controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/loginadmin")
public class XuLyDangNhapAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public XuLyDangNhapAdmin() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		ServletConfig config = this.getServletConfig();
		String nameAdmin = config.getInitParameter("adminName");
		String passAdmin = config.getInitParameter("adminPass");

		HttpSession ss = request.getSession();
		String nameAdminFromClient = request.getParameter("uname");
		String passAdminFromClient = request.getParameter("psw");

		String urlIndex = "/admin/index.jsp";
		String action = request.getParameter("action");
		
		if (action.equals("dangnhap")) {

			
			if (nameAdminFromClient.equals(nameAdmin) && passAdminFromClient.equals(passAdmin)) {
				ss.setAttribute("admin", nameAdmin);
				response.sendRedirect(request.getContextPath() + urlIndex);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher(urlIndex);
				rd.forward(request, response);
			}

		} else if (action.equals("dangxuat")) {
			ss.invalidate();
			
			response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
