<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="database.Category, database.Thread, database.Comment" %>
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
	<b><%= current.getName() %></b>
	<div class="Comments">
	<table>
	<% for (database.Comment comment : current.getComments()) { %>
		
				<tr>
					<td>Written by: <%= comment.getUser().getName() %></td>
					<td>content <%= comment.getContent() %></td>
				</tr>
				<tr>
					<td><h5>created <%= comment.getChanged() %>	</h5></td>
				</tr>
			<%if (s.getUser() != null && comment.getUser().getIdentifier() == s.getUser().getIdentifier()) { %>
				<tr>
		 		<jsp:include page="_ChangeComment.jsp">
		 			<jsp:param name="comment" value="<%= comment.getIdentifier() %>"/>
		 			</jsp:include>
				</tr>
			<% } %>
				 
		
	<% } %>
	</table>
	</div>
<% } %>
<% if(!current.getClosed() && s.getUser() != null){ %>
<jsp:include page="_CreateComment.jsp"/>
<% } %>