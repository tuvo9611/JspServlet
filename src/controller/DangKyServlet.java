/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TaiKhoanDAOImpl;
import java.io.IOException;
import java.util.Date;

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
 * @author Nguyen
 */
public class DangKyServlet extends HttpServlet {
	private TaiKhoanDAOImpl taiKhoanDAO = new TaiKhoanDAOImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String ten_tai_khoan = request.getParameter("ten_tai_khoan");
		String email = request.getParameter("email");
		String mat_khau = request.getParameter("mat_khau");

		String ttk_ERROR = "", tdn_ERROR = "", mk_ERROR = "";

		// TEN TAI KHOAN
		if (ten_tai_khoan.equals("")) {
			ttk_ERROR = "Vui lòng nhập Họ tên của bạn !";
		}
		if (ttk_ERROR.length() > 0) {
			request.setAttribute("ten_tai_khoan_err", ttk_ERROR);
		}

		// EMAIL
		if (email.equals("")) {
			tdn_ERROR = "Vui lòng nhập Email của bạn";
		} else {
			if (taiKhoanDAO.kiemTraTaiKhoan(email) == true) {
				tdn_ERROR = "Địa chỉ Email đã tồn tại";
			}
		}
		if (tdn_ERROR.length() > 0) {
			request.setAttribute("email_err", tdn_ERROR);
		}

		// MAT KHAU
		if (mat_khau.equals("")) {
			mk_ERROR = "Vui lòng nhập mật khẩu";
		}
		if (mk_ERROR.length() > 0) {
			request.setAttribute("mat_khau_err", mk_ERROR);
		}

		// CHUYENR DEN VALUE
//		HttpSession session = request.getSession();
		request.setAttribute("ten_tai_khoan", ten_tai_khoan);
		request.setAttribute("email", email);
		request.setAttribute("mat_khau", mat_khau);
		String url = "/account.jsp";
		try {
			
			if (mk_ERROR.length() == 0 && tdn_ERROR.length() == 0 && ttk_ERROR.length() == 0) {

				TaiKhoan tk = new TaiKhoan(ten_tai_khoan, email, MaHoa.MaHoaMD5(mat_khau), 0);
				taiKhoanDAO.themTaiKhoan(tk);
				url = "/index.jsp";

			} else {
				url = "/account.jsp";
			}
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
