package space.jinwoo.multimoduletest.trace.hellotrace

import org.junit.jupiter.api.Test
import space.jinwoo.multimoduletest.trace.hellotrace.HelloTraceV1

internal class HelloTraceV1Test {
    @Test
    fun begin_end() {
        // given
        val helloTrace = HelloTraceV1()
        val traceStatus = helloTrace.begin("hello")
        // when
        helloTrace.end(traceStatus)
    }

    @Test
    fun begin_ex() {
        // given
        val helloTrace = HelloTraceV1()
        val traceStatus = helloTrace.begin("hello")
        // when
        helloTrace.exception(traceStatus, IllegalStateException("ex"))
    }

}