package com.jbetfair.util;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * TypeAdaptor for the Date class which can be given to Gson. Betfair's API-NG
 * requires all dates to be serialized in ISO8601 UTC.
 */
public class ISO8601DateTypeAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {
  public static final String ISO_8601_FORMAT_STRING = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
  public static final String ISO_8601_TIMEZONE = "UTC";

  private DateFormat dateFormat() {
    DateFormat dateFormat = new SimpleDateFormat(ISO_8601_FORMAT_STRING);
    dateFormat.setTimeZone(TimeZone.getTimeZone(ISO_8601_TIMEZONE));
    return dateFormat;
  }

  public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    try {
      return dateFormat().parse(json.getAsString());
    } catch (ParseException e) {
      throw new JsonParseException(e);
    }
  }

  public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
    return new JsonPrimitive(dateFormat().format(src));
  }

}
