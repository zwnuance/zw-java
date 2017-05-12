package org.zw.jvm.btrace.debug;

import static com.sun.btrace.BTraceUtils.*;
import com.sun.btrace.annotations.*;
import com.sun.btrace.annotations.Export;

/**
 * Created by zw on 4/12/2017.
 */
@BTrace
public class Debug {

  @Export static long counter;

  @OnMethod(clazz = "org.zw.jvm.btrace.BtraceCase", method = "add", location = @Location(Kind.RETURN))
  public static void run(@Self Object self, int a, int b, @Return int result, @Duration long time) {
    println(strcat(strcat("parameter: a=", str(a)), strcat(", b=", str(b))));
    println(strcat("cost time: ", str(time)));
    counter++;
  }

  @OnTimer(1000)
  public static void run() {
    println(strcat("execute counter: ", str(counter)));
  }
}
