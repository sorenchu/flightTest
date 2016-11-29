package com.flightsearch.model;

public class Airport {
	private String IATA;
	private String city;

	public Airport(String iata, String city) {
		this.IATA = iata;
		this.city = city;
	}

	public String getIata() {
		return this.IATA;
	}

	public String getCity() {
		return this.city;
	}
}
