package space.jinwoo.multimoduletest.trace

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import space.jinwoo.multimoduletest.trace.logtrace.FieldLogTrace
import space.jinwoo.multimoduletest.trace.logtrace.LogTrace
import space.jinwoo.multimoduletest.trace.logtrace.ThreadLocalLogTrace

@Configuration
class LogTraceConfig {

    @Bean
    fun logTrace(): LogTrace {
        return ThreadLocalLogTrace()
    }

}