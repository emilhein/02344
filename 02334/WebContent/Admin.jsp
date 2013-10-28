<%@page import="org.omg.CORBA.Request"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
<%@ page import="database.Connector, database.Category, database.User"%>
<jsp:useBean id="a" class="web.Application" scope="application"/>
<jsp:useBean id="s" class="web.Session" scope="session" />
<jsp:setProperty name="s" property="*" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Panel</title>
<link rel="stylesheet" type="text/css" href="Style.css">
</head>
<body>


<% if (request.getMethod().equalsIgnoreCase("post")) {
			try {
				User user = a.getConnector().getUser(request.getParameter("mail"));
				if (request.getParameter("action").equalsIgnoreCase("promote")){
					user.setType(user.getType()-1);
				}else {
					user.setType(user.getType()+1);
				}
				
			} catch (Exception e) {
		%><span style="color:red">A problem has occurred: <%= e.getMessage() %></span><br><br><%
			}
		} %>







		<div class="lists">
			<table>
				<tr>
					<th>Administrators</th>
					<th>Email</th>
				</tr>

				<%
					for (User user : a.getConnector().getUsers(User.ADMINISTRATOR)) {
				%>
				<tr>
					<td><%=user.getName()%></td>
					<td><%=user.getMail()%></td>
					<td>
						<form method="post">
							<input type="hidden" name="action" value="degrade">
							<input type="hidden" name="mail" value="<%= user.getMail() %>">	
							<input type="submit" value="-">	
						</form>
					</td>

				</tr>

				<%
					}
				%>

				<tr>
					<th align="left"><br>Moderators</th>
					<th>Email</th>
				</tr>
				<%
					for (User user : a.getConnector().getUsers(User.MODERATOR)) {
				%>


				<tr>
					<td><%=user.getName()%></td>
					<td><%=user.getMail()%></td>
					<td>
						<form method="post">
							<input type="hidden" name="action" value="promote">
							<input type="hidden" name="mail" value="<%= user.getMail() %>">	
							<input type="submit" value="+">	
						</form>
					</td>
					<td>
						<form method="post">
							<input type="hidden" name="action" value="degrade">
							<input type="hidden" name="mail" value="<%= user.getMail() %>">	
							<input type="submit" value="-">	
						</form>
					</td>
				</tr>

				<%
					}
				%>
				<tr>
					<th align="left"><br>Users</th>
					<th>Email</th>
				</tr>

				<%
					for (User user : a.getConnector().getUsers(User.USER)) {
				%>


				<tr>
					<td><%=user.getName()%></td>
					<td><%=user.getMail()%></td>
					<td>
						<form method="post">
							<input type="hidden" name="action" value="promote">
							<input type="hidden" name="mail" value="<%= user.getMail() %>">	
							<input type="submit" value="+">	
						</form>
					</td>
					<td>
						<form method="post">
							<input type="hidden" name="action" value="degrade">
							<input type="hidden" name="mail" value="<%= user.getMail() %>">	
							<input type="submit" value="-">	
						</form>
					</td>
				</tr>

				<%
					}
				%>

							<tr>
					<th align="left"><br>Blocked</th>
					<th>Email</th>
				</tr>
				<%
					for (User user : a.getConnector().getUsers(User.BLOCKED)) {
				%>


				<tr>
					<td><%=user.getName()%></td>
					<td><%=user.getMail()%></td>
					<td>
						<form method="post">
							<input type="hidden" name="action" value="promote">
							<input type="hidden" name="mail" value="<%= user.getMail() %>">	
							<input type="submit" value="+">	
						</form>
					</td>
				</tr>

				<%
					}
				%>
			</table>
		</div>
	


</body>
</html>