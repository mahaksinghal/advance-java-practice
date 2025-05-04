package com.admission.pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admission.pojos.Course;
import com.admission.pojos.Student;

/**
 * Servlet implementation class ProcessAdmissionForm
 */
@WebServlet("/process_admission")
public class ProcessAdmissionForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. set cont type
		response.setContentType("text/html");
		//2. get pw
		try(PrintWriter pw=response.getWriter()) {
			//3. read req params
			String firstName=request.getParameter("fn");
			String lastName=request.getParameter("ln");
			int score=Integer.parseInt(request.getParameter("score"));
			Course myCourse=Course.valueOf(request.getParameter("course"));
			Student student=new Student(firstName, lastName, score, myCourse);
			if(score>= myCourse.getMinScore())
				student.setAdmissionStatus(true);
			//4. Add student details under request scope
			request.setAttribute("student_dtls", student);
			pw.print("from the 1st page.....");
			// pw.flush();
			//RD
			RequestDispatcher rd=request.getRequestDispatcher("result");
			rd.forward(request, response);
			/*
			 * WC -forward -   Clears(discard) resp buffer
			 * include - retains the resp buffer
			 * suspends the exec of this method 
			 * Invokes - doPost of ResultPage ( /result) - can generate resp
			 * control comes back --> resp is committed | sent | rendered 
			 * 
			 */
			System.out.println("control came back.....");
			pw.print("some more contents : from 1st page....");
		}
	}

}
