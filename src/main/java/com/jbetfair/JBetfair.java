package com.jbetfair;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.google.common.collect.ImmutableList;
import com.jbetfair.api.ApiOperation;
import com.jbetfair.api.HttpUtil;
import com.jbetfair.api.entities.MarketBook;
import com.jbetfair.api.entities.MarketCatalogue;
import com.jbetfair.api.params.APIRequestParam;
import com.jbetfair.api.params.CurrencyCode;
import com.jbetfair.api.params.MarketFilter;
import com.jbetfair.api.params.MarketProjection;
import com.jbetfair.api.params.MarketSort;
import com.jbetfair.api.params.MatchProjection;
import com.jbetfair.api.params.OrderProjection;
import com.jbetfair.api.params.PriceProjection;
import com.jbetfair.api.params.TimeGranularity;
import com.jbetfair.api.responses.EventTypeResult;
import com.jbetfair.api.responses.GenericAPIResponse;
import com.jbetfair.api.responses.ListCompetitionResult;
import com.jbetfair.api.responses.ListCountriesResult;
import com.jbetfair.api.responses.ListEventsResult;
import com.jbetfair.api.responses.ListMarketTypesResult;
import com.jbetfair.api.responses.ListTimeRangesResult;
import com.jbetfair.api.responses.ListVenuesResult;
import com.jbetfair.api.responses.LoginResponse;
import com.jbetfair.exceptions.BetfairException;
import com.jbetfair.util.JsonConverter;
import com.jbetfair.util.JsonResponseHandler;

public class JBetfair {

  public static final boolean DEBUG = false;

  public static String locale = Locale.getDefault().toString();
  public static CurrencyCode currencyCode = CurrencyCode.GBP;

  private Properties props;

  private String appKey;
  private String sessionToken;

  private HttpUtil httpUtil = new HttpUtil();

  public static boolean isDebug() {
    return DEBUG;
  }

  public JBetfair() {
    InputStream input;
    props = new Properties();
    try {
      input = new FileInputStream(System.getProperty("user.home") + "/.jbetfair");
      props.load(input);
      input.close();
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("Unable to read ~/.jbetfair properites file", e);
    } catch (IOException e) {
      throw new IllegalStateException("Unable to read ~/.jbetfair properites file", e);
    }

    this.appKey = props.getProperty("appKey");
  }

  public String sessionToken() {
    if (this.sessionToken == null) {
      this.login();
    }
    return this.sessionToken;
  }

  public void setSessionToken(String token) {
    this.sessionToken = token;
  }

  /***
   * uses the credentials stored in ~/.jbetfair to login
   * 
   */
  public LoginResponse login() {
    return login(props.getProperty("username"), props.getProperty("password"));
  }

  public LoginResponse login(String username, String password) {
    String url = "https://identitysso.betfair.com/api/login";
    HttpPost post = new HttpPost(url);
    String resp = null;
    try {

      post.setHeader("Accept", "application/json");
      post.setHeader("Accept-Charset", "UTF-8");
      post.setHeader("X-Application", this.appKey);

      List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
      nameValuePairs.add(new BasicNameValuePair("username", username));
      nameValuePairs.add(new BasicNameValuePair("password", password));
      post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

      HttpClient httpClient = new DefaultHttpClient();

      resp = httpClient.execute(post, new JsonResponseHandler());

    } catch (UnsupportedEncodingException e1) {
      throw new IllegalStateException("Unable to process login", e1);
    } catch (ClientProtocolException e) {
      throw new IllegalStateException("Unable to process login", e);
    } catch (IOException e) {
      throw new IllegalStateException("Unable to process login", e);
    }

    LoginResponse response = JsonConverter.convertFromJson(resp, LoginResponse.class);
    if ("SUCCESS".equals(response.getStatus())) {
      this.setSessionToken(response.getToken());
    } else {
      throw new IllegalStateException("Unable to login " + response.getError());
    }

    return response;
  }

  @SuppressWarnings("unchecked")
  public <T> T makeRequest(ApiOperation operation, APIRequestParam... params) throws BetfairException {
    String result = httpUtil.makeRequest(appKey, sessionToken(), operation, params);
    GenericAPIResponse<T> container = JsonConverter.convertFromJson(result, operation.getType());
    if (container.hasError()) {
      throw container.getError().getData().getBetfairException();
    }
    return (T) container.getResult();
  }

  @SuppressWarnings("unchecked")
  public <T> T makeRequest(ApiOperation operation, Map<String, Object> parameters) throws BetfairException {
    String result = httpUtil.makeRequest(appKey, sessionToken(), operation, parameters);
    GenericAPIResponse<T> container = JsonConverter.convertFromJson(result, operation.getType());
    if (container.hasError()) {
      throw container.getError().getData().getBetfairException();
    }
    return (T) container.getResult();
  }

  // --- List Event Types ---

  /***
   * Returns a list of Event Types (i.e. Sports) associated with the markets
   * selected by the MarketFilter.
   * 
   * @return
   * @throws BetfairException
   */
  public List<EventTypeResult> listEventTypes() throws BetfairException {
    return this.listEventTypes(new MarketFilter());
  }

  /***
   * Returns a list of Event Types (i.e. Sports) associated with the markets
   * selected by the MarketFilter.
   * 
   * @param filter
   *          The filter to select desired markets. All markets that match the
   *          criteria in the filter are selected.
   * 
   * @return
   * @throws BetfairException
   */
  public List<EventTypeResult> listEventTypes(MarketFilter filter) throws BetfairException {
    return this.makeRequest(ApiOperation.LIST_EVENT_TYPES, filter);
  }

  // --- List Competitions ---

  /***
   * Returns a list of Competitions (i.e., World Cup 2013) associated with the
   * markets selected by the MarketFilter. Currently only Football markets have
   * an associated competition.
   * 
   * @return
   * @throws BetfairException
   */
  public List<ListCompetitionResult> listCompetitions() throws BetfairException {
    return this.listCompetitions(new MarketFilter());
  }

  /***
   * Returns a list of Competitions (i.e., World Cup 2013) associated with the
   * markets selected by the MarketFilter. Currently only Football markets have
   * an associated competition.
   * 
   * @param filter
   *          The filter to select desired markets. All markets that match the
   *          criteria in the filter are selected.
   * 
   * @return
   * @throws BetfairException
   */
  public List<ListCompetitionResult> listCompetitions(MarketFilter filter) throws BetfairException {
    return this.makeRequest(ApiOperation.LIST_COMPETITIONS, filter);
  }

  // --- List Time Ranges ---

  /***
   * Returns a list of time ranges in the granularity specified in the request
   * (i.e. 3PM to 4PM, Aug 14th to Aug 15th) associated with the markets
   * selected by the MarketFilter.
   * 
   * The default time granularity is days.
   * 
   * @return
   * @throws BetfairException
   */
  public List<ListTimeRangesResult> listTimeRanges() throws BetfairException {
    return this.listTimeRanges(new MarketFilter());
  }

  /***
   * Returns a list of time ranges in the granularity specified in the request
   * (i.e. 3PM to 4PM, Aug 14th to Aug 15th) associated with the markets
   * selected by the MarketFilter.
   * 
   * The default time granularity is days.
   * 
   * @param filter
   * @return
   * @throws BetfairException
   */
  public List<ListTimeRangesResult> listTimeRanges(MarketFilter filter) throws BetfairException {
    return this.listTimeRanges(filter, TimeGranularity.DAYS);
  }

  /***
   * Returns a list of time ranges in the granularity specified in the request
   * (i.e. 3PM to 4PM, Aug 14th to Aug 15th) associated with the markets
   * selected by the MarketFilter.
   * 
   * @param filter
   *          The filter to select desired markets. All markets that match the
   *          criteria in the filter are selected.
   * 
   * @param granularity
   *          The granularity of time periods that correspond to markets
   *          selected by the market filter.
   * 
   * @return
   * @throws BetfairException
   */
  public List<ListTimeRangesResult> listTimeRanges(MarketFilter filter, TimeGranularity granularity) throws BetfairException {
    return this.makeRequest(ApiOperation.LIST_TIME_RANGES, filter, granularity);
  }

  // --- List Events
  /***
   * Returns a list of Events (i.e, Reading vs. Man United) associated with the
   * markets selected by the MarketFilter.
   * 
   * @return
   * @throws BetfairException
   */
  public List<ListEventsResult> listEvents() throws BetfairException {
    return this.listEvents(new MarketFilter());
  }

  /***
   * Returns a list of Events (i.e, Reading vs. Man United) associated with the
   * markets selected by the MarketFilter.
   * 
   * @param filter
   *          The filter to select desired markets. All markets that match the
   *          criteria in the filter are selected.
   * 
   * @return
   * @throws BetfairException
   */
  public List<ListEventsResult> listEvents(MarketFilter filter) throws BetfairException {
    return this.makeRequest(ApiOperation.LIST_EVENTS, filter);
  }

  // --- List Market Types

  /***
   * Returns a list of market types (i.e. MATCH_ODDS, NEXT_GOAL) associated with
   * the markets selected by the MarketFilter. The market types are always the
   * same, regardless of locale.
   * 
   * @return
   * @throws BetfairException
   */
  public List<ListMarketTypesResult> listMarketTypes() throws BetfairException {
    return this.listMarketTypes(new MarketFilter());
  }

  /***
   * Returns a list of market types (i.e. MATCH_ODDS, NEXT_GOAL) associated with
   * the markets selected by the MarketFilter. The market types are always the
   * same, regardless of locale.
   * 
   * @param filter
   * @return
   * @throws BetfairException
   */
  public List<ListMarketTypesResult> listMarketTypes(MarketFilter filter) throws BetfairException {
    return this.makeRequest(ApiOperation.LIST_MARKET_TYPES, filter);
  }

  // --- List Countries

  /***
   * Returns a list of Countries associated with the markets selected by the
   * MarketFilter.
   * 
   * @return
   * @throws BetfairException
   */
  public List<ListCountriesResult> listCountries() throws BetfairException {
    return this.listCountries(new MarketFilter());
  }

  /***
   * Returns a list of Countries associated with the markets selected by the
   * MarketFilter.
   * 
   * @param filter
   * @return
   * @throws BetfairException
   */
  public List<ListCountriesResult> listCountries(MarketFilter filter) throws BetfairException {
    return this.makeRequest(ApiOperation.LIST_COUNTRIES, filter);
  }

  // --- List Venues

  /***
   * Returns a list of Venues (i.e. Cheltenham, Ascot) associated with the
   * markets selected by the MarketFilter. Currently, only Horse Racing markets
   * are associated with a Venue.
   * 
   * 
   * @return
   * @throws BetfairException
   */
  public List<ListVenuesResult> listVenues() throws BetfairException {
    return this.listVenues(new MarketFilter());
  }

  /***
   * Returns a list of Venues (i.e. Cheltenham, Ascot) associated with the
   * markets selected by the MarketFilter. Currently, only Horse Racing markets
   * are associated with a Venue.
   * 
   * 
   * @param filter
   *          The filter to select desired markets. All markets that match the
   *          criteria in the filter are selected.
   * 
   * @return
   * @throws BetfairException
   */
  public List<ListVenuesResult> listVenues(MarketFilter filter) throws BetfairException {
    return this.makeRequest(ApiOperation.LIST_VENUES, filter);
  }

  // --- List Market Catalogue

  /***
   * Returns a list of information about published (ACTIVE/SUSPENDED) markets
   * that does not change (or changes very rarely). You use listMarketCatalogue
   * to retrieve the name of the market, the names of selections and other
   * information about markets. Market Data Request Limits apply to requests
   * made to listMarketCatalogue.
   * 
   * Please note: listMarketCatalogue does not return markets that are CLOSED.
   * 
   * @return
   * @throws BetfairException
   */
  public List<MarketCatalogue> listMarketCatalogue() throws BetfairException {
    return this.listMarketCatalogue(new MarketFilter(), MarketProjection.allProjections(), MarketSort.MAXIMUM_TRADED, 10);
  }

  /***
   * Returns a list of information about published (ACTIVE/SUSPENDED) markets
   * that does not change (or changes very rarely). You use listMarketCatalogue
   * to retrieve the name of the market, the names of selections and other
   * information about markets. Market Data Request Limits apply to requests
   * made to listMarketCatalogue.
   * 
   * Please note: listMarketCatalogue does not return markets that are CLOSED.
   * 
   * @param filter
   *          The filter to select desired markets. All markets that match the
   *          criteria in the filter are selected.
   * @return
   * @throws BetfairException
   */
  public List<MarketCatalogue> listMarketCatalogue(MarketFilter filter) throws BetfairException {
    return this.listMarketCatalogue(filter, MarketProjection.allProjections(), MarketSort.MAXIMUM_TRADED, 10);
  }

  /***
   * Returns a list of information about published (ACTIVE/SUSPENDED) markets
   * that does not change (or changes very rarely). You use listMarketCatalogue
   * to retrieve the name of the market, the names of selections and other
   * information about markets. Market Data Request Limitsapply to requests made
   * to listMarketCatalogue.
   * 
   * Please note: listMarketCatalogue does not return markets that are CLOSED.
   * 
   * @param filter
   *          The filter to select desired markets. All markets that match the
   *          criteria in the filter are selected.
   * @param maxResults
   *          limit on the total number of results returned, must be greater
   *          than 0 and less than or equal to 1000
   * @return
   * @throws BetfairException
   */
  public List<MarketCatalogue> listMarketCatalogue(MarketFilter filter, int maxResults) throws BetfairException {
    return this.listMarketCatalogue(new MarketFilter(), MarketProjection.allProjections(), MarketSort.MAXIMUM_TRADED, maxResults);
  }

  /***
   * Returns a list of information about published (ACTIVE/SUSPENDED) markets
   * that does not change (or changes very rarely). You use listMarketCatalogue
   * to retrieve the name of the market, the names of selections and other
   * information about markets. Market Data Request Limitsapply to requests made
   * to listMarketCatalogue.
   * 
   * Please note: listMarketCatalogue does not return markets that are CLOSED.
   * 
   * @param filter
   *          The filter to select desired markets. All markets that match the
   *          criteria in the filter are selected.
   * @param sort
   *          The order of the results. Will default to RANK if not passed. RANK
   *          is an assigned priority that is determined by our Market
   *          Operations team in our back-end system. A result's overall rank is
   *          derived from the ranking given to the flowing attributes for the
   *          result. EventType, Competition, StartTime, MarketType, MarketId.
   *          For example, EventType is ranked by the most popular sports types
   *          and marketTypes are ranked in the following order: ODDS ASIAN LINE
   *          RANGE If all other dimensions of the result are equal, then the
   *          results are ranked in MarketId order.
   * @param maxResults
   *          limit on the total number of results returned, must be greater
   *          than 0 and less than or equal to 1000
   * @return
   * @throws BetfairException
   */
  public List<MarketCatalogue> listMarketCatalogue(MarketFilter filter, MarketSort sort, int maxResults) throws BetfairException {
    return this.listMarketCatalogue(filter, MarketProjection.allProjections(), sort, maxResults);
  }

  /***
   * Returns a list of information about published (ACTIVE/SUSPENDED) markets
   * that does not change (or changes very rarely). You use listMarketCatalogue
   * to retrieve the name of the market, the names of selections and other
   * information about markets. Market Data Request Limits apply to requests
   * made to listMarketCatalogue.
   * 
   * Please note: listMarketCatalogue does not return markets that are CLOSED.
   * 
   * @param filter
   *          The filter to select desired markets. All markets that match the
   *          criteria in the filter are selected.
   * @param marketProjection
   *          The type and amount of data returned about the market.
   * @param sort
   *          The order of the results. Will default to RANK if not passed. RANK
   *          is an assigned priority that is determined by our Market
   *          Operations team in our back-end system. A result's overall rank is
   *          derived from the ranking given to the flowing attributes for the
   *          result. EventType, Competition, StartTime, MarketType, MarketId.
   *          For example, EventType is ranked by the most popular sports types
   *          and marketTypes are ranked in the following order: ODDS ASIAN LINE
   *          RANGE If all other dimensions of the result are equal, then the
   *          results are ranked in MarketId order.
   * @param maxResults
   *          limit on the total number of results returned, must be greater
   *          than 0 and less than or equal to 1000
   * @return
   * @throws BetfairException
   */
  public List<MarketCatalogue> listMarketCatalogue(MarketFilter filter, Set<MarketProjection> marketProjection, MarketSort sort, int maxResults)
      throws BetfairException {
    if (maxResults < 0 || maxResults > 100) {
      throw new IllegalArgumentException("Max results should be between 0 and 100");
    }
    Map<String, Object> params = new HashMap<String, Object>();
    params.put(filter.getParameterName(), filter);
    params.put(marketProjection.iterator().next().getParameterName(), marketProjection);
    params.put(sort.getParameterName(), sort);
    params.put("maxResults", maxResults);
    return this.makeRequest(ApiOperation.LIST_MARKET_CATALOGUE, params);
  }

  // --- List Market Book
  /***
   * Returns a list of dynamic data about markets. Dynamic data includes prices,
   * the status of the market, the status of selections, the traded volume, and
   * the status of any orders you have placed in the market.
   * 
   * @param marketId
   * @return
   * @throws BetfairException
   */
  public List<MarketBook> listMarketBook(String marketId) throws BetfairException {
    return listMarketBook(ImmutableList.of(marketId), PriceProjection.defaultPriceProjection(), OrderProjection.ALL, MatchProjection.NO_ROLLUP);
  }

  /***
   * Returns a list of dynamic data about markets. Dynamic data includes prices,
   * the status of the market, the status of selections, the traded volume, and
   * the status of any orders you have placed in the market.
   * 
   * @param marketIds
   *          One or more market ids. The number of markets returned depends on
   *          the amount of data you request via the price projection.
   * @param priceProjection
   *          The projection of price data you want to receive in the response.
   * @param orderProjection
   *          The orders you want to receive in the response.
   * @param matchProjection
   *          If you ask for orders, specifies the representation of matches.
   * @return
   * @throws BetfairException
   */
  public List<MarketBook> listMarketBook(List<String> marketIds, PriceProjection priceProjection, OrderProjection orderProjection,
      MatchProjection matchProjection) throws BetfairException {
    if (marketIds == null || marketIds.size() == 0) {
      throw new IllegalArgumentException("At least 1 market ID is required");
    }

    Map<String, Object> params = new HashMap<String, Object>();
    params.put("marketIds", marketIds);
    params.put(priceProjection.getParameterName(), priceProjection);
    params.put(orderProjection.getParameterName(), orderProjection);
    params.put(matchProjection.getParameterName(), matchProjection);
    params.put(currencyCode.getParameterName(), currencyCode);

    return this.makeRequest(ApiOperation.LIST_MARKET_BOOK, params);
  }

}
