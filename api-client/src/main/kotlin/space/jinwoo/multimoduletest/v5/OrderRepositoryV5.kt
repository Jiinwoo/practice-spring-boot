package space.jinwoo.multimoduletest.v5

import org.springframework.stereotype.Repository
import space.jinwoo.multimoduletest.trace.callback.TraceTemplate
import space.jinwoo.multimoduletest.trace.logtrace.LogTrace
import space.jinwoo.multimoduletest.trace.template.AbstractTemplate

@Repository
class OrderRepositoryV5(
    logTrace: LogTrace
) {

    private val template = TraceTemplate(logTrace)
    fun save(itemId: String) {
        template.execute("OrderRepositoryV5.save") {
            Thread.sleep(1000)
            println("OrderRepositoryV5.save() called")
        }

    }

    private fun sleep (millis: Long) {
        Thread.sleep(millis)
    }
}