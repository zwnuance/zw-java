import static com.sun.btrace.BTraceUtils.*;
import com.sun.btrace.annotations.*;

@BTrace
public class TraceMethodArgsAndReturn{
   @OnMethod(
         clazz="CaseObject",
               method="execute",
                     location=@Location(Kind.RETURN)
                        )
   public static void traceExecute(@Self CaseObject instance,int sleepTime,@Return boolean result){
        println("call CaseObject.execute");
        println(strcat("sleepTime is:",str(sleepTime)));
        println(strcat("sleepTotalTime is:",str(get(field("org.pang.taobao.btrace.CaseObject","sleepTotalTime"),instance))));
        println(strcat("return value is:",str(result)));
        
      }
}
