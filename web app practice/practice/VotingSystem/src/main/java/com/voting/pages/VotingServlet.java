package com.voting.pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VotingServlet
 */
@WebServlet(value = "/authenticate", loadOnStartup = 1)
public class VotingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init()
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("in init" + getClass());
		// DAO Instance
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("in destroy" + getClass());
		// DAO's CleanUp
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in do-post" + getClass());
		response.setContentType("text/html");
		try(PrintWriter pw = response.getWriter()){
			pw.print("DAO's sign in method....." + request.getParameter("em") + " " + request.getParameter("pass"));
		}
	}

}
