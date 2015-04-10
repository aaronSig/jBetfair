package com.superpixel.jbetfair;

import java.util.List;

import junit.framework.TestCase;

import com.jbetfair.JBetfair;
import com.jbetfair.api.ApiOperation;
import com.jbetfair.api.entities.MarketBook;
import com.jbetfair.api.entities.MarketCatalogue;
import com.jbetfair.api.params.MarketFilter;
import com.jbetfair.api.responses.EventTypeResult;
import com.jbetfair.api.responses.ListCompetitionResult;
import com.jbetfair.api.responses.ListCountriesResult;
import com.jbetfair.api.responses.ListEventsResult;
import com.jbetfair.api.responses.ListMarketTypesResult;
import com.jbetfair.api.responses.ListTimeRangesResult;
import com.jbetfair.api.responses.ListVenuesResult;

public class JBetfairTest 
extends TestCase {

  private JBetfair betfair = new JBetfair();

  public void testLogin() throws Exception {
    try {
      betfair.login();
    } catch (Exception e) {
      fail();
    }
  }

  public void testOperationWithRequests() throws Exception {
    assertNotNull(betfair.makeRequest(ApiOperation.LIST_EVENT_TYPES, new MarketFilter()));
  }

  public void testListEventTypes() throws Exception {
    List<EventTypeResult> result = betfair.listEventTypes();
    assertTrue(result.size() > 0);
    for (EventTypeResult e : result) {
      assertNotNull(e.getEventType().getId());
      assertNotNull(e.getEventType().getName());
      assertNotNull(e.getMarketCount());
    }
  }

  public void testListCompetitions() throws Exception {
    List<ListCompetitionResult> result = betfair.listCompetitions();
    assertTrue(result.size() > 0);
    for (ListCompetitionResult c : result) {
      assertNotNull(c.getCompetition().getId());
      assertNotNull(c.getCompetition().getName());
      assertNotNull(c.getCompetitionRegion());
      assertNotNull(c.getMarketCount());
    }
  }

  public void testListTimeRanges() throws Exception {
    List<ListTimeRangesResult> result = betfair.listTimeRanges();
    assertTrue(result.size() > 0);
    for (ListTimeRangesResult t : result) {
      assertNotNull(t.getMarketCount());
      assertNotNull(t.getTimeRange().getFrom());
      assertNotNull(t.getTimeRange().getTo());
    }
  }

  public void testListEvents() throws Exception {
    List<ListEventsResult> result = betfair.listEvents();
    assertTrue(result.size() > 0);
    for (ListEventsResult e : result) {
      assertNotNull(e.getMarketCount());
      assertNotNull(e.getEvent().getId());
      assertNotNull(e.getEvent().getName());
      assertNotNull(e.getEvent().getTimezone());
      assertNotNull(e.getEvent().getOpenDate());
    }
  }

  public void testListMarketTypes() throws Exception {
    List<ListMarketTypesResult> result = betfair.listMarketTypes();
    assertTrue(result.size() > 0);
    for (ListMarketTypesResult m : result) {
      assertNotNull(m.getMarketCount());
      assertNotNull(m.getMarketType());
    }
  }

  public void testListCountries() throws Exception {
    List<ListCountriesResult> result = betfair.listCountries();
    assertTrue(result.size() > 0);
    for (ListCountriesResult c : result) {
      assertNotNull(c.getMarketCount());
      assertNotNull(c.getCountryCode());
    }
  }

  public void testListVenues() throws Exception {
    List<ListVenuesResult> result = betfair.listVenues();
    assertTrue(result.size() > 0);
    for (ListVenuesResult v : result) {
      assertNotNull(v.getMarketCount());
      assertNotNull(v.getVenue());
    }
  }

  public void testListMarketCatalogue() throws Exception {
    List<MarketCatalogue> result = betfair.listMarketCatalogue();
    assertTrue(result.size() > 0);
    for (MarketCatalogue c : result) {
      assertNotNull(c.getMarketId());
      assertNotNull(c.getMarketName());
      assertNotNull(c.getDescription());
      assertNotNull(c.getEvent());
      assertNotNull(c.getEventType());
      assertNotNull(c.getRunners());
    }
  }

  public void testListMarketBook() throws Exception {
    List<MarketCatalogue> catalogue = betfair.listMarketCatalogue(new MarketFilter(), 1);    
    List<MarketBook> result = betfair.listMarketBook(catalogue.get(0).getMarketId());
    assertTrue(result.size() > 0);
    for (MarketBook b : result) {
      assertNotNull(b.getMarketId());
      assertNotNull(b.getStatus());
      assertNotNull(b.getBetDelay());
      assertNotNull(b.getBspReconciled());
      assertNotNull(b.getComplete());
      assertNotNull(b.getCrossMatching());
      assertNotNull(b.getInplay());
      assertNotNull(b.getIsMarketDataDelayed());
      assertNotNull(b.getLastMatchTime());
      assertNotNull(b.getNumberOfActiveRunners());
      assertNotNull(b.getNumberOfRunners());
      assertNotNull(b.getNumberOfWinners());
      assertNotNull(b.getRunners());
      assertNotNull(b.getRunnersVoidable());
      assertNotNull(b.getTotalAvailable());
      assertNotNull(b.getTotalMatched());
      assertNotNull(b.getVersion());
    }
  }

}
