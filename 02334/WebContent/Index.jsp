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
	<div id="mainDesign"><h1>Welcome</h1></div>
	<%Category parent = null;
	try{
	parent=s.getConnector().getCategory(Integer.parseInt(request.getParameter("Category")));
	if (parent.getParent() == null){%>
	<br>Go back to: <a href="?"><%=parent.getName()%></a><br/> 
	<%}else	{%>
		<br>Go back to: <a href="?Category=<%=parent.getParent().getIdentifier()%>"><%=parent.getName()%></a><br/>	
	<%
	}
	}	catch (Exception e) {} 
	for (Category category : s.getConnector().getCategories(parent)) { 
	%>
	<div id="Categories">
		<table>
			<tr>
				<td><a href="?Category=<%=category.getIdentifier()%>"><%=category.getName()%></a>
				<td />
			</tr>
			<tr>
				<td>Description of the category</td>
				<td>Number of Threads</td>
			</tr>
			
		</table>
	</div>
	<% 
	}
	
	%>
	<%if ( parent != null){
	for (database.Thread thread : parent.getThreads()) { 
	%>
		<br><a href="?Thread=<%=thread.getIdentifier()%>"><%=thread.getName()%></a><br/>
	<% 
	}
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
					<td><a href="Admin.jsp">Admin panel</a></td>
				</tr>

			</table>

		</form>

	</div>
</body>
</html>
