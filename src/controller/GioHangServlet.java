/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.SanPhamDAOImpl;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.GioHang;
import model.SanPham;

public class GioHangServlet extends HttpServlet {

    private SanPhamDAOImpl sanPhamDAO = new SanPhamDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);   }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    	HttpSession session = request.getSession();
       
    	int msp = Integer.parseInt(request.getParameter("ma_san_pham"));
        GioHang cart = (GioHang) session.getAttribute("cart");
        
        String command = request.getParameter("command");
        String url = "/cart.jsp";
        
//        ArrayList<Long> listBuy =  new ArrayList<>();
//        session.setAttribute("cartID", listBuy);
        try {
           
//        	long idBuy = 0;
//            if (request.getParameter("cartID") != null) {
//                idBuy = Long.parseLong(request.getParameter("cartID"));
//            }

            SanPham sp = sanPhamDAO.getChiTietSanPham(msp);

            switch (command) {
                case "insert":
                    
//                    if (listBuy.indexOf(idBuy) == -1) 
//                    {
                        cart.addToCart(sp, 1);
//                        listBuy.add(idBuy);
//                    }
                    break;
           
                case "plus":
                 
//                	if (listBuy.indexOf(idBuy) == -1) 
//                	{
                        cart.addToCart(sp, 1);
//                        listBuy.add(idBuy);
//                    }
                    break;
         
                case "sub":
                 
//                	if(listBuy.indexOf(idBuy) ==-1)
//                    {
                        cart.subToCart(sp, 1);
//                        listBuy.add(idBuy);
//                    }
                    break;
                case "remove":
                    cart.removeToCart(sp);
                    break;
                
                default:
                    break;

            }
            RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
            rd.forward(request, response);

        } catch (Exception e) {
        }
    }
}
