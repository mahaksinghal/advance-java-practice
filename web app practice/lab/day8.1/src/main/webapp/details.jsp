<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h4>Login Successful !</h4>
	<h5>User Details - ${sessionScope.user_details}</h5>
	<%
	 String encURL=response.encodeURL("logout.jsp");
	%>
	<h5>
		<a href="<%= encURL %>">Log Out</a>
	</h5>
</body>
</html>