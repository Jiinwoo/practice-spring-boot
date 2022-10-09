package space.jinwoo.multimoduletest

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.*
import kotlinx.coroutines.slf4j.MDCContext
import mu.KotlinLogging
import org.slf4j.MDC
import org.springframework.cloud.sleuth.Tracer
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

private val logger = KotlinLogging.logger {}

@RestController
class ClientAPI(
    private val restTemplate: RestTemplate,
    private val client: HttpClient,
    private val tracer: Tracer
) {

    @GetMapping("/client")
    fun client(): String {
        logger.info { "client call start" }
        logger.info { tracer.currentSpan()?.context()?.traceId() }
        logger.info { "hi = ${MDC.get("traceId")}" }

        // restTemplate get call
//        val response = restTemplate.getForEntity("http://localhost:8082/server", String::class.java)
//        runBlocking {
//            client.get("http://localhost:8082/server") {
//
//            }
//            logger.info { "213123" + tracer.currentSpan()}
//            logger.info { "111111" + tracer.currentTraceContext()?.context()?.traceId()}
//            logger.info { "hi = ${MDC.get("traceId")}" }
//        }
        val join = GlobalScope.launch(Dispatchers.IO + MDCContext()) {
            client.get("http://localhost:8082/server") {

            }
            logger.info { "213123" + tracer.currentSpan() }
            logger.info { "111111" + tracer.currentTraceContext()?.context()?.traceId() }
            logger.info { "hi = ${MDC.get("traceId")}" }
        }
        runBlocking {
            join.join()
        }
        logger.info { "client call end" }
        return "client"
    }

}