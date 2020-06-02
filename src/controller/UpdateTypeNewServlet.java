package controller;

import dao.LoaiTinDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.LoaiTin;

public class UpdateTypeNewServlet extends HttpServlet {

	LoaiTinDAOImpl loaiTinDAO = new LoaiTinDAOImpl();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String ten_loai_tin = request.getParameter("TenLoaiTin");
		String ma_loai_tin = request.getParameter("ma_loai_tin");
		String url = "", mess = "";
		if (ten_loai_tin.equals("")) {
			mess = "Nhập đi mà !";
		}
		if(mess.length()==0) {
			mess = "Thêm thành công "+ten_loai_tin;
			loaiTinDAO.updateLoaiTin(new LoaiTin(Integer.parseInt(ma_loai_tin), ten_loai_tin));
			url = "/admin/manager_typenew.jsp";
		}else {
			url = "/admin/insert_typenews.jsp";
		}

		request.setAttribute("mess", mess);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}
}
