<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>
		Welcome 2 JSP !!!!!
		<%=LocalDateTime.now()%></h3>
	<h4>
		<a href="comments.jsp">Test Comments</a>
	</h4>
	<h4>
		<a href="form.jsp">Test Scripting Elements</a>
	</h4>
</body>
</html>