package space.jinwoo.multimoduletest.v1

import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import space.jinwoo.multimoduletest.trace.hellotrace.HelloTraceV1
import space.jinwoo.multimoduletest.trace.TraceStatus

private val logger = KotlinLogging.logger {}

@RestController
class OrderControllerV1(
    private val orderService: OrderServiceV1,
    private val helloTraceV1: HelloTraceV1
) {

    @GetMapping("/v1/request")
    fun request(itemId: String): String {
        var status: TraceStatus? = null
        try {
            status = helloTraceV1.begin("OrderControllerV1.request()")
            orderService.orderItem(itemId)
            helloTraceV1.end(status)
        }catch (e: Exception) {
            helloTraceV1.exception(status!!, e)
            throw e
        }
        return "ok"
    }

}