<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

form {
	border: 3px solid #f1f1f1;
	text-align: center;
}

input[type=text], input[type=password] {
	width: 50%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}

button {
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 80px;
	border: none;
	cursor: pointer;
	width: 40%;
}

button:hover {
	opacity: 0.8;
}

.container {
	padding: 16px;
}

h2 {
	text-align: center;
}
</style>
</head>
<body>

	<h2>Login Form</h2>

	<form action="../loginadmin?action=dangnhap" method="post">
		<div class="imgcontainer"></div>

		<div class="container">
			<label for="uname"><b>Username</b></label> 
			<input type="text"	placeholder="Enter Username" name="uname" required> <br>

			<label for="psw"><b>Password</b></label> 
			<input type="password"	placeholder="Enter Password" name="psw" required> <br>
			<button type="submit">Login</button>
		</div>

	</form>

</body>
</html>
