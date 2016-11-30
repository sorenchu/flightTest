package com.flightsearch.model;

public class SearchResult {
  private String flightCode;
  private double totalMoney;

  public SearchResult(String flightCode, double money) {
    this.flightCode = flightCode;
    this.totalMoney = money;
  }

  public String getFlightCode() {
    return this.flightCode;
  }

  public double getTotalMoney() {
    return this.totalMoney;
  }

  @Override
  public boolean equals(Object object) {
    SearchResult searchResult = (SearchResult) object;
    if (searchResult.getFlightCode().equals(this.getFlightCode())
        && searchResult.getTotalMoney() == this.getTotalMoney()) {
      return true;
    }
    return false;
  }
}
