<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<<<<<<< HEAD
<jsp:useBean id="s" class="Web.Session" scope="session"/>
<jsp:setProperty name="s" property="*"/>
=======
<%@ page import="database.Connector" %>



>>>>>>> fd9c0a33236d1531599191582aefdfd997068a75
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="Style.css">
		<title>07_2344</title>
	</head>
	<body>
	
	
<<<<<<< HEAD
	    <h1><%= s.getMessage() %></h1>
	
=======
>>>>>>> fd9c0a33236d1531599191582aefdfd997068a75
		<div id="body">
		Forum
		</div>
		
		<div id="Login">	
			<form method="post">
			<input type="hidden" name="login" value="true">
				<table>
					<tr>
						<td>Id:</td>
						<td><input type="text" name="id" value="<%= request.getParameter("id") != null ? request.getParameter("id") : "" %>"></td>
					
						<td>Password:</td>
						<td><input type="password" name="password" value="<%= request.getParameter("password") != null ? request.getParameter("password") : "" %>"></td>
						<td><br><input type="submit" value="Sign in"></td>
					</tr>
					<tr>
						<td></td>
<<<<<<< HEAD
						<td><input type="submit" value=" Not registered ?"></td>
				
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
=======
						<td><input type="submit" value="register ?"></td>
>>>>>>> fd9c0a33236d1531599191582aefdfd997068a75
					</tr>
				</table>
			</form>
		</div>



	<div id="Categories">
		<table>
			<%
				for (int i = 0; i < 10; i++) {
			%>
			<tr>
				<td><div id ="table">This is category for subject number:..................................................... <%=i%>			
				<p></p>
				</div></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>

		</body>
</html>
