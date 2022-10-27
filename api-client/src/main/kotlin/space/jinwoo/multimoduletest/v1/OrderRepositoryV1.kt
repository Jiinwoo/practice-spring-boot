package space.jinwoo.multimoduletest.v1

import org.springframework.stereotype.Repository
import space.jinwoo.multimoduletest.trace.hellotrace.HelloTraceV1
import space.jinwoo.multimoduletest.trace.TraceStatus

@Repository
class OrderRepositoryV1(
    private val helloTraceV1: HelloTraceV1
) {

    fun save(itemId: String) {
        var status: TraceStatus? = null
        try {
            status = helloTraceV1.begin("OrderRepositoryV1.save()")
            // save order
            if (itemId == "ex") {
                throw IllegalStateException("예외 발생")
            }
            sleep(1000L)
            helloTraceV1.end(status)
        }catch (e: Exception) {
            helloTraceV1.exception(status!!, e)
            throw e
        }
    }

    private fun sleep (millis: Long) {
        Thread.sleep(millis)
    }
}