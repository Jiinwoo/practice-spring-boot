package space.jinwoo.multimoduletest.v4

import org.springframework.stereotype.Service
import space.jinwoo.multimoduletest.trace.TraceStatus
import space.jinwoo.multimoduletest.trace.logtrace.LogTrace
import space.jinwoo.multimoduletest.trace.template.AbstractTemplate

@Service
class OrderServiceV4(
    private val orderRepository: OrderRepositoryV4,
    private val logTrace: LogTrace
) {
    fun orderItem(itemId: String) {
        object: AbstractTemplate<Unit>(logTrace) {
            override fun call() {
                orderRepository.save(itemId)
            }
        }.execute( "OrderServiceV4.orderItem()")
    }
}