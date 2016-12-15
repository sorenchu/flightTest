package com.flightsearch.controller;

import java.util.ArrayList;

import com.flightsearch.model.Airline;
import com.flightsearch.model.Airport;
import com.flightsearch.model.Flight;
import com.flightsearch.model.Passengers;
import com.flightsearch.model.SearchResult;
import com.flightsearch.population.DataCreation;

public class FlightSearch {
  private static final double CHILD_DISCOUNT = 0.67;
  private Airport srcAirport;
  private Airport dstAirport;
  private String departureDate;
  private Passengers passengers;
  private FlightDates flightDate;
  private DataCreation dataCreation;
  private ArrayList<SearchResult> searchResults;

  public FlightSearch(String srcCity, String dstCity, String depDate,
      Integer adultPass, Integer childPass, Integer infantPass) {
    this.dataCreation = new DataCreation();
    this.srcAirport = dataCreation.getAirportFromCity(srcCity);
    this.dstAirport = dataCreation.getAirportFromCity(dstCity);
    this.departureDate = depDate;
    this.passengers = new Passengers(adultPass, childPass, infantPass);
    this.searchResults = new ArrayList<SearchResult>();
    this.flightDate = new FlightDates();
  }

  public String getDepartureDate() {
    return this.departureDate;
  }

  public void doSearch() {
    ArrayList<Flight> flights = new ArrayList<Flight>();
    System.out.println("Flights found");

    for (Flight flight : dataCreation.getFlights()) {
      if (this.isThisFlight(flight)) {
        this.processFlight(flight);
        flights.add(flight);
      }
    }
    if (0 < flights.size()) {
      System.out.println();
    } else {
      System.out.println("no flights available");
    }
  }

  public boolean isThisFlight(Flight flight) {
    if (this.srcAirport.equals(flight.getSrcAirport())
        && this.dstAirport.equals(flight.getDstAirport())) {
      return true;
    }
    return false;
  }

  public void processFlight(Flight flight) {
    String flightCode = flight.getFlightCode();
    double money = this.getTotalPrice(flight);
    searchResults.add(new SearchResult(flightCode, money));
    System.out.println(flightCode + ", " + money + " €");
  }

  public double getTotalPrice(Flight flight) {
    Airline airline = flight.getAirline();
    double adultAmount = passengers.getAdultPassengers() * flight.getBasePrice()
        * flightDate.getCorrection(this.departureDate);
    double childAmount = passengers.getChildPassengers() * flight.getBasePrice()
        * flightDate.getCorrection(this.departureDate) * CHILD_DISCOUNT;
    double infantAmount = passengers.getInfantPassengers() * airline.getInfantPrice();
    return roundingNumberToTwoDecimals(adultAmount + childAmount + infantAmount);
  }

  public ArrayList<SearchResult> getSearchResult() {
    return this.searchResults;
  }

  private double roundingNumberToTwoDecimals(double initialValue) {
    double intpart = Math.floor(initialValue);
    double result = initialValue;
    result = (result - intpart) * Math.pow(10, 2);
    result = Math.round(result);
    result = (result / Math.pow(10, 2)) + intpart;
    return result;
  }
}
