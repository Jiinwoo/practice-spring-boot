package space.jinwoo.multimoduletest.v4

import org.springframework.stereotype.Repository
import space.jinwoo.multimoduletest.trace.TraceStatus
import space.jinwoo.multimoduletest.trace.logtrace.LogTrace
import space.jinwoo.multimoduletest.trace.template.AbstractTemplate

@Repository
class OrderRepositoryV4(
    private val logTrace: LogTrace
) {

    fun save(itemId: String) {
        object: AbstractTemplate<Unit>(logTrace) {
            override fun call() {
                // save order
                if (itemId == "ex") {
                    throw IllegalStateException("예외 발생")
                }
                sleep(1000L)
            }
        }.execute("OrderRepositoryV4.save")

    }

    private fun sleep (millis: Long) {
        Thread.sleep(millis)
    }
}