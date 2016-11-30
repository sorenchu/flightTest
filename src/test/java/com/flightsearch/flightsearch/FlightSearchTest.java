package com.flightsearch.flightsearch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import junit.framework.TestCase;

import com.flightsearch.controller.FlightSearch;
import com.flightsearch.model.SearchResult;
import com.flightsearch.population.DataCreation;

public class FlightSearchTest extends TestCase {
  private static final long ONE_DAY = 86400000;
  private static final long TWO_DAYS = 2 * ONE_DAY;
  private static final long FIFTEEN_DAYS = 15 * ONE_DAY;
  private static final long THIRTY_ONE_DAYS = 31 * ONE_DAY;
  private SimpleDateFormat dateFormat;

  public void setUp() {
    DataCreation.dataPopulation();
    dateFormat = new SimpleDateFormat("MM/dd/yyyy");
  }

  public void testPopulation() throws Exception {
    assertEquals(89, DataCreation.getFlights().size());
  }

  public void testDifferenceOfDates() throws Exception {
    String tomorrow = dateFormat.format(new Date(java.lang.System
        .currentTimeMillis() + ONE_DAY));
    FlightSearch flightSearch1 = new FlightSearch("Copenhage", "Frakfurt",
        tomorrow, 2, 1, 1);
    assertEquals(1, flightSearch1.betweenDates());
  }

  public void testSearch1() throws Exception {
    ArrayList<SearchResult> wantedResults = new ArrayList<SearchResult>();
    wantedResults.add(new SearchResult("TK2372", 157.6));
    wantedResults.add(new SearchResult("TK2659", 198.4));
    wantedResults.add(new SearchResult("LH5909", 90.4));

    String thiryOneDays = dateFormat.format(new Date(java.lang.System
        .currentTimeMillis() + THIRTY_ONE_DAYS));
    FlightSearch flightSearch = new FlightSearch("Amsterdam", "Frakfurt",
        thiryOneDays, 1, 0, 0);
    flightSearch.doSearch();
    ArrayList<SearchResult> searchResults = flightSearch.getSearchResult();
    assertTrue(wantedResults.containsAll(searchResults));
  }

  public void testSearch2() throws Exception {
    ArrayList<SearchResult> wantedResults = new ArrayList<SearchResult>();
    wantedResults.add(new SearchResult("TK8891", 806));
    wantedResults.add(new SearchResult("LH1085", 481.19));

    String fifteenDays = dateFormat.format(new Date(java.lang.System
        .currentTimeMillis() + FIFTEEN_DAYS));
    FlightSearch flightSearch = new FlightSearch("London", "Istanbul",
        fifteenDays, 2, 1, 1);
    flightSearch.doSearch();
    ArrayList<SearchResult> searchResults = flightSearch.getSearchResult();
    assertTrue(wantedResults.containsAll(searchResults));
  }

  public void testSearch3() throws Exception {
    ArrayList<SearchResult> wantedResults = new ArrayList<SearchResult>();
    wantedResults.add(new SearchResult("IB2171", 929.09));
    wantedResults.add(new SearchResult("LH5496", 1042.43));

    String twoDays = dateFormat.format(new Date(java.lang.System
        .currentTimeMillis() + TWO_DAYS));
    FlightSearch flightSearch = new FlightSearch("Barcelona", "Madrid",
        twoDays, 1, 2, 2);
    flightSearch.doSearch();
    ArrayList<SearchResult> searchResults = flightSearch.getSearchResult();
    assertTrue(wantedResults.containsAll(searchResults));
  }

  public void testSearchNotFound() throws Exception {
    ArrayList<SearchResult> wantedResults = new ArrayList<SearchResult>();
    wantedResults.add(new SearchResult("IB2171", 929.09));
    wantedResults.add(new SearchResult("LH5496", 1042.43));

    String twoDays = dateFormat.format(new Date(java.lang.System
        .currentTimeMillis() + TWO_DAYS));
    FlightSearch flightSearch = new FlightSearch("Paris", "Frakfurt", twoDays,
        1, 0, 0);
    flightSearch.doSearch();
    ArrayList<SearchResult> searchResults = flightSearch.getSearchResult();
    assertEquals(0, searchResults.size());
  }

  public void tearDown() {
    DataCreation.dataDeletion();
  }
}
