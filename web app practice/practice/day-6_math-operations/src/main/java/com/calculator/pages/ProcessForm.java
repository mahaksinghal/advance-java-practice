package com.calculator.pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProcessForm
 */
@WebServlet("/calculate")
public class ProcessForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in do-post " + getClass());

		try (PrintWriter pw = response.getWriter()) {
			double num1 = Double.parseDouble(request.getParameter("num1"));
			double num2 = Double.parseDouble(request.getParameter("num2"));
			String operation = request.getParameter("operation");
			double result = 0;
			switch (operation) {
			case "+":
				result = num1 + num2;
				break;
			case "-":
				result = num1 - num2;
				break;
			case "*":
				result = num1 * num2;
				break;
			case "/":
				result = num1 / num2;
				break;
			}

			request.setAttribute("result", result);
			RequestDispatcher rd = request.getRequestDispatcher("result");
			rd.forward(request, response);
		}
	}

}
