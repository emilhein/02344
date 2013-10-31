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
<title>Insert title here</title>
</head>
<body>

	<div id="mainDesign">
		<h1>Welcome</h1>
	</div>
	<%Category parent = null;
	try{
	parent=a.getConnector().getCategory(Integer.parseInt(request.getParameter("Category")));
	if (parent.getParent() == null){%>
	<br>Go back to:
	<a href="?"><%=parent.getName()%></a>
	<br />
	<%}else	{%>
	<br>Go back to:
	<a href="?Category=<%=parent.getParent().getIdentifier()%>"><%=parent.getName()%></a>
	<br />
	<%
	}
	}	catch (Exception e) {} 
	for (Category category : a.getConnector().getCategories(parent)) { 
	%>
	<div class="Categories">
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
	<div class="Threads">
		<table>
			<tr>
				<td><a href="?Thread=<%=thread.getIdentifier()%>"><%=thread.getName()%></a>
				<td />
			</tr>
		</table>	
	</div>
		
		<%
			}
			}
		%>
	
</body>
</html>