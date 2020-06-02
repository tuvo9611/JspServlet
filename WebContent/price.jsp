<%-- 
    Document   : price
    Created on : May 7, 2017, 9:25:32 PM
    Author     : Nguyen
--%>

<%@page import="model.SanPham"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="dao.SanPhamDAOImpl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title> Sản phẩm tìm theo giá</title>
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
        <% int gia1 = Integer.parseInt(request.getParameter("gia1"));
            int gia2 = Integer.parseInt(request.getParameter("gia2"));
            SanPhamDAOImpl spDao = new SanPhamDAOImpl();
            NumberFormat nf = NumberFormat.getInstance();
            nf.setMinimumIntegerDigits(0);
            ArrayList<SanPham> arr = spDao.getListSanPhamTheoGia(gia1, gia2);
            int start = 0, end = 9;
            if (arr.size() < 9) {
                end = arr.size();

            }
            if (request.getParameter("start") != null) {
                start = Integer.parseInt(request.getParameter("start"));

            }
            if (request.getParameter("end") != null) {
                end = Integer.parseInt(request.getParameter("end"));

            }
            ArrayList<SanPham> list = spDao.getListSanPhamTheo(arr, start, end);

        %>
        <jsp:include page="header.jsp"></jsp:include>
        <jsp:include page="slider.jsp"></jsp:include>
            <section>
                <div class="container">
                    <div class="row">
                    <jsp:include page="category.jsp"></jsp:include>
                        <div class="col-sm-9 padding-right">
                            <div class="features_items"><!--features_items-->
                                <h2 class="title text-center">Sản phẩm tìm thấy</h2>

                            <% 
                            	for (SanPham sp : list) {
                            %>
                            <div class="col-sm-4">
                                <div class="product-image-wrapper">
                                    <div class="single-products">
                                        <div class="productinfo text-center">
                                            <%-- <img src="/MyProject/ImagesProduct/<%=sp.getHinh_anh()%>" alt="" /> --%>
                                            
	                                         <img src="data:image/jpg;base64,<%=sp.getHinh_annh()%>" width="48" height="240"/>   
                                            
                                            <h2><%=nf.format(sp.getDon_gia())%> VND</h2>
                                            <p><%=sp.getTen_san_pham()%></p>
                                            <a href="GioHangServlet?command=insert&ma_san_pham=<%=sp.getMa_san_pham()%>&cartID=<%=System.currentTimeMillis()%>" class="btn btn-default add-to-cart">
                                                <i class="fa fa-shopping-cart"></i>Thêm vào giỏ hàng</a>
                                        </div>
                                        <div class="product-overlay">
                                            <div class="overlay-content">
                                                <h2><%=nf.format(sp.getDon_gia())%>VND</h2>
                                                <p><%=sp.getTen_san_pham()%></p>
                                                <a href="GioHangServlet?command=insert&ma_san_pham=<%=sp.getMa_san_pham()%>&cartID=<%=System.currentTimeMillis()%>" class="btn btn-default add-to-cart">
                                                    <i class="fa fa-shopping-cart"></i>Thêm vào giỏ hàng</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="choose">
                                        <ul class="nav nav-pills nav-justified">
                                            
                                            <li><a href="detail.jsp?ma_san_pham=<%=sp.getMa_san_pham()%>"><i class="fa fa-plus-square"></i>Xem chi tiết</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <% }%>
                            <div style="clear: both"></div>
                            <ul class="pagination">
                                <%
                                    int a, b;
                                    int limit = arr.size() / 9;
                                    if (limit * 9 < arr.size()) {
                                        limit += 1;
                                    }
                                    for (int i = 1; i <= limit; i++) {
                                        a = (i - 1) * 9;
                                        b = i * 9;
                                        if (b > arr.size()) {
                                            b = arr.size();
                                        }

                                %>
                                <li class="active"><a href="price.jsp?gia1=<%=gia1 %>&gia2=<%=gia2%>&start=<%=a%>&end=<%=b%> "><%=i%></a></li>
                                    <% }
                                    %>
                            </ul>


                        </div><!--features_items-->





                    </div>
                </div>
            </div>
        </section>

        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>

