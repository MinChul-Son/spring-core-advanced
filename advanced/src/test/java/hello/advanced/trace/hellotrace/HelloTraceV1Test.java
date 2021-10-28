package hello.advanced.trace.hellotrace;

import static org.junit.jupiter.api.Assertions.*;
import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class HelloTraceV1Test {

    @Test
    void begin_end() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("안녕 민철");
        trace.end(status);
    }

    @Test
    void begin_exception() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("안녕 민철");
        trace.exception(status, new IllegalArgumentException());
    }

}