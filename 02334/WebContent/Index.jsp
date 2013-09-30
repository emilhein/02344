<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="Style.css">
		<title>07_2344</title>
	</head>
	<body>
		<div id="body">
		Hej
		
		</div>
		
		<div id="login">
			<h2>Login</h2>
			<form method="post">
				<input type="hidden" name="login" value="true">
				<table>
					<tr>
						<td>Id:</td>
						<td><input type="text" name="id" value="<%= request.getParameter("id") != null ? request.getParameter("id") : "" %>"></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type="password" name="password" value="<%= request.getParameter("password") != null ? request.getParameter("password") : "" %>"></td>
					</tr>
					<tr>
						<td></td>
						<td><br><input type="submit" value="Sign in"></td>
					</tr>
				
				</table>
			</form>
			
		</div>
		<%-- <div id="Opret Bruger">
			<h2></h2>
			<form method="post">
				<input type="hidden" name="Opret Bruger" value="true">
				<table>
					<tr>
						<td>Email:</td>
						<td><input type="text" name="Email" value="<%= request.getParameter("Email") != null ? request.getParameter("Email") : "" %>"></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type="password" name="password" value="<%= request.getParameter("password") != null ? request.getParameter("password") : "" %>"></td>
					</tr>
					<tr>
						<td></td>
						<td><br><input type="submit" value="Opret Bruger"></td>
					</tr>
				</table>
			</form>
			
		</div>
		--%>
	</body>
</html>