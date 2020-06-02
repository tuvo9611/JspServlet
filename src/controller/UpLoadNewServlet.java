/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TinTucDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.TinTuc;
import utils.UploadFileUtils;

/**
 *
 * @author Nguyen
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024
		* 100)
public class UpLoadNewServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String message = "";

		int MaLoaiTin = Integer.parseInt(request.getParameter("MaLoaiTin"));
		String TenTinTuc = request.getParameter("TenTinTuc");
		String TieuDe = request.getParameter("TieuDe");
		String NoiDung = request.getParameter("NoiDung");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		String date = dateFormat.format(new Date());

//        String image = UploadFileUtils.UploadFile(request);
		Part imagePart = request.getPart("HinhAnh");
//        TinTuc tt = new TinTuc(MaLoaiTin, TenTinTuc, TieuDe, NoiDung, imagePart, date);
		TinTuc tt = new TinTuc(MaLoaiTin, TenTinTuc, TieuDe, NoiDung, imagePart, date);
		TinTucDAOImpl ttDAO = new TinTucDAOImpl();
		boolean returnCode = ttDAO.insertTinTuc(tt);
		if (returnCode) {
			message = "Thêm thành công " + TenTinTuc;
		} else {
			message = "ERROR";
		}
		
		request.setAttribute("message", message);
		request.getRequestDispatcher("/admin/manager_news.jsp").forward(request, response);

	}

}
