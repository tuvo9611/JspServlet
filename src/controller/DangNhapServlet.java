/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TaiKhoanDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.TaiKhoan;
import tools.MaHoa;

/**
 *
 * @author Vo
 */
public class DangNhapServlet extends HttpServlet {
	private TaiKhoanDAOImpl taiKhoanDAO = new TaiKhoanDAOImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("command").equals("logout")) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("/MyProject/default.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String email = request.getParameter("email");
		String mat_khau = request.getParameter("mat_khau");
		String err = "";

		if (email.equals("") || mat_khau.equals("")) {
			err = "Vui lòng nhập đầy đủ thông tin";
		} else {
			if (taiKhoanDAO.kiemTraDangNhap(email, MaHoa.MaHoaMD5(mat_khau)) == false) {
				err = "Email hoặc mật khẩu không chính xác";
			}
		}
		if (err.length() > 0) {
			request.setAttribute("err", err);
		}
		String url = "/account.jsp";
		try {
			if (err.length() == 0) {
				TaiKhoan tkG = taiKhoanDAO.getTaiKhoan(email);

				int t = tkG.getQuyen_truy_cap();
				String tenTK = tkG.getTen_tai_khoan();

//				if (t == 1) {
//					HttpSession session = request.getSession();
//					session.setAttribute("email", email);
//					session.setAttribute("ten_tai_khoan", tenTK);
//					url = "/admin/index.jsp";
//				}
				if (t == 0) {
					HttpSession session = request.getSession();
					session.setAttribute("email", email);
					session.setAttribute("ten_tai_khoan", tenTK);
					url = "/default.jsp";
				}
			} else {
				url = "/account.jsp";
			}
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);

		} catch (Exception e) {
		}
	}
}
