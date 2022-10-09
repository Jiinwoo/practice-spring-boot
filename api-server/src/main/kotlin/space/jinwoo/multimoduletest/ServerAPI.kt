package space.jinwoo.multimoduletest

import mu.KotlinLogging
import org.slf4j.MDC
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

val logger = KotlinLogging.logger {}

@RestController
class ServerAPI {

    @GetMapping("/server")
    fun server(request: HttpServletRequest): String {
        // log all header from request
        request.headerNames.toList().forEach { logger.info { "$it: ${request.getHeader(it)}" } }
        logger.info { "server response" }
        logger.info { "mdc value: ${MDC.get("traceId")}" }
        logger.info { "mdc value: ${MDC.get("spanId")}" }
        return "server"
    }

}