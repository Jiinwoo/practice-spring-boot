package space.jinwoo.multimoduletest.v3

import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import space.jinwoo.multimoduletest.trace.TraceStatus
import space.jinwoo.multimoduletest.trace.hellotrace.HelloTraceV2
import space.jinwoo.multimoduletest.trace.logtrace.FieldLogTrace
import space.jinwoo.multimoduletest.trace.logtrace.LogTrace

private val logger = KotlinLogging.logger {}

@RestController
class OrderControllerV3(
    private val orderService: OrderServiceV3,
    private val logTrace: LogTrace
) {

    @GetMapping("/v3/request")
    fun request(itemId: String): String {
        var status: TraceStatus? = null
        try {
            status = logTrace.begin("OrderControllerV1.request()")
            orderService.orderItem(itemId)
            logTrace.end(status)
        }catch (e: Exception) {
            logTrace.exception(status!!, e)
            throw e
        }
        return "ok"
    }

}