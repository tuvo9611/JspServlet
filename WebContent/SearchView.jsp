<%-- 
    Document   : index
    Created on : Apr 25, 2017, 9:01:16 PM
    Author     : Nguyen
--%>

<%@page import="model.SanPham"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="dao.SanPhamDAOImpl"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Home | E-Shopper</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/prettyPhoto.css" rel="stylesheet">
        <link href="css/price-range.css" rel="stylesheet">
        <link href="css/animate.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">
        <link href="css/responsive.css" rel="stylesheet">
        <script src="js/jquery.prettyPhoto.js"></script>
        <script src="js/main.js"></script>
        <!--[if lt IE 9]>
        <script src="js/html5shiv.js"></script>
        <script src="js/respond.min.js"></script>
        <![endif]-->       


    </head>
    <body>
        <%
            SanPhamDAOImpl spdao = new SanPhamDAOImpl();
            String ten_san_pham = "";
            if (request.getAttribute("ten_san_pham") != null) {
                ten_san_pham = (String) request.getAttribute("ten_san_pham");
            }
            NumberFormat nf = NumberFormat.getInstance();
            nf.setMinimumIntegerDigits(0);

        %>

        <jsp:include page="header.jsp"></jsp:include>
        <jsp:include page="slider.jsp"></jsp:include>
            <section>
                <div class="container">
                    <div class="row">
                    <jsp:include page="category.jsp"></jsp:include>
                        <div class="col-sm-9 padding-right">
                            <div class="features_items"><!--features_items-->
                                <h2 class="title text-center">SẢN PHẨM TÌM THẤY</h2>
                                
                            <% int count = 0;
                                for (SanPham sp : spdao.getListSanPham(ten_san_pham)) {
                                    count++;
                            %>
                            <div class="col-sm-4">
                                <div class="product-image-wrapper">
                                    <div class="single-products">
                                        <div class="productinfo text-center">
                                            <a href="detail.jsp?ma_san_pham=<%=sp.getMa_san_pham()%>"> 
                                            <%-- <img src="/MyProject/ImagesProduct/<%=sp.getHinh_anh()%>" alt="" /></a> --%>
                                            <img src="data:image/jpg;base64,<%=sp.getHinh_annh()%>" width="48" height="240"/>
                                            <h2><%=nf.format(sp.getDon_gia())%> VND</h2>
                                            <p><%=sp.getTen_san_pham()%></p>
                                            <a href="GioHangServlet?command=insert&ma_san_pham=<%=sp.getMa_san_pham()%>&cartID=<%=System.currentTimeMillis() %>" class="btn btn-default add-to-cart">
                                                <i class="fa fa-shopping-cart"></i>Thêm vào giỏ hàng</a>
                                        </div>

                                    </div>
                                    <div class="choose">
                                        <ul class="nav nav-pills nav-justified">
                                            <li><a href="#"><i class="fa fa-plus-square"></i>So sánh</a></li>
                                            <li><a href="detail.jsp?ma_san_pham=<%=sp.getMa_san_pham()%>"><i class="fa fa-plus-square"></i>Xem chi tiết</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <% }
                            %>



                        </div>
                    </div>
                </div>
        </section>

        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
