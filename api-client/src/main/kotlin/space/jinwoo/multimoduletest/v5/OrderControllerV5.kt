package space.jinwoo.multimoduletest.v5

import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import space.jinwoo.multimoduletest.trace.callback.TraceTemplate
import space.jinwoo.multimoduletest.trace.logtrace.LogTrace
import space.jinwoo.multimoduletest.trace.template.AbstractTemplate
import java.security.PrivateKey

private val logger = KotlinLogging.logger {}

@RestController
class OrderControllerV5(
    private val orderService: OrderServiceV5,
    logTrace: LogTrace,
) {
    val template = TraceTemplate(logTrace)

    @GetMapping("/v5/request")
    fun request(itemId: String): String {
        return template.execute("OrderControllerV5#request") {
            orderService.orderItem(itemId)
            "ok"
        }
    }

}