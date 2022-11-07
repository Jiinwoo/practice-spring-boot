package space.jinwoo.multimoduletest.v5

import org.springframework.stereotype.Service
import space.jinwoo.multimoduletest.trace.callback.TraceTemplate
import space.jinwoo.multimoduletest.trace.logtrace.LogTrace
import space.jinwoo.multimoduletest.trace.template.AbstractTemplate

@Service
class OrderServiceV5(
    private val orderRepository: OrderRepositoryV5,
    logTrace: LogTrace
) {
    private val template = TraceTemplate(logTrace)

    fun orderItem(itemId: String) {
        template.execute("OrderServiceV5.orderItem()") {
            orderRepository.save(itemId)
        }
    }
}