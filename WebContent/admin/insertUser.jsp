<%-- 
    Document   : insertUser
    Created on : May 21, 2017, 9:27:40 AM
    Author     : Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thêm mới tài khoản</title>
        <link href="/MyProject/css/mos-style.css" rel='stylesheet' type='text/css' />
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        
        
        <%
	       	String ttkError =(String)request.getAttribute("");
	        if(ttkError!=null){
	        	ttkError=(String)request.getAttribute("");
	        }
        %>
        
            <div id="wrapper">
            <jsp:include page="left.jsp"></jsp:include>
                <div id="rightContent">
                    <h3>Thông tin tài khoản</h3>
                    <form action="/MyProject/UpLoadUserServlet" method="Post">
                        <table width="95%">
                            <tr>
                                <td width="125px"><b>Tên tài khoản</b></td>
                                <td><input type="text" placeholder="Tên của bạn" class="pendek" name="TenTaiKhoan">
                                </td>
                            </tr>
                            <tr>
                                <td width="125px"><b>Email</b></td>
                                <td><input type="email" placeholder="Địa chỉ email" class="pendek" name="TenDangNhap">
                                </td>
                            </tr>
                            <tr>
                                <td width="125px"><b>Mật khẩu</b></td>
                                <td><input type="password" placeholder="mật khẩu đăng nhập" class="pendek" name="MatKhau">
                                </td>
                            </tr>
                            <!-- <tr>
                                <td width="125px"><b>Quyền truy cập</b></td>
                                <td><input type="text" placeholder="Quyền" class="pendek" name="QuyenTruyCap">
                                </td>
                            </tr> -->

                            <tr>
                                <td></td>
                                <td>
                                    <input type="submit" class="button" value="Lưu dữ liệu">
                                </td>
                            </tr>

                        </table>
                    </form>
                </div>


                <div class="clear"></div>
            <jsp:include page="footer.jsp"></jsp:include>
        </div>
    </body>
</html>
