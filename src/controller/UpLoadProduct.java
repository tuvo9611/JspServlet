/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.SanPhamDAOImpl;
import model.SanPham;
import utils.UploadFileUtils;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024
		* 100)
public class UpLoadProduct extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String message = "";

		String TenSanPham = request.getParameter("TenSanPham");
		int MaDanhMuc = Integer.parseInt(request.getParameter("MaDanhMuc"));
//		String image = UploadFileUtils.UploadFile(request);
		Part imagePart = request.getPart("HinhAnh");
		int SoLuong = Integer.parseInt(request.getParameter("SoLuong"));
		int DonGia = Integer.parseInt(request.getParameter("DonGia"));
		int GiamGia = Integer.parseInt(request.getParameter("GiamGia"));
		String ManHinh = request.getParameter("ManHinh");
		String CameraSau = request.getParameter("CameraSau");
		String CameraTruoc = request.getParameter("CameraTruoc");
		String HeDieuHanh = request.getParameter("HeDieuHanh");
		String Cpu = request.getParameter("Cpu");
		String BoNho = request.getParameter("BoNho");
		String Ram = request.getParameter("Ram");
		String KetNoi = request.getParameter("KetNoi");
		String PinSac = request.getParameter("PinSac");
		String BaoHanh = request.getParameter("BaoHanh");
		String MoTa = request.getParameter("MoTa");

		SanPham san_pham = new SanPham(TenSanPham, MaDanhMuc, imagePart, SoLuong, DonGia, GiamGia, ManHinh, CameraSau,
				CameraTruoc, HeDieuHanh, Cpu, BoNho, Ram, KetNoi, PinSac, BaoHanh, MoTa);
		SanPhamDAOImpl sanPhamDAO = new SanPhamDAOImpl();
		boolean returnCode = sanPhamDAO.insertSanPham(san_pham);
		System.out.println("them san pham: "+returnCode);
		if (returnCode) {
			message = "Thêm thành công "+TenSanPham;
		} else {
			message = "Thêm không thành công";
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("/admin/manager_product.jsp").forward(request, response);
//		response.sendRedirect("/admin/manager_product.jsp");
	}

}
