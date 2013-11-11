<%@page import="database.Category"%>
<%@page import="database.Connector"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<jsp:useBean id="a" class="web.Application" scope="application"/>
<jsp:useBean id="s" class="web.Session" scope="session"/>
<%
	//createThread(User user, Category category, String name, boolean sticky, boolean closed
	//createComment(User user, Thread thread, String content)
	
	
	
	
	
	String message = null;
	database.Thread thread = null;
	if (request.getMethod().equalsIgnoreCase("post")) {
		try {
			database.Category category = a.getConnector().getCategory(Integer.parseInt(request.getParameter("category")));
			
						
			a.getConnector().createThread(s.getUser(), category,request.getParameter("title"), false, false );
			
			
			thread = a.getConnector().getThread(category, request.getParameter("title"));
			a.getConnector().createComment(s.getUser(), thread, request.getParameter("content"));
			
	} catch (Exception e) {
		message = e.getMessage();
		thread = null;
	}
	}

   if (thread == null) { %>


Fill out the information below.<br><br>
	<form method="post">
		<input type="hidden" name="action" value="createThread">
		<input type="hidden" name="category" value="<%= request.getParameter("category") %>">
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
	Congratz. it done! <%= s.getUser().getName() %>!<br><br>
	<a href="?page=Thread&thread=<%= thread.getIdentifier()%>">Go back to the front page</a>
<%} %>
	