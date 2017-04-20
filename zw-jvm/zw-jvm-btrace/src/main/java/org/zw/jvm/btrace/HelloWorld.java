package org.zw.jvm.btrace;

import com.sun.btrace.annotations.*;
import static com.sun.btrace.BTraceUtils.*;

/**
 * Created by zw on 4/12/2017.
 */
@BTrace
public class HelloWorld {
  @OnMethod(clazz = "java.lang.Thread", method = "start")
  public static void onThreadStart() {
    println("thread start");
  }
}
