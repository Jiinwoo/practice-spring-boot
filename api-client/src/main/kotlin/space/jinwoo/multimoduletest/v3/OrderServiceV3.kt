package space.jinwoo.multimoduletest.v3

import org.springframework.stereotype.Service
import space.jinwoo.multimoduletest.trace.TraceId
import space.jinwoo.multimoduletest.trace.TraceStatus
import space.jinwoo.multimoduletest.trace.hellotrace.HelloTraceV2
import space.jinwoo.multimoduletest.trace.logtrace.LogTrace

@Service
class OrderServiceV3(
    private val orderRepository: OrderRepositoryV3,
    private val logTrace: LogTrace
) {
    fun orderItem(itemId: String) {
        var status: TraceStatus? = null
        try {
            status = logTrace.begin( "OrderServiceV1.orderItem()")
            orderRepository.save(itemId)
            logTrace.end(status)
        }catch (e: Exception) {
            logTrace.exception(status!!, e)
            throw e
        }
    }
}