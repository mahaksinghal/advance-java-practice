<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h5>Candidate List Page</h5>
<h6>Hello, ${sessionScope.user.userDetails.firstName} ${sessionScope.user.userDetails.lastName}</h6>
<h6>Your Role - ${sessionScope.user.userDetails.role}</h6>
</body>
</html>