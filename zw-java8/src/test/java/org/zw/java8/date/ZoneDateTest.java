package org.zw.java8.date;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import org.junit.Test;

/**
 * Created by zw on 4/26/2017.
 */
public class ZoneDateTest {

  @Test
  public void test() {
    ZonedDateTime cttDate = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
    ZonedDateTime pstDate = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
    ZonedDateTime ectDate = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
    System.out.println(cttDate);
    System.out.println(pstDate);
    System.out.println(ectDate);
  }
}
