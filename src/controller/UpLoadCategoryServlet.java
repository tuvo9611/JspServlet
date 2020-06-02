/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DanhMucDAOImpl;
import model.DanhMuc;

/**
 *
 * @author Nguyen
 */
public class UpLoadCategoryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DanhMucDAOImpl danhMucDAO = new DanhMucDAOImpl();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String TenDanhMuc = request.getParameter("TenDanhMuc");
 
		String url = "";
		String message = "";
		if (TenDanhMuc.equals("")) {
			message = "Nhập đi mà !";
		}
		if (message.length() == 0) {
			message = "Thêm thành công danh mục " + TenDanhMuc;
			danhMucDAO.insertDanhMuc(new DanhMuc(TenDanhMuc));

			url = "/admin/manager_category.jsp";
		} else {
			url = "/admin/insert_category.jsp";
		}
		request.setAttribute("mess", message);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}
}
