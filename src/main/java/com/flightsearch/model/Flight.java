package com.flightsearch.model;

import com.flightsearch.population.DataCreation;
import com.flightsearch.model.Airport;

public class Flight {
  private Airport srcAirport;
  private Airport dstAirport;
  private String flightCode;
  private double basePrice;
  private DataCreation dataCreation = new DataCreation();

  public Flight(String srcIata, String dstIata, String flightCode,
      double basePrice) {
    // HARDCODED. This should be a query to a DB
    this.srcAirport = dataCreation.getAirportFromIata(srcIata);
    this.dstAirport = dataCreation.getAirportFromIata(dstIata);
    this.flightCode = flightCode;
    this.basePrice = basePrice;
  }

  public Airline getAirline() {
    return dataCreation.getAirlineFromIata(flightCode.substring(0, 2));
  }

  public Airport getSrcAirport() {
    return this.srcAirport;
  }

  public Airport getDstAirport() {
    return this.dstAirport;
  }

  public double getBasePrice() {
    return this.basePrice;
  }

  public String getFlightCode() {
    return this.flightCode;
  }
}
