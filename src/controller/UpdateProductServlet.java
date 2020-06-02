/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SanPhamDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.SanPham;
import utils.UploadFileUtils;

/**
 *
 * @author Nguyen
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024
		* 100)
public class UpdateProductServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String MaSanPham = request.getParameter("ma_san_pham");
		int SoLuong = Integer.parseInt(request.getParameter("SoLuong"));
		SanPhamDAOImpl sanPhamDAO = new SanPhamDAOImpl();
		sanPhamDAO.updateSanPham(new SanPham(Integer.parseInt(MaSanPham), SoLuong));
		request.getRequestDispatcher("/admin/manager_product.jsp").forward(request, response);

	}

}
