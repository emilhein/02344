<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="database.Category" %>
<jsp:useBean id="a" class="web.Application" scope="application"/>
<jsp:useBean id="s" class="web.Session" scope="session"/>
<%
	Category current = null;
	Category parent = null;
	try {
		current = a.getConnector().getCategory(Integer.parseInt(request.getParameter("category")));
		parent = current.getParent();
	} catch (Exception e) {
	}
%>
<% if (parent != null) { %>
	Go back to <a href="?page=category&category=<%= parent.getIdentifier() %>"><%= parent.getName() %></a><br><br>
<% } else if (current != null) { %>
	Go back to <a href="?page=category">Categories</a><br><br>
<% } %>
<% for (Category category : a.getConnector().getCategories(current)) { %>
	<div class="Category">
		<table>
			<tr>
				<td><a href="?page=category&category=<%= category.getIdentifier() %>"><%= category.getName() %></a></td>
				<td>(Number of Threads)</td>
			</tr>
		</table>
	</div>
<% } %>
<br><br>
<% if (current != null) { %>
	<% for (database.Thread thread : current.getThreads()) { %>
		<div class="Thread">
			<table>
				<tr>
					<td><a href="?page=thread&thread=<%= thread.getIdentifier() %>"><%= thread.getName() %></a></td>
					<td>(Number of Comments)</td>
				</tr>
			</table>	
		</div>
	<% } %>
<% } %>