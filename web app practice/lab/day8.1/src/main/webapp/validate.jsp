<%@page import="com.app.pojos.User"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<%!//JSP declaration
	private Map<String, User> users;

	//override jspInit
	public void jspInit() {
		//create empty HashMap n populate it with users
		users = new HashMap<>();
		users.put("a1@gmail.com", new User("a1 b1", "a1@gmail.com", "1234", 30));
		users.put("a2@gmail.com", new User("a2 b2", "a2@gmail.com", "2234", 25));
		System.out.println("jsp inited !");
	}%>
<body>
	<%
	//read req params
	String email = request.getParameter("em");
	String password = request.getParameter("pass");
	//get user by email
	User user = users.get(email);
	if (user != null) {
		//email exists, chk pwd
		if (user.getPassword().equals(password)) {
			//valid login -> save user details under sesison scope
			session.setAttribute("user_details", user);
			//redirect clnt to the details.jsp
			response.sendRedirect(
					response.encodeRedirectURL("details.jsp"));
			/*
			 Location - in case of cookies accepted - details.jsp
			 Location - in case of cookies blocked - details.jsp;jsessionid=DFSHSYTY654654FGH
			*/
		} else {
	%>
	<h5 style="color: red;">
		Invalid Password , Please <a href="login.jsp">Retry</a>
	</h5>
	<%
	}

	} else {
	%>
	<h5 style="color: red;">
		Invalid Email , Please <a href="login.jsp">Retry</a>
	</h5>
	<%
	}
	%>
</body>
</html>