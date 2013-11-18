<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="database.CommentsAndUser" %>
	
<jsp:useBean id="a" class="web.Application" scope="application" />
<jsp:useBean id="s" class="web.Session" scope="session" />
Welcome!

<table>
		<tr>
			<td>Antal brugere: <%= a.getConnector().getCount("users") %></td>
		</tr>
		<%if (s.getUser()!=null){%>
		<tr>	
			<td>Antal categories:  <%= a.getConnector().getCount("categories") %></td>
		</tr>
		<tr>
			<td>Antal threads:  <%= a.getConnector().getCount("threads") %></td>
		</tr>
		<tr>	
			<td>Antal comments:  <%= a.getConnector().getCount("comments") %></td>
		</tr>
			
			
		<tr>
			<% for (CommentsAndUser user : a.getConnector().getUserActivity()) { %>
			
			<td>username: <%= user.getName() %></td>
			<td>username: <%= user.getComments() %></td>
			<td></td>
		<% }} %>
		</tr>
				
	</table>
	
		
		
<img src="images.jpeg"
	style="float: align = center; margin: 50px 50px 50px 50px; cursor: hand; border: 0"
	width="500" height="300">