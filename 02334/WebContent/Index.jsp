<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="database.User" %>
<%@ page import="web.Page" %>
<jsp:useBean id="a" class="web.Application" scope="application"/>
<jsp:useBean id="s" class="web.Session" scope="session"/>
<%
	s.setup(a.getConnector());
	String message = null;
	if (request.getMethod().equalsIgnoreCase("post")) {
		try {
			if (request.getParameter("action").equalsIgnoreCase("signout")) {
				s.signout();
			} else if (request.getParameter("action").equalsIgnoreCase("signin")) {
				s.signin(request.getParameter("mailOrName"), request.getParameter("password"));
			} else if (request.getParameter("action").equalsIgnoreCase("block")){
				s.blockUser();
				
			}
		} catch (Exception e) {
			message = e.getMessage();
		}
	}
	Page p = a.getPage(request.getParameter("page"), s);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="Style.css">
		<title><%= p.getTitle() %> - 07_2344</title>
	</head>
	<body>
		<% if (s.getUser() == null) { %>
			<div id="login">
				<form method="post">
					<input type="hidden" name="action" value="signin">
					<table>
						<tr>
							<td>Mail or name:</td>
							<td><input type="text" name="mailOrName" value="<%= request.getParameter("mailOrName") != null ? request.getParameter("mailOrName") : "" %>"></td>
							<td>Password:</td>
							<td><input type="password" name="password" value="<%= request.getParameter("password") != null ? request.getParameter("password") : "" %>"></td>
							<td><input type="submit" value="Sign In"></td>
						</tr>
						<tr>
							<td><a href="?page=home">Home</a></td>
							<td><a href="?page=category">Categories</a></td>
							<td><a href="?page=createuser">Create user</a></td>
							<td><span style="color:red"><%= message != null ? message : "" %></span></td>
						</tr>
					</table>
				</form>
			</div>
		<% } else { %>
			<div id="login">
				
					<table>
						<tr>
							
							<td>Welcome <%= s.getUser().getName() %></td>
							<td>
							<form method="post">
							<input type="hidden" name="action" value="signout">
							<input type="submit" value="Sign Out">
							</form>
							</td>
							<%if (s.getUser().getType() == User.USER){ %>
						
							<td> 
							<form method="post">
							<input type="hidden" name="action" value="block">
							<input type="submit" value="Block account" style = "color: red">
							</form>
							</td>
						
						<% } %>					
						</tr>
						<tr>
							<td><a href="?page=home">Home</a></td>
							<td><a href="?page=category">Categories</a></td>
					<% if (s.getUser().getType() <= User.MODERATOR) { %>
							<td><a href="?page=administrateusers">Administrate users</a></td>
					<% } %>
						</tr>
					</table>
				
			</div>
		<% } %>
		<div id="content">
			<jsp:include page="<%= p.getPath() %>"/>
		</div>
	</body>
</html>