<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="database.Connector" %>
<jsp:useBean id="s" class="Web.Session" scope="session"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Opret Bruger</title>
	</head>
	<body>
	
		Fill out the information below:<br><br>
		
		<% if (request.getMethod().equalsIgnoreCase("post")) {
			try {
				if (!request.getParameter("password").equals(request.getParameter("repeatPassword"))) {
					throw new Exception("Passwords do not match.");
				}
				s.getConnector().createUser(request.getParameter("mail"), request.getParameter("name"), request.getParameter("password"), database.User.USER);
			} catch (Exception e) {
		%><span style="color:red">A problem has occurred: <%= e.getMessage() %></span><br><br><%
			}
		} %>
	
		<form method="post">
			<input type="hidden" name="action" value="createUser">
			<table>
				<tr>
					<td>Mail:</td>
					<td><input type="text" name="mail" value="<%= request.getParameter("mail") != null ? request.getParameter("mail") : "" %>"></td>
				</tr>
				<tr>
					<td>Name:</td>
					<td><input type="text" name="name" value="<%= request.getParameter("name") != null ? request.getParameter("name") : "" %>"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" value="<%= request.getParameter("password") != null ? request.getParameter("password") : "" %>"></td>
				</tr>
				<tr>
					<td>Repeat password:</td>
					<td><input type="password" name="repeatPassword" value="<%= request.getParameter("repeatPassword") != null ? request.getParameter("repeatPassword") : "" %>"></td>
				</tr>
				<tr>
					<td></td>
					<td><br><input type="submit" value="Create"></td>
				</tr>
			</table>
		</form>
	</body>
</html>