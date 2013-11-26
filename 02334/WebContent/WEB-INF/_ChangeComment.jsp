<%@page import="database.Category"%>
<%@page import="database.Connector"%>
<%@page import="database.Thread"%>
<%@page import="database.Comment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<jsp:useBean id="a" class="web.Application" scope="application"/>
<jsp:useBean id="s" class="web.Session" scope="session"/>
<%
	String message = null;
	database.Comment comment = null;
	
	
	if (request.getMethod().equalsIgnoreCase("post")) {
		try {
	
			comment = a.getConnector().getComment(Integer.parseInt(request.getParameter("comment")));
			a.getConnector().updateComment(request.getParameter("content"),comment.getIdentifier());
			
	} catch (Exception e) {
		message = e.getMessage();
		comment = null;
	}
	} %>

  

<br><br>
	<form method="post">
		<input type="hidden" name="action" value="changecomment">
		<input type="hidden" name="comment" value="<%= request.getParameter("comment") %>">
		<table>

			<tr>
				<td>change your comment too:</td>
				<td><input type="text" name="content" value="<%= request.getParameter("content") != null ? request.getParameter("content") : "" %>"></td>
				
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
	
	