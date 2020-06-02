<%-- 
    Document   : header
    Created on : May 9, 2017, 10:32:59 AM
    Author     : Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div id="header">
		<div class="inHeader">
			<div class="mosAdmin">

				<a href="<%=request.getContextPath()%>/default.jsp">Trang người dùng</a> |
				
				<a href="#">Xin chào <%=(String)session.getAttribute("admin")%></a>	|

				<a href="../loginadmin?action=dangxuat">Đăng xuất</a>
				
			</div>
			<div class="clear"></div>
		</div>
	</div>
</body>
</html>
