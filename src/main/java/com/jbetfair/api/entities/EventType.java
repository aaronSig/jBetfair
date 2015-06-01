package com.jbetfair.api.entities;

public class EventType {
  private String id;
  private String name;

  public EventType() {
  }

  public EventType(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String toString() {
    return "{" + "" + "id=" + getId() + "," + "name=" + getName() + "}";
  }

}
