<%-- 
    Document   : left
    Created on : May 9, 2017, 10:43:30 AM
    Author     : Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="leftBar">
	<ul>
				<li><a href="<%=request.getContextPath()%>/admin/index.jsp">Trang chủ</a></li>
                <li><a href="<%=request.getContextPath()%>/admin/manager_category.jsp">Danh mục </a></li>
                <li><a href="<%=request.getContextPath()%>/admin/manager_product.jsp">Sản phẩm</a></li>
                <li><a href="<%=request.getContextPath()%>/admin/manager_typenew.jsp">Loại tin </a></li>
                <li><a href="<%=request.getContextPath()%>/admin/manager_news.jsp">Tin tức</a></li>
                <li><a href="<%=request.getContextPath()%>/admin/manager_bill.jsp"> Hóa Đơn</a></li>
                <li><a href="<%=request.getContextPath()%>/admin/manager_user.jsp">Người dùng</a></li>
                <li><a href="<%=request.getContextPath()%>/ThongKeServlet"> Thống kê</a></li>
	</ul>
	</div>
    </body>
</html>
