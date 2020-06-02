/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LoaiTinDAOImpl;
import model.LoaiTin;

public class UpLoadTypeNewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LoaiTinDAOImpl loaiTinDAO = new LoaiTinDAOImpl();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String message = "";

		String ten_loai_tin = request.getParameter("TenLoaiTin");


			boolean returnCode = loaiTinDAO.insertLoaiTin(new LoaiTin(ten_loai_tin));

			if (returnCode) {
				message = "Thêm thành công " + ten_loai_tin;
			} else {
				message = "Chưa được !";
			}

		request.setAttribute("message", message);
//		request.getRequestDispatcher("/admin/manager_typenew.jsp").forward(request, response);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/manager_typenew.jsp");
		rd.forward(request, response);
	}

}
