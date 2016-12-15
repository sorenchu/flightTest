package com.flightsearch.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FlightDates {
  public double getCorrection(String departureDate) {
    long differenceOfDays = betweenDates(departureDate);
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

  public long betweenDates(String departureDate) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    try {
      String currentDay = dateFormat.format(new Date());
      Date asked = dateFormat.parse(departureDate);
      Date today = dateFormat.parse(currentDay);
      long diffInMilliSec = asked.getTime() - today.getTime();
      long numberOfDays = (diffInMilliSec / (1000 * 60 * 60 * 24)) % 365;
      return numberOfDays;
    } catch (ParseException e) {
      System.err.println("Exception throwed because of parsing date " + e);
      return -1;
    }
  }
}
