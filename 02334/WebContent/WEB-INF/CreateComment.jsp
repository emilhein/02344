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
		
			thread = a.getConnector().getThread(Integer.parseInt(request.getParameter("thread")));
			a.getConnector().createComment(s.getUser(), thread, request.getParameter("content"));
			
	} catch (Exception e) {
		message = e.getMessage();
		thread = null;
	}
	}

   if (thread == null) { %>


Fill out the information below.<br><br>
	<form method="post">
		<input type="hidden" name="action" value="createComment">
		<input type="hidden" name="thread" value="<%= request.getParameter("thread") %>">
		<table>

			<tr>
				<td>Your comment:</td>
				<td><input type="text" name="content" value="<%= request.getParameter("content") != null ? request.getParameter("content") : "" %>" style="width:300px; height:100px;"></td>
				
			</tr>
			
			<tr>
				<td><br></td>
				<td><br><input type="submit" value="Submit"></td>
				<td><br><span style="color:red"><%= message != null ? message : "" %></span></td>
			</tr>
			<tr>
				<td><br></td>
				
			</tr>
		</table>
	</form>
	<% }else { %>
	Thanks for keeping the debate alive <%= s.getUser().getName() %>!<br><br>
	<a href="?page=Thread&thread=<%= thread.getIdentifier()%>">Go back to the thread</a>
<%} %>
	