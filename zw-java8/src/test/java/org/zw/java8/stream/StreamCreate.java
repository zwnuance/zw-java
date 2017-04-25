package org.zw.java8.stream;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by zw on 4/25/2017.
 */
public class StreamCreate {

  @Before
  public void init() {

  }

  @Test
  public void newStream() {
    Stream<String> stream = Stream.of("Ash", "nazg", "durbatulûk", "ash", "nazg", "gimbatul","ash", "nazg", "thrakatulûk", "agh", "burzum-ishi", "krimpatul");
    String collect = stream.collect(Collectors.joining(","));
    System.out.println(collect);
    Stream<String> generate = Stream.generate(() -> "hello");
    Stream<Double> generate1 = Stream.generate(Math::random);
    Stream<BigInteger> iterate = Stream.iterate(BigInteger.ONE, n -> n.add(BigInteger.ONE));
  }
}
