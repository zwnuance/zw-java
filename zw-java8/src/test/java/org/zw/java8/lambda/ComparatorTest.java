package org.zw.java8.lambda;

import java.util.Arrays;
import java.util.Comparator;
import org.junit.Test;

/**
 * Created by zw on 4/19/2017.
 */
public class ComparatorTest implements Comparator<String> {
  @Override public int compare(String first, String second) {
    return Integer.compare(first.length(), second.length());
  }

  public void noLambda(String[] strArray) {
    Arrays.sort(strArray, new ComparatorTest());
  }

  public void usingLambda(String[] strArray) {
    Arrays.sort(strArray,(String first, String second) -> Integer.compare(first.length(), second.length()));
  }

  @Test
  public void test() throws Exception {
    String [] strArray = {"123", "23234", "234", "", "1231231", "12"};
    String [] strArray2 = {"123", "23234", "234", "", "1231231", "12"};

    noLambda(strArray);
    usingLambda(strArray2);

    for (int i = 0; i < strArray.length; i++) {
      if(strArray[i] != strArray2[i]) {
        throw new Exception("not equals");
      }
    }
  }
}
