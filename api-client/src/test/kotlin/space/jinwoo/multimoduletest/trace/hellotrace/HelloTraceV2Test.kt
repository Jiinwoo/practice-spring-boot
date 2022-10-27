package space.jinwoo.multimoduletest.trace.hellotrace

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class HelloTraceV2Test {
    @Test
    fun begin_end() {
        // given
        val helloTrace = HelloTraceV2()
        val traceStatus = helloTrace.begin("hello")
        val beginSyncStatus = helloTrace.beginSync(traceStatus.traceId, "hello2")
        // when
        helloTrace.end(beginSyncStatus)
        helloTrace.end(traceStatus)
    }

    @Test
    fun begin_ex() {
        // given
        val helloTrace = HelloTraceV2()
        val traceStatus1 = helloTrace.begin("hello")
        val traceStatus2 = helloTrace.beginSync(traceStatus1.traceId,"hello2")
        // when
        helloTrace.exception(traceStatus2 , IllegalStateException("ex"))
        helloTrace.exception(traceStatus1 , IllegalStateException("ex"))
    }
}
