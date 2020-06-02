/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ChiTietHoaDonDAOImpl;
import dao.HoaDonDAOImpl;
import dao.SanPhamDAOImpl;
import dao.TaiKhoanDAOImpl;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ChiTietHoaDon;
import model.GioHang;
import model.HoaDon;
import model.SanPham;
import model.TaiKhoan;

public class ThanhToanServlet extends HttpServlet {

    private final HoaDonDAOImpl hoaDonDAO = new HoaDonDAOImpl();
    private final ChiTietHoaDonDAOImpl chiTietHoaDonDAO = new ChiTietHoaDonDAOImpl();
    private final TaiKhoanDAOImpl taiKhoanDAOI = new TaiKhoanDAOImpl();
    private final SanPhamDAOImpl sanphamDAO= new SanPhamDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String dia_chi_giao_hang = request.getParameter("dia_chi_giao_hang");
        String so_dien_thoai = request.getParameter("so_dien_thoai");
        String phuong_thuc_thanh_toan = request.getParameter("phuong_thuc_thanh_toan");
        String dia_chi_erro = "", so_dien_thoai_err = "";
        if (dia_chi_giao_hang.equals("")) {
            dia_chi_erro = "Nhập đi mà";
        }
        if (dia_chi_erro.length() > 0) {
            request.setAttribute("dia_chi_erro", dia_chi_erro);
        }
        if (so_dien_thoai.equals("")) {
            so_dien_thoai_err = "Nhập đi mà";
        }
        if(so_dien_thoai_err.length()>0)
        {
            request.setAttribute("so_dien_thoai_err", so_dien_thoai_err);
        }
        request.setAttribute("dia_chi_giao_hang", dia_chi_giao_hang);
        request.setAttribute("so_dien_thoai",so_dien_thoai);

        
        if (dia_chi_erro.length() == 0 && so_dien_thoai_err.length()==0) {

            HttpSession session = request.getSession();
            GioHang cart = (GioHang) session.getAttribute("cart");
            String email = (String) session.getAttribute("email");
            try {
            	// ngay mua hang
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String date = dateFormat.format(new Date());
                // ma hoa don
                int ID = (int) new Date().getTime();
                HoaDon hd = new HoaDon();
                hd.setMa_hoa_don(ID);
                hd.setMa_tai_khoan(taiKhoanDAOI.getTaiKhoan(email).getMa_tai_khoan());
                hd.setTong_tien(cart.total());
                hd.setDia_chi_giao_hang(dia_chi_giao_hang);
                hd.setSo_dien_thoai(so_dien_thoai);
                hd.setPhuong_thuc_thanh_toan(phuong_thuc_thanh_toan);
                hd.setNgay_mua_hang(date);
                hd.setTinh_trang_don_hang(0);

                hoaDonDAO.themHoaDon(hd);
                TreeMap<SanPham, Integer> list = cart.getList();
                for (Map.Entry<SanPham, Integer> ds : list.entrySet()) {

                    chiTietHoaDonDAO.themChiTietHoaDon(new ChiTietHoaDon(
                    		ID, 
                    		ds.getKey().getMa_san_pham(),
                            ds.getValue(), 
                            ds.getKey().getDon_gia(), 
                            ds.getKey().getGiamgia(),
                            ds.getValue() * ds.getKey().getDon_gia() - (ds.getValue() * ds.getKey().getDon_gia() * ds.getKey().getGiamgia()) / 100)
                    		);
                       // mua roi phai tru di so luong   
                   sanphamDAO.updateSanPham(new SanPham(ds.getKey().getMa_san_pham(),ds.getKey().getSo_luong()-ds.getValue()));

                }
                cart = new GioHang();
                session.setAttribute("cart", cart);
            } catch (Exception e) {
            	e.printStackTrace();
            	System.out.println(e.getMessage());
            }
            response.sendRedirect("/MyProject/default.jsp");
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/checkout.jsp");
            rd.forward(request, response);
        }
    }
}
