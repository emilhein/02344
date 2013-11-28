<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="database.Category"%>
<jsp:useBean id="a" class="web.Application" scope="application" />
<jsp:useBean id="s" class="web.Session" scope="session" />
<%
	Category current = null;
	Category parent = null;

	try {
		current = a.getConnector().getCategory(
				Integer.parseInt(request.getParameter("category")));
		parent = current.getParent();
	} catch (Exception e) {
	}
%>
<H2>Welcome to the basic forum design.</H2>
<%
	if (parent != null) {
%>
Go back to
<a href="?page=category&category=<%=parent.getIdentifier()%>"><%=parent.getName()%></a>
<br>
<br>
<%
	} else if (current != null) {
%>
Go back to
<a href="?page=category">Categories</a>
<br>
<br>
<%
	}
%>

<%
	if (current != null && s.getUser() != null) {
%>
<td><a
	href="?page=createThread&category=<%=current.getIdentifier()%>">Create
		thread</a></td>

<%
	}
%>
<div class="Categories">
	<table>
		<tr>
			<td><b>Title</b></td>
			<td style="text-align: right"><b>Thread</b></td>
			<td style="text-align: right"><b>Comments</b></td>
		</tr>

		<%
	for (Category category : a.getConnector().getCategories(current)) {
%>

		<tr>
			<td><b><a
					href="?page=category&category=<%=category.getIdentifier()%>"><%=category.getName()%></a></b></td>
			<td><%=a.getConnector().getThreadCount(category)%></td>
			<td><%=a.getConnector().getCategoryCount(category)%></td>
		</tr>


		<%
	}
%>
	</table>
</div>
<%
	if (current != null) {
%>
<div class="Threads">
	<table>
		<tr>
			<td><b>Title</b></td>
			<td style="text-align: right"><b>Comments</b></td>
		</tr>
		<%
			for (database.Thread thread : current.getThreads()) {
		%>


		<tr>

			<td><a href="?page=thread&thread=<%=thread.getIdentifier()%>"><%=thread.getName()%></a></td>
			<td><%=a.getConnector().getCommentCount(thread)%></td>
		</tr>

		<%
			}
		%>
	</table>
</div>
<% } %>