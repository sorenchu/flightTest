package com.flightsearch.flightsearch;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.flightsearch.controller.FlightDates;
import com.flightsearch.population.DataCreation;
import com.flightsearch.population.IDataCreation;

import junit.framework.TestCase;

public class PricingCorrectionTest extends TestCase {
  private static final long ONE_DAY = 86400000;
  private static final long TWO_DAYS = 2 * ONE_DAY;
  private static final long THREE_DAYS = 3 * ONE_DAY;
  private static final long FOUR_DAYS = 4 * ONE_DAY;
  private static final long FIFTEEN_DAYS = 15 * ONE_DAY;
  private static final long SIXTEEN_DAYS = 16 * ONE_DAY;
  private static final long THIRTY_DAYS = 30 * ONE_DAY;
  private static final long THIRTY_ONE_DAYS = 31 * ONE_DAY;
  private IDataCreation dataCreation;

  protected void setUp() throws Exception {
    dataCreation = new DataCreation();
    dataCreation.dataPopulation();
  }

  public void testTomorrowCorrection() throws Exception {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    String tomorrow = dateFormat
        .format(new Date(java.lang.System.currentTimeMillis() + ONE_DAY));
    FlightDates flightDates = new FlightDates();
    assertEquals(1.5, flightDates.getCorrection(tomorrow));
  }

  public void testTwoDaysCorrection() throws Exception {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    String twoDays = dateFormat
        .format(new Date(java.lang.System.currentTimeMillis() + TWO_DAYS));
    FlightDates flightDates = new FlightDates();
    assertEquals(1.5, flightDates.getCorrection(twoDays));
  }

  public void testThreeDaysCorrection() throws Exception {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    String threeDays = dateFormat
        .format(new Date(java.lang.System.currentTimeMillis() + THREE_DAYS));
    FlightDates flightDates = new FlightDates();
    assertEquals(1.2, flightDates.getCorrection(threeDays));
  }

  public void testFourDaysCorrection() throws Exception {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    String fourDays = dateFormat
        .format(new Date(java.lang.System.currentTimeMillis() + FOUR_DAYS));
    FlightDates flightDates = new FlightDates();
    assertEquals(1.2, flightDates.getCorrection(fourDays));
  }

  public void testFifteenDaysCorrection() throws Exception {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    String fifteenDays = dateFormat
        .format(new Date(java.lang.System.currentTimeMillis() + FIFTEEN_DAYS));
    FlightDates flightDates = new FlightDates();
    assertEquals(1.2, flightDates.getCorrection(fifteenDays));
  }

  public void testSixteenDaysCorrection() throws Exception {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    String sixteenDays = dateFormat
        .format(new Date(java.lang.System.currentTimeMillis() + SIXTEEN_DAYS));
    FlightDates flightDates = new FlightDates();
    assertEquals(1.0, flightDates.getCorrection(sixteenDays));
  }

  public void testThirtyDaysCorrection() throws Exception {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    String thirtyDays = dateFormat
        .format(new Date(java.lang.System.currentTimeMillis() + THIRTY_DAYS));
    FlightDates flightDates = new FlightDates();
    assertEquals(1.0, flightDates.getCorrection(thirtyDays));
  }

  public void testThirtyOneDaysCorrection() throws Exception {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    String thirtyOneDays = dateFormat.format(
        new Date(java.lang.System.currentTimeMillis() + THIRTY_ONE_DAYS));
    FlightDates flightDates = new FlightDates();
    assertEquals(0.8, flightDates.getCorrection(thirtyOneDays));
  }

  protected void tearDown() throws Exception {
    dataCreation.dataDeletion();
  }
}
