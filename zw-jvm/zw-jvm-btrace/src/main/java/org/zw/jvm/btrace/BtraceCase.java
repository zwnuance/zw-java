package org.zw.jvm.btrace;

import java.util.Random;

/**
 * Created by zw on 4/12/2017.
 */
public class BtraceCase {
  public static Random random = new Random();
  public int size;

  public static void main(String[] args) throws Exception {
    new BtraceCase().run();
  }

  public void run() throws Exception {
    while (true) {
      add(random.nextInt(10), random.nextInt(10));
    }
  }

  public int add(int a, int b) throws Exception {
    Thread.sleep(random.nextInt(10) * 100);
    return a + b;
  }
}