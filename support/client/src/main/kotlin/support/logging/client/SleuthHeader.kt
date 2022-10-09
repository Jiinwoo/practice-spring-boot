package support.logging.client

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.util.*
import org.springframework.cloud.sleuth.Tracer

class SleuthHeader internal constructor(
    private val tracer: Tracer
) {
    class Config {
        internal lateinit var tracer: Tracer

        fun tracer(tracer: Tracer) {
            this.tracer = tracer
        }
    }

    private fun setupRequest(client: HttpClient) {
        client.requestPipeline.intercept(HttpRequestPipeline.State) {

            val span = tracer.currentSpan() ?: tracer.nextSpan()
            context.headers.append("X-B3-TraceId", span.context().traceId())
            context.headers.append("X-B3-SpanId", tracer.nextSpan().context().spanId())
            context.headers.append("X-B3-ParentSpanId", tracer.nextSpan().context().spanId())
            context.headers.append("X-B3-Sampled", "1")
        }
    }

    companion object : HttpClientPlugin<Config, SleuthHeader> {

        override val key: AttributeKey<SleuthHeader> = AttributeKey("SleuthHeaderPlugin")

        override fun install(plugin: SleuthHeader, scope: HttpClient) {
            plugin.setupRequest(scope)
        }

        override fun prepare(block: Config.() -> Unit): SleuthHeader {
            val config = Config().apply(block)
            return SleuthHeader(config.tracer)
        }
    }
}