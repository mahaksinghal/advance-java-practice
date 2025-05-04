package com.flight.tester;

import java.util.List;
import java.util.Scanner;

import com.flight.core.Flight;
import com.flight.dao.FlightDao;
import com.flight.dao.FlightDaoImpl;

public class FlightTester {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			// init phase - create dao instance
			FlightDao flightDao = new FlightDaoImpl();
			boolean exit = false;
			while (!exit) {
				// client servicing phase
				System.out.println("Options 1. Display Flight Details\n"
						+ "2. Update Stops\n"
						+ "3. Delete Flight\n"
						+ "0. Exit\n");
				try {
					switch (sc.nextInt()) {
					case 1:
						System.out.println("Enter source, destination, departure date");
						List<Flight> flights = flightDao.displayFlights(sc.next(), sc.next(), sc.next());
						flights.forEach(System.out::println);
						break;
					case 2:
						System.out.println("Enter flight id, no of stops, arrival time");
						System.out.println(flightDao.updateStopsArrTime(sc.nextInt(), sc.nextInt(), sc.next()));
						break;
						
					case 3:
						System.out.println("Enter airline, source, destination");
						System.out.println(flightDao.deleteFlight(sc.nextLine(), sc.next(), sc.next()));
						break;

					case 0: // shut down (destroy)
						exit = true;
						flightDao.closeResources();
						System.out.println("Program terminated");
						break;
					}
				} catch (Exception e) {
					System.out.println(e);
					sc.nextLine();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
