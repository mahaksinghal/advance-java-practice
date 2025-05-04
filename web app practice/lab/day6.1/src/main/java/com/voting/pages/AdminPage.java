package com.voting.pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CandidateListPage
 */
@WebServlet("/admin")
public class AdminPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1. set resp cont type
		response.setContentType("text/html");
		//2. get writer : PW
		try(PrintWriter pw=response.getWriter())
		{
			pw.print("Welcome admin - in admin main page.....");
		}
		
	}

}
