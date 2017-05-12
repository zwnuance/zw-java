package org.zw.jvm.btrace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by zw on 5/12/2017.
 * jps -l
 */
public class BtraceTest {

  public int add(int a, int b) {
    return a + b;
  }

  public static void main(String[] args) throws IOException {
    System.out.println("started...");
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    BtraceTest test = new BtraceTest();
    for (int i = 0; i < 10; i++) {
      reader.readLine();
      int a = (int)Math.round(Math.random() * 1000);
      int b = (int)Math.round(Math.random() * 1000);
      test.add(a, b);
    }
  }
}
