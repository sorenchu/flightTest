package com.flightsearch.population;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.flightsearch.model.*;

public final class DataCreation {
	private static ArrayList<Airport> airportList = new ArrayList<Airport>();
	private static ArrayList<Airline> airlineList = new ArrayList<Airline>();
	private static ArrayList<Flight> flightList = new ArrayList<Flight>();

	public static void dataPopulation() {
		airportList.add(new Airport("MAD", "Madrid"));
		airportList.add(new Airport("BCN", "Barcelona"));
		airportList.add(new Airport("LHR", "London"));
		airportList.add(new Airport("CDG", "Paris"));
		airportList.add(new Airport("FRA", "Frakfurt"));
		airportList.add(new Airport("IST", "Istanbul"));
		airportList.add(new Airport("AMS", "Amsterdam"));
		airportList.add(new Airport("FCO", "Rome"));
		airportList.add(new Airport("CPH", "Copenhage"));

		airlineList.add(new Airline("IB", "Iberia", 10));
		airlineList.add(new Airline("BA", "British Airways", 15));
		airlineList.add(new Airline("LH", "Lufthansa", 7));
		airlineList.add(new Airline("FR", "Ryanair", 20));
		airlineList.add(new Airline("VY", "Vueling", 10));
		airlineList.add(new Airline("TK", "Turkish Airlines", 5));
		airlineList.add(new Airline("U2", "Easyjet", 19.90));

		populateFlights();
	}

	public static void populateFlights() {
		String csvFile = "/home/antonio/Descargas/senior-java-developer-assignment-master/data.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {
			br = new BufferedReader(new FileReader(csvFile));
			boolean skipFirstLine = false;
			while ((line = br.readLine()) != null) {
				if (!skipFirstLine) {
					line = br.readLine();
					skipFirstLine = true;
				}
				// use comma as separator
				String[] flight = line.split(cvsSplitBy);
				flightList.add(new Flight(flight[0], flight[1], flight[2],
						Float.parseFloat(flight[3])));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static Airline getAirlineFromIata(String iata) {
		for (Airline airline : airlineList) {
			if (iata.equals(airline.getIata())) {
				return airline;
			}
		}
		return null;
	}

	public static Airport getAirportFromCity(String city) {
		for (Airport airport : airportList) {
			if (city.equals(airport.getCity())) {
				return airport;
			}
		}
		return null;
	}

	public static Airport getAirportFromIata(String iata) {
		for (Airport airport : airportList) {
			if (iata.equals(airport.getIata())) {
				return airport;
			}
		}
		return null;
	}

	public static ArrayList<Flight> getFlights() {
		return flightList;
	}

	public static void dataDeletion() {
		airportList.clear();
		airlineList.clear();
		flightList.clear();
	}
}
