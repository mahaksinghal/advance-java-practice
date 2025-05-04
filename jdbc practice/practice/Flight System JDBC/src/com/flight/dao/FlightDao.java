package com.flight.dao;

import java.sql.SQLException;
import java.util.List;

import com.flight.core.Flight;

public interface FlightDao {
	
	List<Flight> displayFlights(String source, String destination, String departure_date) throws SQLException;
	
	String updateStopsArrTime(int flight_id, int no_of_stops, String arrival_time) throws SQLException;
	
	String deleteFlight(String airline, String source, String destination) throws SQLException;
	
	void closeResources() throws SQLException;
}
