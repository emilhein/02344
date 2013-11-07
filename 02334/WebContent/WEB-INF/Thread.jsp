<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="database.Category, database.Thread" %>
<jsp:useBean id="a" class="web.Application" scope="application"/>
<jsp:useBean id="s" class="web.Session" scope="session"/>
<%
	Thread current = null;
	Category parent = null;
	String message = null;
	try {
		current = a.getConnector().getThread(Integer.parseInt(request.getParameter("thread")));
		parent = current.getCategory();
	} catch (Exception e) {
		message = e.getMessage();
	}
%>
<% if (message != null) { %>
	<span style="color:red"><%= message %></span><br><br>
<% } %>
<% if (parent != null) { %>
	Go back to <a href="?page=category&category=<%= parent.getIdentifier() %>"><%= parent.getName() %></a><br><br>
<% } %>
<% if (current != null) { %>
	<%= current.getName() %><br><br>
	<% for (database.Comment comment : current.getComments()) { %>
		<div class="Comment">
			<table>
				<tr>
					<td><%= comment.getUser().getName() %></td>
					<td><%= comment.getContent() %></td>
				</tr>
			</table>	
		</div>
	<% } %>
<% } %>