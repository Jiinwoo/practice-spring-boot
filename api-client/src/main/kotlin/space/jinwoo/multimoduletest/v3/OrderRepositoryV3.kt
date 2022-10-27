package space.jinwoo.multimoduletest.v3

import org.springframework.stereotype.Repository
import space.jinwoo.multimoduletest.trace.TraceId
import space.jinwoo.multimoduletest.trace.TraceStatus
import space.jinwoo.multimoduletest.trace.hellotrace.HelloTraceV2
import space.jinwoo.multimoduletest.trace.logtrace.LogTrace

@Repository
class OrderRepositoryV3(
    private val logTrace: LogTrace
) {

    fun save(itemId: String) {
        var status: TraceStatus? = null
        try {
            status = logTrace.begin( "OrderRepositoryV1.save()")
            // save order
            if (itemId == "ex") {
                throw IllegalStateException("예외 발생")
            }
            sleep(1000L)
            logTrace.end(status)
        } catch (e: Exception) {
            logTrace.exception(status!!, e)
            throw e
        }
    }

    private fun sleep (millis: Long) {
        Thread.sleep(millis)
    }
}