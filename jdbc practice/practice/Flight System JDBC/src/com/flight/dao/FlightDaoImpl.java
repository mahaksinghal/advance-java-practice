package com.flight.dao;

import static com.flight.utils.DBUtils.closeConnection;
import static com.flight.utils.DBUtils.openConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.flight.core.Flight;

public class FlightDaoImpl implements FlightDao {
	private Connection connection;
	private PreparedStatement pst1, pst2, pst3;

	public FlightDaoImpl() throws SQLException {
		connection = openConnection();
		pst1 = connection.prepareStatement("select * from flights where source = ? and destination = ? and departure_date = ?");
		pst2 = connection.prepareStatement("update flights set no_of_stops = ? , arrival_time = ? where flight_id = ?");
		pst3 = connection.prepareStatement("delete from flights where airline = ? and source = ? and destination = ?");
	}

	/*
	 *  flight_id | airline   | source    | destination | departure_date | arrival_date | 
	 *  departure_time | arrival_time | no_of_stops | price 
	 */
	@Override
	public List<Flight> displayFlights(String source, String destination, String departure_date) throws SQLException {
		pst1.setString(1, source);
		pst1.setString(2, destination);
		pst1.setDate(3, Date.valueOf(departure_date));
		List<Flight> flights = new ArrayList<Flight>();
		try(ResultSet rst = pst1.executeQuery()){
			while(rst.next()) 
				flights.add(new Flight(rst.getInt(1), rst.getString(2), 
						rst.getString(3), rst.getString(4), rst.getDate(5), rst.getDate(6), 
						rst.getTime(7), rst.getTime(8), rst.getInt(9), rst.getDouble(10)));			
		}
		return flights;
	}
	
	@Override
	public String updateStopsArrTime(int flight_id, int no_of_stops, String arrival_time) throws SQLException {
		pst2.setInt(1, no_of_stops);
		pst2.setTime(2, Time.valueOf(arrival_time));
		pst2.setInt(3, flight_id);
		int updateCount = pst2.executeUpdate();
			if(updateCount == 1)
				return "Updated Succesfully";
		return "Updation failed";
	}
	
	@Override
	public String deleteFlight(String airline, String source, String destination) throws SQLException {
		pst3.setString(1, airline);
		pst3.setString(2, source);
		pst3.setString(3, destination);
		int deleteCount = pst3.executeUpdate();
		if(deleteCount == 1)
			return "Deletion Successful";
		return "No flight found";
		// Vistara Chennai Bangalore
	}

	@Override
	public void closeResources() throws SQLException {
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		if(pst3 != null)
			pst3.close();
		closeConnection();
		System.out.println("Closing all resources");
	}

}
