<%@page import="database.Category"%>
<%@page import="database.Connector"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<jsp:useBean id="a" class="web.Application" scope="application"/>
<jsp:useBean id="s" class="web.Session" scope="session"/>
<%
	//createThread(User user, Category category, String name, boolean sticky, boolean closed
	//createComment(User user, Thread thread, String content)
	
	database.Category category = a.getConnector().getCategory(1);
	database.Thread thread = a.getConnector().getThread(1);
	
	
	
	String message = null;
	if (request.getMethod().equalsIgnoreCase("post")) {
		try {
			a.getConnector().createTread(database.User.USER, category, request.getParameter("tilte"), false, false );
			a.getConnector().createComment(database.User.USER, thread, request.getParameter("content"));
	} catch (Exception e) {
		message = e.getMessage();
	}
	}

   if (s.getUser() == null) { %>


Fill out the information below.<br><br>
	<form method="post">
		<input type="hidden" name="action" value="createThread">
		<table>
			<tr>
				<td>Title:</td>
				<td><input type="text" name="title" value="<%= request.getParameter("title") != null ? request.getParameter("title") : "" %>"></td>
			</tr>
			<tr>
				<td>Content:</td>
				<td><input type="text" name="content" value="<%= request.getParameter("content") != null ? request.getParameter("content") : "" %>" style="width:300px; height:100px;"></td>
			</tr>
			
			<tr>
				<td><br></td>
				<td><br><input type="submit" value="Create"></td>
				<td><br><span style="color:red"><%= message != null ? message : "" %></span></td>
			</tr>
			<tr>
				<td><br></td>
				
			</tr>
		</table>
	</form>
	<% }else { %>
	Welcome <%= s.getUser().getName() %>!<br><br>
	<a href="?page=home">Go back to the front page</a>
<%} %>
	