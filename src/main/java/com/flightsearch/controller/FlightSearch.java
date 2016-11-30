package com.flightsearch.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.flightsearch.model.Airline;
import com.flightsearch.model.Airport;
import com.flightsearch.model.Flight;
import com.flightsearch.model.SearchResult;
import com.flightsearch.population.DataCreation;

public class FlightSearch {
  private static final double CHILD_DISCOUNT = 0.67;
  private Airport srcAirport;
  private Airport dstAirport;
  private String departureDate;
  private Integer adultPassengers;
  private Integer childPassengers;
  private Integer infantPassenger;
  private ArrayList<SearchResult> searchResults;

  public FlightSearch(String srcCity, String dstCity, String depDate,
      Integer adultPass, Integer childPass, Integer infantPass) {
    this.srcAirport = DataCreation.getAirportFromCity(srcCity);
    this.dstAirport = DataCreation.getAirportFromCity(dstCity);
    this.departureDate = depDate;
    this.adultPassengers = adultPass;
    this.childPassengers = childPass;
    this.infantPassenger = infantPass;
    this.searchResults = new ArrayList<SearchResult>();
  }

  public String getDepartureDate() {
    return this.departureDate;
  }

  public ArrayList<Flight> doSearch() {
    ArrayList<Flight> flights = new ArrayList<Flight>();
    System.out.println("Flights found");

    for (Flight flight : DataCreation.getFlights()) {
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
    return flights;
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
    double adultAmount = this.adultPassengers * flight.getBasePrice()
        * this.getCorrection();
    double childAmount = this.childPassengers * flight.getBasePrice()
        * this.getCorrection() * CHILD_DISCOUNT;
    double infantAmount = this.infantPassenger * airline.getInfantPrice();
    return roundingNumberToTwoDecimals(adultAmount + childAmount + infantAmount);
  }

  public double getCorrection() {
    long differenceOfDays = betweenDates();
    if (31 <= differenceOfDays) {
      return 0.8;
    } else if (31 > differenceOfDays && 15 < differenceOfDays) {
      return 1.0;
    } else if (15 >= differenceOfDays && 3 <= differenceOfDays) {
      return 1.2;
    } else if (3 > differenceOfDays) {
      return 1.5;
    } else {
      return 0;
    }
  }

  public long betweenDates() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    try {
      String currentDay = dateFormat.format(new Date());
      Date asked = dateFormat.parse(this.departureDate);
      Date today = dateFormat.parse(currentDay);
      long diffInMilliSec = asked.getTime() - today.getTime();
      long numberOfDays = (diffInMilliSec / (1000 * 60 * 60 * 24)) % 365;
      return numberOfDays;
    } catch (ParseException e) {
      System.err.println("Exception throwed because of parsing date " + e);
    }
    return -1;
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
