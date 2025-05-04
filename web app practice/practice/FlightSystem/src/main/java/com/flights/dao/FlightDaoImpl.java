package com.flights.dao;

import static com.flights.utils.DBUtils.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flights.pojo.Flight;

public class FlightDaoImpl implements FlightDao {
	private Connection connection;
	private PreparedStatement pst1;

	public FlightDaoImpl() throws SQLException {
		System.out.println("in FlightsDaoImpl");
		connection = getConnection();
		pst1 = connection
				.prepareStatement("select * from flights where source = ? and destination = ? and departure_date = ?");
	}

//	@Override
	public List<Flight> getFlightDetails(String src, String dest, String dept_date) throws SQLException {
		
		System.out.println("in getFlightDetails");
		pst1.setString(1, src);
		pst1.setString(2, dest);
		pst1.setDate(3, Date.valueOf(dept_date));
		List<Flight> flights = new ArrayList<Flight>();

		try (ResultSet rst = pst1.executeQuery()) {
			/*
			 * flight_id | airline | source | destination | departure_date | arrival_date |
			 * departure_time | arrival_time | no_of_stops | price
			 */
			while (rst.next())
				flights.add(new Flight(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4),
						rst.getDate(5), rst.getDate(6), rst.getTime(7), rst.getTime(8), rst.getInt(9),
						rst.getDouble(10)));
		}
		return flights;
	}
	
	@Override
	public void closeResources() throws SQLException{
		System.out.println("in closeResources");
		if(pst1 != null)
			pst1.close();	
		closeConnection();
		
	}

}
