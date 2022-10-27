package space.jinwoo.multimoduletest.v2

import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import space.jinwoo.multimoduletest.trace.hellotrace.HelloTraceV1
import space.jinwoo.multimoduletest.trace.TraceStatus
import space.jinwoo.multimoduletest.trace.hellotrace.HelloTraceV2

private val logger = KotlinLogging.logger {}

@RestController
class OrderControllerV2(
    private val orderService: OrderServiceV2,
    private val helloTraceV2: HelloTraceV2
) {

    @GetMapping("/v2/request")
    fun request(itemId: String): String {
        var status: TraceStatus? = null
        try {
            status = helloTraceV2.begin("OrderControllerV1.request()")
            orderService.orderItem(status!!.traceId, itemId)
            helloTraceV2.end(status)
        }catch (e: Exception) {
            helloTraceV2.exception(status!!, e)
            throw e
        }
        return "ok"
    }

}