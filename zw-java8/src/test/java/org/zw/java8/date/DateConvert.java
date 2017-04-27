package org.zw.java8.date;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;
import org.junit.Test;

/**
 * Created by zw on 4/26/2017.
 */
public class DateConvert {

  @Test
  public void new2Old() {
    //instant
    Instant instant = Instant.now();
    Date oldDate = Date.from(instant);
    Timestamp oldTimestamp = Timestamp.from(instant);

    //LocalDate
    LocalDate localDate = LocalDate.now();
    java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

    //LocalDateTime
    LocalDateTime localDateTime = LocalDateTime.now();
    oldTimestamp = Timestamp.valueOf(localDateTime);

    //ZonedDateTime
    ZonedDateTime newZone = ZonedDateTime.now();

  }

  @Test
  public void old2New() {
    //Date
    Date oldDate = new Date();
    Instant instant = oldDate.toInstant();

    //Timestamp
    Timestamp timestamp = new Timestamp(oldDate.getTime());
    LocalDateTime localDateTime = timestamp.toLocalDateTime();

    java.sql.Date date = new java.sql.Date(oldDate.getTime());
    LocalDate localDate = date.toLocalDate();

    //TimeZone
    TimeZone timeZone = TimeZone.getDefault();
    ZoneId zoneId = timeZone.toZoneId();
  }
}
