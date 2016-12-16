package com.flightsearch.population;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.flightsearch.model.Airline;
import com.flightsearch.model.Airport;
import com.flightsearch.model.Flight;

public class DataCreation implements IDataCreation {
  private List<Airport> airportList = new ArrayList<Airport>();
  private List<Airline> airlineList = new ArrayList<Airline>();
  private List<Flight> flightList = new ArrayList<Flight>();

  public void dataPopulation() {
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

    this.populateFlights();
  }

  private void populateFlights() {
    String csvFile = "./data.csv";
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
        Airport srcAirport = this.getAirportFromIata(flight[0]);
        Airport dstAirport = this.getAirportFromIata(flight[1]);
        flightList.add(new Flight(srcAirport, dstAirport, flight[2],
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

  public Airline getAirlineFromIata(String iata) {
    for (Airline airline : airlineList) {
      if (iata.equals(airline.getIata())) {
        return airline;
      }
    }
    return null;
  }

  public Airport getAirportFromCity(String city) {
    for (Airport airport : airportList) {
      if (city.equals(airport.getCity())) {
        return airport;
      }
    }
    return null;
  }

  public Airport getAirportFromIata(String iata) {
    for (Airport airport : airportList) {
      if (iata.equals(airport.getIata())) {
        return airport;
      }
    }
    return null;
  }

  public Airline getAirlineFromFlighCode(String flightCode) {
    return this.getAirlineFromIata(flightCode.substring(0, 2));
  }

  public List<Flight> getFlights() {
    return flightList;
  }

  public void dataDeletion() {
    airportList.clear();
    airlineList.clear();
    flightList.clear();
  }
}
