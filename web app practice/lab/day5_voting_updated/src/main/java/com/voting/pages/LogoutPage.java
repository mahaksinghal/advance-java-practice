package com.voting.pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.voting.pojos.User;

/**
 * Servlet implementation class CandidateListPage
 */
@WebServlet("/logout")
public class LogoutPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. set resp cont type
		response.setContentType("text/html");
		// 2. get writer : PW
		try (PrintWriter pw = response.getWriter()) {
			pw.print(" in logout page.....");
			// greet user!!!!! Hello, user name
			// 1. get HttpSession from WC
			HttpSession session = request.getSession();
			System.out.println(getClass() + " session " + session.isNew());// false
			System.out.println("Session ID " + session.getId());//same session id
			
			// get user details from session scope
			User user = (User)session.getAttribute("user_dtls");
			if(user!=null) {
				pw.print("<h5>Hi " + user.getFirstName() + " " + user.getLastName() + "</h5>");
				pw.print("<h5> You have logged out....</h5>");
			}
			else {
				pw.print("<h5> No existing session found---no cookies or session expired</h5>");
			}
			
			//2. invalidate session
			session.invalidate();
		}

	}

}
