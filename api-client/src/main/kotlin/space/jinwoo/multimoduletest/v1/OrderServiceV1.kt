package space.jinwoo.multimoduletest.v1

import org.springframework.stereotype.Service
import space.jinwoo.multimoduletest.trace.hellotrace.HelloTraceV1
import space.jinwoo.multimoduletest.trace.TraceStatus

@Service
class OrderServiceV1(
    private val orderRepository: OrderRepositoryV1,
    private val helloTraceV1: HelloTraceV1
) {
    fun orderItem(itemId: String) {
        var status: TraceStatus? = null
        try {
            status = helloTraceV1.begin("OrderServiceV1.orderItem()")
            orderRepository.save(itemId)
            helloTraceV1.end(status)
        }catch (e: Exception) {
            helloTraceV1.exception(status!!, e)
            throw e
        }
    }
}