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

public class DataCreation {
  private List<Airport> airportList = new ArrayList<Airport>();
  private List<Airline> airlineList = new ArrayList<Airline>();
  private List<Flight> flightList = new ArrayList<Flight>();

  public void dataPopulation() {
    this.airportList.add(new Airport("MAD", "Madrid"));
    this.airportList.add(new Airport("BCN", "Barcelona"));
    this.airportList.add(new Airport("LHR", "London"));
    this.airportList.add(new Airport("CDG", "Paris"));
    this.airportList.add(new Airport("FRA", "Frakfurt"));
    this.airportList.add(new Airport("IST", "Istanbul"));
    this.airportList.add(new Airport("AMS", "Amsterdam"));
    this.airportList.add(new Airport("FCO", "Rome"));
    this.airportList.add(new Airport("CPH", "Copenhage"));

    this.airlineList.add(new Airline("IB", "Iberia", 10));
    this.airlineList.add(new Airline("BA", "British Airways", 15));
    this.airlineList.add(new Airline("LH", "Lufthansa", 7));
    this.airlineList.add(new Airline("FR", "Ryanair", 20));
    this.airlineList.add(new Airline("VY", "Vueling", 10));
    this.airlineList.add(new Airline("TK", "Turkish Airlines", 5));
    this.airlineList.add(new Airline("U2", "Easyjet", 19.90));

    populateFlights();
  }

  public void populateFlights() {
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
        this.flightList.add(new Flight(flight[0], flight[1], flight[2],
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
    for (Airline airline : this.airlineList) {
      if (iata.equals(airline.getIata())) {
        return airline;
      }
    }
    return null;
  }

  public Airport getAirportFromCity(String city) {
    for (Airport airport : this.airportList) {
      if (city.equals(airport.getCity())) {
        return airport;
      }
    }
    return null;
  }

  public Airport getAirportFromIata(String iata) {
    for (Airport airport : this.airportList) {
      if (iata.equals(airport.getIata())) {
        return airport;
      }
    }
    return null;
  }

  public List<Flight> getFlights() {
    return this.flightList;
  }

  public void dataDeletion() {
    this.airportList.clear();
    this.airlineList.clear();
    this.flightList.clear();
  }
}
