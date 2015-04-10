# jBetfair - Java bindings to the Betfair API

A refactor of the original Java bindings provided by Betfair.

Note: All the list API requests have been implemented. Placing and viewing orders is still TODO.

Installation
============

### Maven users

This project has yet to be published to a maven repo. In the meantime:

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>com.jbetfair</groupId>
  <artifactId>jbetfair</artifactId>
  <version>1.0</version>
</dependency>
```

Then build the source with:
`mvn install -DskipTests`

Usage
=====

The library pulls the API key, username and password from a java properties file called jbetfair located in your home directory. 

Create the following file in your home directory substituting for your values:

~/.jbetfair

```
appKey=API_KEY_HERE
username=USERNAME_HERE
password=PASSWORD_HERE
```

With the properties file in place the library will automatically login for you if you make a request and do not yet have a session key.

To use create an instance of the jBetfair class. All calls are done through that.

```java

private JBetfair betfair = new JBetfair();

public void printNextBigHorseRace() {
  MarketFilter filter = new MarketFilter()
      .withEventTypeId(7) // Horse racing
      .withMarketCountry("GB");

  List<MarketCatalogue> nextBigHorseRaceLst = jBetfair.listMarketCatalogue(filter, MarketSort.MAXIMUM_TRADED, 1);
  MarketCatalogue nextBigHorseRace = nextBigHorseRaceLst.get(0);
  MarketBook racePrices = jBetfair.listMarketBook(nextBigHorseRace.getMarketId()).get(0);

  System.out.println("The most popular traded next horse race is: " + nextBigHorseRace.getMarketName());
  System.out.println("The runners and their last traded prices are:");
  for (int i = 0; i < nextBigHorseRace.getRunners().size(); i++) {
    RunnerCatalog runnerDetails = nextBigHorseRace.getRunners().get(i);
    Runner runnerPrices = racePrices.getRunners().get(i);
    System.out.println(" (" + runnerDetails.getSelectionId() + ")  " + runnerDetails.getRunnerName() + " -> " + runnerPrices.getLastPriceTraded());
  }
}

```

For a more indepth example checkout the JBetfairTest.java class. 
