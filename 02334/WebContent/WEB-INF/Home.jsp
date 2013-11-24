<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="database.CommentsAndUser" %>
	
<jsp:useBean id="a" class="web.Application" scope="application" />
<jsp:useBean id="s" class="web.Session" scope="session" />
Welcome!

<table>
		
		<tr>
			<td>Number of brugere: <%= a.getConnector().getUserCount() %></td>
		</tr>
		<%if (s.getUser()!=null){%>
		<tr>	
			<td>Number of categories:  <%= a.getConnector().getCategoryCount() %></td>
		</tr>
		<tr>
			<td>Number of threads:  <%= a.getConnector().getThreadCount() %></td>
		</tr>
		<tr>	
			<td>Number of comments:  <%= a.getConnector().getCommentCount() %></td>
		</tr>
		<tr>	
			<td>Total amount of characters in comments:  <%= a.getConnector().getTotalCommentLength() %></td>
		</tr>
		<tr>	
			<td>Average amount of characters in comments:  <%= a.getConnector().getAverageCommentLength() %></td>
		</tr>
			<tr>
				<th>
				Username
				</th>
				<th>
				Comments
				</th>
			
			</tr>
			<% for (CommentsAndUser user : a.getConnector().getUserActivity()) { %>
		<tr>
			
			
			<td><%= user.getUser().getName() %></td>
			<td><%= user.getComments() %></td>
			
		
		</tr>
		<% }} %>		
	</table>
	
		
		
<img src="images.jpeg"
	style="float: align = center; margin: 50px 50px 50px 50px; cursor: hand; border: 0"
	width="500" height="300">
