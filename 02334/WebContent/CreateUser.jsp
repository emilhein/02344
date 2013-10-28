<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="database.Connector" %>
<jsp:useBean id="a" class="Web.Application" scope="application"/>
<jsp:useBean id="s" class="Web.Session" scope="session"/>
<%
	String message = null;
	if (request.getMethod().equalsIgnoreCase("post")) {
		try {
			if (!request.getParameter("password1").equals(request.getParameter("password2"))) {
				throw new Exception("Passwords do not match.");
			}
			a.getConnector().createUser(request.getParameter("mail"), request.getParameter("name"), request.getParameter("password1"), database.User.USER);
			s.login(a, request.getParameter("name"), request.getParameter("password1"));
			response.sendRedirect("Index.jsp");
		} catch (Exception e) {
			message = e.getMessage();
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Opret Bruger</title>
	</head>
	<body>
		Fill out the information below.<br><br>
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
					<td><input type="password" name="password1" value="<%= request.getParameter("password1") != null ? request.getParameter("password1") : "" %>"></td>
				</tr>
				<tr>
					<td>Password (repeat):</td>
					<td><input type="password" name="password2" value="<%= request.getParameter("password2") != null ? request.getParameter("password2") : "" %>"></td>
				</tr>
				<tr>
					<td><br></td>
					<td><br><input type="submit" value="Create"></td>
				</tr>
				<tr>
					<td><br></td>
					<td><br><span style="color:red"><%= message != null ? message : "" %></span></td>
				</tr>
			</table>
		</form>
	</body>
</html>