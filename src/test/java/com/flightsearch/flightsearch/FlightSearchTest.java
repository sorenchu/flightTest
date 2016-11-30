package com.flightsearch.flightsearch;

import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.TestCase;

import com.flightsearch.controller.FlightSearch;
import com.flightsearch.population.DataCreation;

public class FlightSearchTest extends TestCase {
	private static final long ONE_DAY = 86400000;
	private static final long TWO_DAYS = 2 * ONE_DAY;
	private static final long THREE_DAYS = 3 * ONE_DAY;
	private static final long FOUR_DAYS = 4 * ONE_DAY;
	private static final long FIFTEEN_DAYS = 15 * ONE_DAY;
	private static final long SIXTEEN_DAYS = 16 * ONE_DAY;
	private static final long THIRTY_DAYS = 30 * ONE_DAY;
	private static final long THIRTY_ONE_DAYS = 31 * ONE_DAY;
	
	

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

	public void testTomorrowCorrection() throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String tomorrow = dateFormat.format(new Date(java.lang.System
				.currentTimeMillis() + ONE_DAY));
		FlightSearch flightSearch = new FlightSearch("Copenhage", "Frakfurt",
				tomorrow, 2, 1, 1);
		assertEquals(1.5, flightSearch.getCorrection());
	}
	
	public void testTwoDaysCorrection() throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String twoDays = dateFormat.format(new Date(java.lang.System
				.currentTimeMillis() + TWO_DAYS));
		FlightSearch flightSearch = new FlightSearch("Copenhage", "Frakfurt",
				twoDays, 2, 1, 1);
		assertEquals(1.5, flightSearch.getCorrection());
	}
	
	public void testThreeDaysCorrection() throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String threeDays = dateFormat.format(new Date(java.lang.System
				.currentTimeMillis() + THREE_DAYS));
		FlightSearch flightSearch = new FlightSearch("Copenhage", "Frakfurt",
				threeDays, 2, 1, 1);
		assertEquals(1.2, flightSearch.getCorrection());
	}
	
	public void testFourDaysCorrection() throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String fourDays = dateFormat.format(new Date(java.lang.System
				.currentTimeMillis() + FOUR_DAYS));
		FlightSearch flightSearch = new FlightSearch("Copenhage", "Frakfurt",
				fourDays, 2, 1, 1);
		assertEquals(1.2, flightSearch.getCorrection());
	}
	
	public void testFifteenDaysCorrection() throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String fifteenDays = dateFormat.format(new Date(java.lang.System
				.currentTimeMillis() + FIFTEEN_DAYS));
		FlightSearch flightSearch = new FlightSearch("Copenhage", "Frakfurt",
				fifteenDays, 2, 1, 1);
		assertEquals(1.2, flightSearch.getCorrection());
	}
	
	public void testSixteenDaysCorrection() throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String sixteenDays = dateFormat.format(new Date(java.lang.System
				.currentTimeMillis() + SIXTEEN_DAYS));
		FlightSearch flightSearch = new FlightSearch("Copenhage", "Frakfurt",
				sixteenDays, 2, 1, 1);
		assertEquals(1.0, flightSearch.getCorrection());
	}
	
	public void testThirtyDaysCorrection() throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String thirtyDays = dateFormat.format(new Date(java.lang.System
				.currentTimeMillis() + THIRTY_DAYS));
		FlightSearch flightSearch = new FlightSearch("Copenhage", "Frakfurt",
				thirtyDays, 2, 1, 1);
		assertEquals(1.0, flightSearch.getCorrection());
	}
	
	public void testThirtyOneDaysCorrection() throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String thirtyOneDays = dateFormat.format(new Date(java.lang.System
				.currentTimeMillis() + THIRTY_ONE_DAYS));
		FlightSearch flightSearch = new FlightSearch("Copenhage", "Frakfurt",
				thirtyOneDays, 2, 1, 1);
		assertEquals(0.8, flightSearch.getCorrection());
	}
	



	public void tearDown() {
		DataCreation.dataDeletion();
	}
}
