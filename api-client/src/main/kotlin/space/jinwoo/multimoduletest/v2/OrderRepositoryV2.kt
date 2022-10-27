package space.jinwoo.multimoduletest.v2

import org.springframework.stereotype.Repository
import space.jinwoo.multimoduletest.trace.TraceId
import space.jinwoo.multimoduletest.trace.hellotrace.HelloTraceV1
import space.jinwoo.multimoduletest.trace.TraceStatus
import space.jinwoo.multimoduletest.trace.hellotrace.HelloTraceV2

@Repository
class OrderRepositoryV2(
    private val helloTraceV2: HelloTraceV2
) {

    fun save(traceId: TraceId, itemId: String) {
        var status: TraceStatus? = null
        try {
            status = helloTraceV2.beginSync(traceId, "OrderRepositoryV1.save()")
            // save order
            if (itemId == "ex") {
                throw IllegalStateException("예외 발생")
            }
            sleep(1000L)
            helloTraceV2.end(status)
        } catch (e: Exception) {
            helloTraceV2.exception(status!!, e)
            throw e
        }
    }

    private fun sleep (millis: Long) {
        Thread.sleep(millis)
    }
}