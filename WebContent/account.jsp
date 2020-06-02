<%-- 
    Document   : account
    Created on : Apr 25, 2017, 10:18:05 PM
    Author     : Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title> Đăng nhập, đăng ký thành viên</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/prettyPhoto.css" rel="stylesheet">
        <link href="css/price-range.css" rel="stylesheet">
        <link href="css/animate.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">
        <link href="css/responsive.css" rel="stylesheet">
        <script src="js/jquery.prettyPhoto.js"></script>
        <script src="js/main.js"></script>
        
        
        
    </head>
     <body>
        <% String ten_tai_khoan_err="",email_err="",mat_khau_err="";

        if(request.getAttribute("ten_tai_khoan_err")!=null)
        {
            ten_tai_khoan_err=(String)request.getAttribute("ten_tai_khoan_err");
        }
        if(request.getAttribute("email_err")!=null)
        {
        	email_err=(String)request.getAttribute("email_err");
        }
        if(request.getAttribute("mat_khau_err")!=null )
        {
            mat_khau_err=(String)request.getAttribute("mat_khau_err");
        }
        
        
        
        
        
        String ten_tai_khoan="",email="",mat_khau="";
        if(request.getAttribute("ten_tai_khoan")!=null)
        {
            ten_tai_khoan=(String)request.getAttribute("ten_tai_khoan");
        }
        if(request.getAttribute("email")!=null)
        {
        	email=(String)request.getAttribute("email");
        }
        
        if(request.getAttribute("mat_khau")!=null )
        {
            mat_khau=(String)request.getAttribute("mat_khau");
        }
        
        
        
        
        
        String err="";
        if(request.getAttribute("err")!=null)
        {
            err=(String) request.getAttribute("err");
        }
        %>
        
        
        <div id="fb-root"></div>
		<script async defer crossorigin="anonymous" src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v7.0"></script>
        
        
        <jsp:include page="header1.jsp"></jsp:include>
            <section id="form"><!--form-->
                <div class="container">
                    <div class="row">
                        <div class="col-sm-4 col-sm-offset-1">
                            <div class="login-form"><!--login form-->
                                <h2>Đăng nhập</h2>
                                <form action="DangNhapServlet" method="POST">
                                
                                <div class="row">
                                	<div class="col">
								        
	                                    <p style="color:red"><%=err%></p>
	                                    <input type="email" placeholder="Email đăng nhập" name="email" />
	                                    <input type="password" placeholder="Mật khẩu đăng nhập" name="mat_khau" />
	                                   
	                                    <button type="submit" class="btn btn-default">Đăng nhập</button>
	                                    
	                                   </div> 
								    </div>
                                </form>
                            </div><!--/login form-->
                        </div>
                        <div class="col-sm-1">
                            <h2 class="or">Hoặc</h2>
                        </div>
                        <div class="col-sm-4">
                            <div class="signup-form"><!--sign up form-->
                                <h2>Đăng ký tài khoản mới!</h2>
                                <form action="DangKyServlet" method="POST">
                                    <p style="color:red"><%=ten_tai_khoan_err %></p>
                               		<input type="text" placeholder="Họ và tên" name="ten_tai_khoan" value="<%=ten_tai_khoan %>"/>
                                  
                                    <p style="color:red"><%=email_err %></p>
                                    <input type="email" placeholder="Địa chỉ Email đăng ký" name="email" value="<%=email %>"/>
                                  
                                    <p style="color:red"><%=mat_khau_err %></p>
                                    <input type="password" placeholder="Mật khẩu đăng nhập" name="mat_khau" value="<%=mat_khau %>"/>
                                  
                                    <button type="submit" class="btn btn-default">Đăng ký</button>
                                </form>
                            </div><!--/sign up form-->
                        </div>
                    </div>
                </div>
            </section>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
