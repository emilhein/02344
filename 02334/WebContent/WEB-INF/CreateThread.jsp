<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<jsp:useBean id="a" class="web.Application" scope="application"/>
<jsp:useBean id="s" class="web.Session" scope="session"/>
<%
	String message = null;
	if (request.getMethod().equalsIgnoreCase("post")) {
		try {
			if (!request.getParameter("password1").equals(request.getParameter("password2"))) {
				throw new Exception("Passwords do not match.");
			}
			a.getConnector().createUser(request.getParameter("mail"), request.getParameter("name"), request.getParameter("password1"), database.User.USER);
			s.signin(a, request.getParameter("name"), request.getParameter("password1"));
		} catch (Exception e) {
			message = e.getMessage();
		}
	}
%>
<% if (s.getUser() == null) { %>
	Fill out the information below.<br><br>
	<form method="post">
		<input type="hidden" name="action" value="createThread">
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
<% } else { %>
	Welcome <%= s.getUser().getName() %>!<br><br>
	<a href="?page=home">Go back to the front page</a>
<% } %>