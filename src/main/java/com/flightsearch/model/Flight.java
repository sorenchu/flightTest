package com.flightsearch.model;

import com.flightsearch.population.DataCreation;
import com.flightsearch.model.Airport;

public class Flight {
  private Airport srcAirport;
  private Airport dstAirport;
  private String flightCode;
  private double basePrice;

  public Flight(Airport srcAirport, Airport dstAirport, String flightCode,
      double basePrice) {
    // HARDCODED. This should be a query to a DB
    this.srcAirport = srcAirport;
    this.dstAirport = dstAirport;
    this.flightCode = flightCode;
    this.basePrice = basePrice;
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
