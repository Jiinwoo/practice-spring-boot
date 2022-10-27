package space.jinwoo.multimoduletest.trace.logtrace

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class FieldLogTraceTest {

    @Test
    fun begin_env_level2() {
        val fieldLogTrace = FieldLogTrace()
        val status1 = fieldLogTrace.begin("hello1")
        val status2 = fieldLogTrace.begin("hello2")
        fieldLogTrace.end(status2)
        fieldLogTrace.end(status1)
    }

    @Test
    fun begin_ex_level2() {
        val fieldLogTrace = FieldLogTrace()
        val status1 = fieldLogTrace.begin("hello1")
        val status2 = fieldLogTrace.begin("hello2")
        fieldLogTrace.exception(status2, IllegalStateException(""))
        fieldLogTrace.exception(status1, IllegalStateException(""))
    }

}