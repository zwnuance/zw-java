package org.zw.java8.optional;

import java.util.Optional;
import org.junit.Test;

/**
 * Created by zw on 4/25/2017.
 */
public class FlatMapOps {

  @Test
  public void test() {

  }

  public static Optional<Double> squareRoot(Double x) {
    return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
  }
}
