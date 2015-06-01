package com.jbetfair.api.entities;

public enum EventTypes {

  HANDBALL("Handball", 468328), SOCCER("Soccer", 1), TENNIS("Tennis", 2), GOLF("Golf", 3), CRICKET("Cricket", 4), RUGBY_UNION("Rugby Union", 5), BOXING(
      "Boxing", 6), HORSE_RACING("Horse Racing", 7), MOTOR_SPORT("Motor Sport", 8), ICE_HOCKEY("Ice Hockey", 7524), SPECIAL_BETS("Special Bets", 10), CYCLING(
      "Cycling", 11), CHESS("Chess", 136332), BASKETBALL("Basketball", 7522), RUGBY_LEAGUE("Rugby League", 1477), NETBALL("Netball", 606611), GREYHOUND_RACING(
      "Greyhound Racing", 4339), BADMINTON("Badminton", 627555), FINANCIAL_BETS("Financial Bets", 6231), POLITICS("Politics", 2378961), VOLLEYBALL(
      "Volleyball", 998917), BOWLS("Bowls", 998918), MIXED_MARTIAL_ARTS("Mixed Martial Arts", 26420387), DARTS("Darts", 3503), POOL("Pool", 72382), GAELIC_GAMES(
      "Gaelic Games", 2152880), SNOOKER("Snooker", 6422), AMERICAN_FOOTBALL("American Football", 6423), CURRENT_AFFAIRS("Current Affairs", 27388198), POKER(
      "Poker", 315220), BASEBALL("Baseball", 7511);

  private String name;
  private int id;

  private EventTypes(String name, int id) {
    this.name = name;
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public EventType toEventType() {
    return new EventType(Integer.toString(id), name);
  }

}
