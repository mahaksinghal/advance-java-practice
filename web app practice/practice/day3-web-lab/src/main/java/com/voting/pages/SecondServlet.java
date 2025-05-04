package com.voting.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */

public class SecondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */


	/**
	 * @see Servlet#init
	 */
	public void init() throws ServletException {
		System.out.println("in init" + getClass());
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("in destroy" + getClass());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in do-get" + getClass());
		// 1. Set HTTP response content type
		response.setContentType("text/html");
		// 2. Get the PrintWriter, to send the text response
		try(PrintWriter pw = response.getWriter()){
			pw.print("<h4>Welcome to Servlet" + getClass() + "@" + new Date() + "</h4>");
		} // pw.close() --> pw.flush() --> sends the response to the client
	}
}
