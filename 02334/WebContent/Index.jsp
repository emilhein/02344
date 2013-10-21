<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="database.Connector, database.Category, database.User" %>
<jsp:useBean id="s" class="Web.Session" scope="session"/>
<jsp:setProperty name="s" property="*"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="Style.css">
<title>07_2344</title>
</head>
<body>


	<h1><%=s.getMessage()%></h1>
	<h1>Category name</h1>
	<%
	for (Category category : s.getConnector().getCategories(null)) { 
	%>
		<%=category.getName()%>
	<% 
	}
	%>
	
				
	

	<div id="Login">
		<form method="post">
			<input type="hidden" name="login" value="true">

			<table>
				<tr>
					<td>Id:</td>
					<td><input type="text" name="id"
						value="<%=request.getParameter("id") != null ? request.getParameter("id") : ""%>"></td>

					<td>Password:</td>
					<td><input type="password" name="password"
						value="<%=request.getParameter("password") != null ? request.getParameter("password") : ""%>"></td>
					<td><br> <input type="submit" value="Sign in"></td>
				</tr>
				<tr>
					<td><a href="CreateUser.jsp">Opret Bruger</a></td>

				</tr>

			</table>

		</form>

	</div>
</body>
</html>
