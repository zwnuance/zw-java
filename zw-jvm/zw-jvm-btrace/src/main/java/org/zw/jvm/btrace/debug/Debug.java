package org.zw.jvm.btrace.debug;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

/**
 * Created by zw on 4/12/2017.
 */
@BTrace
public class Debug {

  @Export static long counter;

  @OnMethod(clazz = "BtraceCase", method = "add", location = @Location(Kind.RETURN))
  public static void run(@Self Object self, int a, int b, @Return int result, @Duration long time) {
    BTraceUtils.println("parameter: a=" + a + ", b=" + b);
    BTraceUtils.println("cost time: " + time);
    counter++;
  }

  @OnTimer(1000)
  public static void run() {
    BTraceUtils.println("execute counter: " + counter);
  }
}
