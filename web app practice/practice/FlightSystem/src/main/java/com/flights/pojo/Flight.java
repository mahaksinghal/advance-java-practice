package com.flights.pojo;

import java.sql.Time;
import java.util.Date;

/*
 * flight_id | airline   | source    | destination | departure_date | arrival_date | 
 * departure_time | arrival_time | no_of_stops | price |
 */
public class Flight {
	private int flight_id;
	private String airline;
	private String source;
	private String destination;
	private Date departure_date;
	private Date arrival_date;
	private Time departure_time;
	private Time arrival_time;
	private int no_of_stops;
	private double price;

	public Flight(int flight_id, String airline, String source, String destination, Date departure_date,
			Date arrival_date, Time departure_time, Time arrival_time, int no_of_stops, double price) {
		super();
		this.flight_id = flight_id;
		this.airline = airline;
		this.source = source;
		this.destination = destination;
		this.departure_date = departure_date;
		this.arrival_date = arrival_date;
		this.departure_time = departure_time;
		this.arrival_time = arrival_time;
		this.no_of_stops = no_of_stops;
		this.price = price;
	}

	@Override
	public String toString() {
		return "FlightsPOJO [flight_id=" + flight_id + ", airline=" + airline + ", source=" + source + ", destination="
				+ destination + ", departure_date=" + departure_date + ", arrival_date=" + arrival_date
				+ ", departure_time=" + departure_time + ", arrival_time=" + arrival_time + ", no_of_stops="
				+ no_of_stops + ", price=" + price + "]";
	}

	public int getFlight_id() {
		return flight_id;
	}

	public void setFlight_id(int flight_id) {
		this.flight_id = flight_id;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getDeparture_date() {
		return departure_date;
	}

	public void setDeparture_date(Date departure_date) {
		this.departure_date = departure_date;
	}

	public Date getArrival_date() {
		return arrival_date;
	}

	public void setArrival_date(Date arrival_date) {
		this.arrival_date = arrival_date;
	}

	public Time getDeparture_time() {
		return departure_time;
	}

	public void setDeparture_time(Time departure_time) {
		this.departure_time = departure_time;
	}

	public Time getArrival_time() {
		return arrival_time;
	}

	public void setArrival_time(Time arrival_time) {
		this.arrival_time = arrival_time;
	}

	public int getNo_of_stops() {
		return no_of_stops;
	}

	public void setNo_of_stops(int no_of_stops) {
		this.no_of_stops = no_of_stops;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	

}
