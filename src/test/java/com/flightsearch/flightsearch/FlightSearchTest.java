package com.flightsearch.flightsearch;

import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.TestCase;

import com.flightsearch.controller.FlightSearch;
import com.flightsearch.population.DataCreation;

public class FlightSearchTest extends TestCase {
  private static final long ONE_DAY = 86400000;

  public void setUp() {
    DataCreation.dataPopulation();
  }

  public void testPopulation() throws Exception {
    assertEquals(89, DataCreation.getFlights().size());
  }

  public void testDifferenceOfDates() throws Exception {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    String tomorrow = dateFormat.format(new Date(java.lang.System
        .currentTimeMillis() + ONE_DAY));
    FlightSearch flightSearch1 = new FlightSearch("Copenhage", "Frakfurt",
        tomorrow, 2, 1, 1);
    assertEquals(1, flightSearch1.betweenDates());
  }



  public void tearDown() {
    DataCreation.dataDeletion();
  }
}
