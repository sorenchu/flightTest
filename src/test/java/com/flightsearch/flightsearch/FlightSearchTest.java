package com.flightsearch.flightsearch;

import junit.framework.TestCase;

import com.flightsearch.population.DataCreation;

public class FlightSearchTest extends TestCase {

	public void setUp() {
		DataCreation.dataPopulation();
	}

	public void testPopulation() throws Exception {
		assertEquals(89, DataCreation.getFlights().size());
	}


	public void tearDown() {
		DataCreation.dataDeletion();
	}
}
