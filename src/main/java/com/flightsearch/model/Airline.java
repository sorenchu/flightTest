package com.flightsearch.model;

public class Airline {
	private String IATA;
	private String name;
	private double infantPrice;

	public Airline(String iata, String name) {
		this.IATA = iata;
		this.name = name;
		this.infantPrice = 0;
	}

	public Airline(String iata, String name, double infantPrice) {
		this.IATA = iata;
		this.name = name;
		this.infantPrice = infantPrice;
	}

	public String getName() {
		return this.name;
	}

	public String getIata() {
		return this.IATA;
	}

	public double getInfantPrice() {
		return this.infantPrice;
	}

}
