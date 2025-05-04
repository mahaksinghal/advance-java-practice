package com.flights.pages;

import static com.flights.utils.DBUtils.openConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flights.dao.FlightDao;
import com.flights.dao.FlightDaoImpl;
import com.flights.pojo.Flight;

/**
 * Servlet implementation class FlightsServlet
 */
@WebServlet(value= "/authenticate",loadOnStartup = 1)
public class FlightsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FlightDao flightDao;

	/**
	 * @see Servlet#init
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("in init " + getClass());
		try {
			openConnection();
			flightDao = new FlightDaoImpl();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			throw new ServletException("err in init", e);
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("in destroy " + getClass());
		try {
			flightDao.closeResources();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("err in destror", e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in do-post " + getClass());
		response.setContentType("text/html");
		try(PrintWriter pw = response.getWriter()){
			String src = request.getParameter("src");
			String dest = request.getParameter("dest");
			String dept_date = request.getParameter("date");
			List<Flight> flights = new ArrayList<>();
			flights = flightDao.getFlightDetails(src, dest, dept_date);
//			pw.print(flights);
			if(flights != null) {
				flights.forEach(f->pw.print(f));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServletException("Error in do-post", e);
		}
	}

}




