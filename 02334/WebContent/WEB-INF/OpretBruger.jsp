<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:useBean id="s" class="Web.Session" scope="session" />
<jsp:setProperty name="s" property="*" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Opret Bruger</title>
</head>

<body>
	<a href = "OpretBruger.jsp">OpretBruger</a> 
	<input type = "button" value = "Opret Bruger" name ="OpretBruger.jsp"/>
	<div id="Opret Bruger" /div>
		<form method="post">
			<input type="hidden" name="Opret Bruger" value="true">
			<table>
				<tr>
					<td>Name:</td>
					<td><input type="text" name=""
						value="<%=request.getParameter("") != null ? request
					.getParameter("") : ""%>"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password"
						value="<%=request.getParameter("password") != null ? request
					.getParameter("password") : ""%>"></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><input type="Email" name="Email"
						value="<%=request.getParameter("Email") != null ? request
					.getParameter("Email") : ""%>"></td>
				</tr>
				<tr>
					<td>k�n:</td>
					<td><input type="K�n" name="K�n"
						value="<%=request.getParameter("K�n") != null ? request
					.getParameter("K�n") : ""%>"></td>
				</tr>
				<tr>
					<td></td>
					<td><br> <input type="submit" value="Send"></td>
				</tr>
			</table>
		</form>

	</div>



</body>
</html>