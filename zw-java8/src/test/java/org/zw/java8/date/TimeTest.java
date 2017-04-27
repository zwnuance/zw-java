package org.zw.java8.date;

import java.time.LocalTime;
import org.junit.Test;

/**
 * Created by zw on 4/26/2017.
 */
public class TimeTest {

  @Test
  public void testCreate() {
    LocalTime now = LocalTime.now();
    LocalTime noNano = now.withNano(0);
    System.out.println(noNano);
  }
}
