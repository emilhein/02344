<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="database.User" %>
<jsp:useBean id="a" class="web.Application" scope="application"/>
<jsp:useBean id="s" class="web.Session" scope="session"/>
<%
	String message = null;
	if (request.getMethod().equalsIgnoreCase("post")) {
		try {
			User user = a.getConnector().getUser(request.getParameter("mail"));
			if (request.getParameter("action").equalsIgnoreCase("promote")) {
				user.setType(user.getType() - 1);
			} else if (request.getParameter("action").equalsIgnoreCase("degrade")) {
				user.setType(user.getType() + 1);
			}
		} catch (Exception e) {
			message = e.getMessage();
		}
	}
%>
<% if (message != null) { %>
	<span style="color:red"><%= message %></span><br><br>
<% } %>
<div class="lists">
	<table>
		<tr>
			<th>Administrators</th>
			<th>Mail</th>
		</tr>
		<% for (User user : a.getConnector().getUsers(User.ADMINISTRATOR)) { %>
			<tr>
				<td><%= user.getName() %></td>
				<td><%= user.getMail() %></td>
				<td></td>
				<td>
					<% if (s.getUser().getIdentifier() != user.getIdentifier()){ %>
						<form method="post">
							<input type="hidden" name="action" value="degrade">
							<input type="hidden" name="mail" value="<%= user.getMail() %>">	
							<input type="submit" value="&#8595;">	
						</form>
					<% } %>
				</td>
			</tr>
		<% } %>
		<tr>
			<th><br>Moderators</th>
			<th>Mail</th>
		</tr>
		<% for (User user : a.getConnector().getUsers(User.MODERATOR)) { %>
			<tr>
				<td><%= user.getName() %></td>
				<td><%= user.getMail() %></td>
				<td>
					<form method="post">
						<input type="hidden" name="action" value="promote">
						<input type="hidden" name="mail" value="<%= user.getMail() %>">	
						<input type="submit" value="&#8593;">	
					</form>
				</td>
				<td>
					<form method="post">
						<input type="hidden" name="action" value="degrade">
						<input type="hidden" name="mail" value="<%= user.getMail() %>">	
						<input type="submit" value="&#8595;">	
					</form>
				</td>
			</tr>
		<% } %>
		<tr>
			<th><br>Users</th>
			<th>Mail</th>
		</tr>
		<% for (User user : a.getConnector().getUsers(User.USER)) { %>
			<tr>
				<td><%= user.getName() %></td>
				<td><%= user.getMail() %></td>
				<td>
					<form method="post">
						<input type="hidden" name="action" value="promote">
						<input type="hidden" name="mail" value="<%= user.getMail() %>">	
						<input type="submit" value="&#8593;">	
					</form>
				</td>
				<td>
					<form method="post">
						<input type="hidden" name="action" value="degrade">
						<input type="hidden" name="mail" value="<%= user.getMail() %>">	
						<input type="submit" value="&#8595;">	
					</form>
				</td>
			</tr>
		<% } %>
		<tr>
			<th><br>Blocked</th>
			<th>Mail</th>
		</tr>
		<% for (User user : a.getConnector().getUsers(User.BLOCKED)) { %>
			<tr>
				<td><%= user.getName() %></td>
				<td><%= user.getMail() %></td>
				<td>
					<form method="post">
						<input type="hidden" name="action" value="promote">
						<input type="hidden" name="mail" value="<%= user.getMail() %>">	
						<input type="submit" value="&#8593;">	
					</form>
				</td>
			</tr>
		<% } %>
	</table>
</div>
<div class="lists">
	<table>
		<tr>
			<td>Antal brugere: <%= a.getConnector().getCount("users") %></td>
		</tr>
		<tr>	
			<td>Antal categories:  <%= a.getConnector().getCount("categories") %></td>
		</tr>
		<tr>
			<td>Antal threads:  <%= a.getConnector().getCount("threads") %></td>
		</tr>
		<tr>	
			<td>Antal comments:  <%= a.getConnector().getCount("comments") %></td>
		</tr>
		
	</table>
</div>