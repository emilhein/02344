<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:useBean id="a" class="web.Application" scope="application" />
<jsp:useBean id="s" class="web.Session" scope="session" />
Welcome!

<table>
		<tr>
			<td>Antal brugere: <%= a.getConnector().getCount("users") %></td>
		</tr>
		<%if (s.getUser()!=null){
		%>
		<tr>	
			<td>Antal categories:  <%= a.getConnector().getCount("categories") %></td>
		</tr>
		<tr>
			<td>Antal threads:  <%= a.getConnector().getCount("threads") %></td>
		</tr>
		<tr>	
			<td>Antal comments:  <%= a.getConnector().getCount("comments") %></td>
		</tr>
		<%} %>
	</table>
<img src="images.jpeg"
	style="float: center; margin: 10px 10px 15px 15px; cursor: hand; border: 0"
	width="300" height="300">