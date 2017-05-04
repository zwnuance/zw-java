package org.zw.java8;

import java.time.Month;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.junit.Test;


/**
 * Created by zw on 5/2/2017.
 */
public class DataTypeTest {

  @Test
  public void testStringJoin() {
    String join = String.join("/", "usr", "local", "bin");
    System.out.println(join);
    String ids = String.join(",", ZoneId.getAvailableZoneIds());
    System.out.println(ids);
    String joinUsingString = String.join(",", Arrays.stream(Month.values()).map(Month::toString).map(String::toLowerCase).collect(Collectors.toList()));
    System.out.println(joinUsingString);

    String joinUsingStream = Arrays.stream(Month.values())
        .map(w -> w.toString().toLowerCase())
        .collect(Collectors.joining(" -> "));
    System.out.println(joinUsingStream);
  }

  @Test(expected = ArithmeticException.class)
  public void testMath() {
    System.out.println(100000 * 100000);

    int i = Math.multiplyExact(100000, 100000);
    System.out.println(i);
  }
}
