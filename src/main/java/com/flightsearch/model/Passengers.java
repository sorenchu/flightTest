package com.flightsearch.model;

public class Passengers {
  private Integer adultPassengers;
  private Integer childPassengers;
  private Integer infantPassenger;
  
  public Passengers(Integer adults, Integer children, Integer infants) {
    this.adultPassengers = adults;
    this.childPassengers = children;
    this.infantPassenger = infants;
  }
  
  public Integer getAdultPassengers() {
    return this.adultPassengers;
  }
  
  public Integer getChildPassengers() {
    return this.childPassengers;
  }
  
  public Integer getInfantPassengers() {
    return this.infantPassenger;
  }
}
