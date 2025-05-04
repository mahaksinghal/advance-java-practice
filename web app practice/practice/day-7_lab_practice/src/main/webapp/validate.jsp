<%@page import="com.cdac.pojos.User"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<%!// JSP declaration
	private Map<String, User> users;

	// override jspInit
	public void jspInit() {
		// create empty HashMap and populate it with users
		users = new HashMap<>();
		users.put("a@gmail.com", new User("a", "a@gmail.com", "a12345", 34));
		users.put("b@gmail.com", new User("b", "b@gmail.com", "b12345", 23));
		users.put("c@gmail.com", new User("c", "c@gmail.com", "c12345", 29));
		users.put("d@gmail.com", new User("d", "d@gmail.com", "d12345", 38));
		users.put("e@gmail.com", new User("e", "e@gmail.com", "e12345", 45));
		System.out.println("JSP inited");
	}%>
<body>
	<%
	String email = request.getParameter("em");
	String password = request.getParameter("pass");
	// get user by email
	User user = users.get(email);
	if (user != null) {
		// email exists, check password
		if (user.getPassword().equals(password)) {
			// valid login --> save user details under the session scope
			session.setAttribute("user_details", user);
			// redirect client to the details.jsp
			response.sendRedirect("details.jsp");
		} else {
	%>
	<h5 style="color: red">
		Invalid Password, Please <a href="login.jsp">Retry</a>
	</h5>
	<%
	}
	} else {
	%>
	<h5 style="color: red">
		Invalid Email, Please <a href="login.jsp">Retry</a>
	</h5>
	<%
	}
	%>
</body>
</html>