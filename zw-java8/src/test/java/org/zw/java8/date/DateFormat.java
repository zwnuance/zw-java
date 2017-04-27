package org.zw.java8.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.junit.Test;

/**
 * Created by zw on 4/26/2017.
 */
public class DateFormat {

  @Test
  public void parseDate() {
    String specifyDate = "19760401";
    DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
    LocalDate formatted = LocalDate.parse(specifyDate,formatter);
    System.out.println(formatted);
  }

  @Test
  public void parseDateTime() {
    String specifyDay = "1976-04-01T00:00:01";
    LocalDateTime appleBirth = LocalDateTime.parse(specifyDay, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    System.out.println(appleBirth);
  }

  @Test
  public void formatDate() {
    DateTimeFormatter ymd = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
    String today3 = ymd.format(LocalDate.now());
    System.out.println(today3);
    DateTimeFormatter ymdhms = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分SS秒");
    String today6 = ymdhms.format(LocalDateTime.now());
    System.out.println(today6);
  }

  @Test
  public void formatDefaults() {
    LocalDateTime nowDateTime = LocalDateTime.now();
    System.out.println(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(nowDateTime));
    LocalDate localDate = LocalDate.now();
    System.out.println(DateTimeFormatter.ISO_LOCAL_DATE.format(localDate));

    LocalTime localTime = LocalTime.now();
  }
}
