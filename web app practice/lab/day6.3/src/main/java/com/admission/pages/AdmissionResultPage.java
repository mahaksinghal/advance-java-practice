package com.admission.pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admission.pojos.Student;

/**
 * Servlet implementation class AdmissionResultPage
 */
@WebServlet("/result")
public class AdmissionResultPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. set cont type
		response.setContentType("text/html");
		// 2. get pw

		PrintWriter pw = response.getWriter();
		// 3. get student details from the request scope
		Student s1 = (Student) request.getAttribute("student_dtls");
		pw.print("<h5> Hello " + s1.getFirstName() + " " + s1.getLastName() + "</h5>");
		if (s1.isAdmissionStatus())
			pw.print("<h5> Congratulations ! , You are admitted in course " + s1.getChosenCourse() + " </h5>");
		else
			pw.print("<h5> Sorry ! , You can't be admitted in course " + s1.getChosenCourse() + " </h5>");

	}// control rets to the caller - doPost of the 1st page
}
