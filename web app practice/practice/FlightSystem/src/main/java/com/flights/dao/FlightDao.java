package com.flights.dao;

import java.sql.SQLException;
import java.util.List;

import com.flights.pojo.Flight;

public interface FlightDao {
	
	List<Flight> getFlightDetails(String src, String dest, String dept_date) throws SQLException;
	
	void closeResources() throws SQLException;

}
