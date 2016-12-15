package com.flightsearch.controller;

public class FlightMath {
  public double roundingNumberToTwoDecimals(double initialValue) {
    double intpart = Math.floor(initialValue);
    double result = initialValue;
    result = (result - intpart) * Math.pow(10, 2);
    result = Math.round(result);
    result = (result / Math.pow(10, 2)) + intpart;
    return result;
  }
}
