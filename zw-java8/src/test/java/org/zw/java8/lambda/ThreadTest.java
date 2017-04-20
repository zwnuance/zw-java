package org.zw.java8.lambda;

import org.junit.Test;

/**
 * Created by zw on 4/19/2017.
 */
public class ThreadTest implements Runnable {

  @Override public void run() {
    for (int i = 0; i < 1000; i++) {
      doWork();
    }
  }

  public void doWork() {
    System.out.println(Thread.currentThread().getName());
  }

  @Test
  public void noLambda() {
    ThreadTest w = new ThreadTest();
    new Thread(w).start();
  }

  @Test
  public void useLambda() {
    new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        doWork();
      }
    });
  }
  @Test
  public void repeatMessage(String text, int count) {
    Runnable r = () -> {
      for (int i = 0; i < count; i++) {
        System.out.println(count + "\t" + text);
        Thread.yield();
      }
    };
    new Thread(r).start();
  }

  /**
   * 被lambda捕获的变量不能修改
   * @param args
   */
  //public void modifyVariable(String text, int count) {
  //  Runnable r = () -> {
  //    while (count > 0) {
  //      count--;
  //      System.out.println(text);
  //      Thread.yield();
  //    }
  //  };
  //  new Thread(r).start();
  //}
}
