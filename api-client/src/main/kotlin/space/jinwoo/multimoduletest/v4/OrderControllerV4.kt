package space.jinwoo.multimoduletest.v4

import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import space.jinwoo.multimoduletest.trace.TraceStatus
import space.jinwoo.multimoduletest.trace.logtrace.LogTrace
import space.jinwoo.multimoduletest.trace.template.AbstractTemplate

private val logger = KotlinLogging.logger {}

@RestController
class OrderControllerV4(
    private val orderService: OrderServiceV4,
    private val logTrace: LogTrace
) {

    @GetMapping("/v4/request")
    fun request(itemId: String): String {
        return object: AbstractTemplate<String>(logTrace) {
            override fun call(): String{
                orderService.orderItem("OrderControllerV4.request()")
                return "OK"
            }
        }.execute("OrderControllerV4.request()")

    }

}