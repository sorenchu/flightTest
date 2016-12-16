package com.flightsearch.population;

import java.util.List;

import com.flightsearch.model.Airline;
import com.flightsearch.model.Airport;
import com.flightsearch.model.Flight;

public interface IDataCreation {
  public void dataPopulation();

  public void dataDeletion();

  public Airline getAirlineFromIata(String iata);

  public Airport getAirportFromCity(String city);
  
  public Airline getAirlineFromFlighCode(String flightCode);

  public List<Flight> getFlights();
}
