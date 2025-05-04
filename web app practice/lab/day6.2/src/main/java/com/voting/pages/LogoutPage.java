package com.voting.pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.voting.dao.CandidateDao;
import com.voting.dao.UserDao;
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
		//get servlet context
		ServletContext context=getServletContext();
		String url=context.getInitParameter("db_url");
		System.out.println(url+" from logout page....");
		// 1. set resp cont type
		response.setContentType("text/html");
		// 2. get writer : PW
		try (PrintWriter pw = response.getWriter()) {
			pw.print(" in logout page.....");
			// greet user ! Hello , user name
			// 1. get HttpSession from WC
			HttpSession session = request.getSession();
			System.out.println(getClass() + " session " + session.isNew());// false
			System.out.println("Session ID " + session.getId());// same session id
			// get user details from session scope
			User user = (User) session.getAttribute("user_dtls");
			if (user != null) {
				pw.print("<h5> Hello , " + user.getFirstName() + " " + user.getLastName() + "</h5>");
				pw.print("<h6> You have already voted !!!!!!!!</h6>");
				pw.print("<h5> You have logged out....</h5>");
			} else {
				pw.print("<h5> NO existing Session - no cookies or session expired!!!!</h5>");
			}
			// 2. invalidate session
			session.invalidate();
		}

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		//set cont type
		resp.setContentType("text/html");
		//pw
		try(PrintWriter pw=resp.getWriter()) {
		// get session 
			HttpSession session=request.getSession();
		//get user , daos
			User user=(User) session.getAttribute("user_dtls");
			UserDao userDao=(UserDao) session.getAttribute("user_dao");
			CandidateDao candidateDao=(CandidateDao) session.getAttribute("candidate_dao");
			//invoke user dao's method - update voting status - voter id
			String updateVotingStatus = userDao.updateVotingStatus(user.getUserId());
			pw.print("<h6> "+updateVotingStatus+"</h6>");
			//invoke candidate dao's method - incr votes - candidate id
			int candidateId=Integer.parseInt(request.getParameter("cid"));
			String incrementVotes = candidateDao.incrementVotes(candidateId);
			pw.print("<h6> "+incrementVotes+"</h6>");
			//invalidate session
			session.invalidate();
			pw.print("<h6> You have logged out....</h6>");

		} catch (Exception e) {
			throw new ServletException("err in do-post ", e);
		}
	}

}
