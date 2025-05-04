<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<%-- Create Java Bean Instance and add it under session scope --%>
<%-- WC: session.setAttribute("user", new UserBean()) --%>
<jsp:useBean id="user" class="com.voting.service.UserBean" scope="session"/>
<%-- Tell WC to invoke setters of the java bean class --%>
<jsp:setProperty property="*" name="user"/>
<body>
<%-- Forward the client to the next page as per the outcome --%>
<h5><jsp:forward page="${sessionScope.user.authenticate()}.jsp"/></h5>
</body>
</html>