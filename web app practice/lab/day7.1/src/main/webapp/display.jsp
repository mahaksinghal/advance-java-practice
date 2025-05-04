<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<%! //JSP declaration block
	//implicitly private
	// outside service method cannot be inside a method
	int visits;

	int updateVisits() {
		return ++visits;
	}
	//override jspInit n jspDestroy
	public void jspInit() {
		System.out.println("in jspInit "+Thread.currentThread());
	}
	%>
<body>

	<%--Render email n password to the client using scriptlet --%>
	<h4>Display details using JSP Scriptlet</h4>
	<%
	//JSP scriptlet - _jspService
	System.out.println("in _jspService "+Thread.currentThread());
	String email = request.getParameter("em");
	String password = request.getParameter("pass");
	//render
	out.print("<h5> Email -" + email + "</h5>");
	out.print("<h5> PWD -" + password + "</h5>");
	%>
	<h4>Display details using JSP Expression</h4>
	<h5>
		Email -
		<%=request.getParameter("em")%></h5>
	<h5>
		Password -
		<%=request.getParameter("pass")%></h5>
	<hr />

	<h4>Display details using JSP EL Syntax</h4>
	<h5>Email - ${param.em}</h5>
	<h5>Password - ${param.pass}</h5>
	<hr />
	<h4>Solve</h4>
	<h5><%=page%></h5>
	<h5><%=pageContext%></h5>
	<h5><%=session.getId()%></h5>
	<h5>${pageContext}</h5>
	<%-- <h5><%= exception %></h5> --%>
	<h5>${param}</h5>
	<h5>Visits - <%= updateVisits() %></h5>
</body>
<%!
// JSP declaration block
public void jspDestroy() {
	System.out.println("in jspDestroy "+Thread.currentThread());
}
%>
</html>