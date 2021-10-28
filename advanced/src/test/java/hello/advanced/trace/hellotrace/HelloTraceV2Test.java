package hello.advanced.trace.hellotrace;

import static org.junit.jupiter.api.Assertions.*;
import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class HelloTraceV2Test {

    @Test
    void begin_end() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("안녕 민철");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "안녕 민철 2");
        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void begin_exception() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("안녕 민철");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(),"안녕 민철");
        trace.exception(status2, new IllegalArgumentException());
        trace.exception(status1, new IllegalArgumentException());
    }

}