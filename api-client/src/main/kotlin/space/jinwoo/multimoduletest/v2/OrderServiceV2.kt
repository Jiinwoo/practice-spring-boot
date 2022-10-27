package space.jinwoo.multimoduletest.v2

import org.springframework.stereotype.Service
import space.jinwoo.multimoduletest.trace.TraceId
import space.jinwoo.multimoduletest.trace.hellotrace.HelloTraceV1
import space.jinwoo.multimoduletest.trace.TraceStatus
import space.jinwoo.multimoduletest.trace.hellotrace.HelloTraceV2

@Service
class OrderServiceV2(
    private val orderRepository: OrderRepositoryV2,
    private val helloTraceV2: HelloTraceV2
) {
    fun orderItem(traceId: TraceId, itemId: String) {
        var status: TraceStatus? = null
        try {
            status = helloTraceV2.beginSync(traceId, "OrderServiceV1.orderItem()")
            orderRepository.save(status!!.traceId, itemId)
            helloTraceV2.end(status)
        }catch (e: Exception) {
            helloTraceV2.exception(status!!, e)
            throw e
        }
    }
}