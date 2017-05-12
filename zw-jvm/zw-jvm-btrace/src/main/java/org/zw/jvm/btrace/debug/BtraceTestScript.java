package org.zw.jvm.btrace.debug;

import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;

/**
 * Created by zw on 5/12/2017.
 * btrace [pid] BtraceTestScript.java
 */
@BTrace
public class BtraceTestScript {

  @OnMethod(
      clazz = "org.zw.jvm.btrace.BtraceTest",
      method = "add",
      location = @Location(Kind.RETURN)
  )
  public static void func(@Self Object instance, int a, int b, @Return int result) {
    println("call stack trace");
    jstack();
    println(strcat(strcat("parameter A is ", str(a)), strcat(", parameter B is ", str(b))));
    println(strcat("result is ", str(result)));
  }
}
