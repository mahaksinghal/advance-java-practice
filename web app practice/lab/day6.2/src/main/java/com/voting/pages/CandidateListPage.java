package com.voting.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.voting.dao.CandidateDao;
import com.voting.pojos.Candidate;
import com.voting.pojos.User;

/**
 * Servlet implementation class CandidateListPage
 */
@WebServlet("/list")
public class CandidateListPage extends HttpServlet {
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
			pw.print("Welcome voter - in candidate list page.....");
			pw.print("Email " + request.getParameter("em"));
			/*
			 * get user details from the HttpSession n send it to the clnt OR in case of no
			 * session - send error mesg to the clnt
			 */
			// 1.Get HttpSession from WC
			HttpSession hs = request.getSession();
			System.out.println(getClass() + " session " + hs.isNew());// false
			System.out.println(hs.getId());// same
			// 2. Get user details from session
			User userDetails = (User) hs.getAttribute("user_dtls");
			if (userDetails != null)

			{
				// get candidate dao from session
				CandidateDao candidateDao = (CandidateDao) hs.getAttribute("candidate_dao");
				pw.print("<h5>User details retrieved from Session " + userDetails + "</h5>");
				// invoke dao's method to get list
				List<Candidate> candidates = candidateDao.getAllCandidates();
				pw.print("<h5><form action='logout' method='post'>");
				// send list of candidates to the web clnt
				candidates.forEach(c -> 
				pw.print("<h5><input type='radio' name='cid' value='" + c.getCandidateId()
						+ "'/>" + c.getCandidateName() + " " + c.getPartyName() + "</h5>"));
				pw.print("<input type='submit' value='Vote'/>");
				pw.print("<h5> </form></h5>");
		
			} else {
				pw.print("<h5> NO Session - no cookies or session expired!!!!</h5>");
			}

		} catch (Exception e) {
			// throw the same exc to the caller : WC
			throw new ServletException("err in do-get ", e);
		}

	}

}
