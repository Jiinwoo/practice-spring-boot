package space.jinwoo.multimoduletest.trace.hellotrace

import mu.KotlinLogging
import org.springframework.stereotype.Component
import space.jinwoo.multimoduletest.trace.TraceId
import space.jinwoo.multimoduletest.trace.TraceStatus

private val log = KotlinLogging.logger {}

@Component
class HelloTraceV2 {

    companion object {
        const val START_PREFIX = "-->"
        const val COMPLETE_PREFIX = "<--"
        const val EX_PREFIX = "<X-"
    }

    fun begin(message: String): TraceStatus {
        val traceId = TraceId()
        val startTimeMs = System.currentTimeMillis()

        log.info { "[${traceId.id}] ${addSpace(START_PREFIX, traceId.level)} $message" }
        return TraceStatus(traceId, startTimeMs, message)
    }

    fun beginSync(beforeTraceId: TraceId, message: String): TraceStatus {
        val traceId = beforeTraceId.createNextId()
        val startTimeMs = System.currentTimeMillis()

        log.info { "[${traceId.id}] ${addSpace(START_PREFIX, traceId.level)} $message" }
        return TraceStatus(traceId, startTimeMs, message)
    }


    fun end(traceStatus: TraceStatus): Unit {
        complete(traceStatus)
    }


    fun exception(traceStatus: TraceStatus, e: Exception): Unit {
        complete(traceStatus, e)
    }

    private fun complete(traceStatus: TraceStatus, ex: Exception? = null): Unit {
        val endTimeMs = System.currentTimeMillis()
        val elapsedTimeMs = endTimeMs - traceStatus.startTimeMs
        if (ex == null) {
            log.info {
                "[${traceStatus.traceId.id}] ${
                    addSpace(
                        COMPLETE_PREFIX,
                        traceStatus.traceId.level
                    )
                } ${traceStatus.message} ${elapsedTimeMs}ms"
            }
        } else {
            log.info(ex) {
                "[${traceStatus.traceId.id}] ${
                    addSpace(
                        EX_PREFIX,
                        traceStatus.traceId.level
                    )
                } ${traceStatus.message} ${elapsedTimeMs}ms 예외 발생!!"
            }
        }
    }

    private fun addSpace(prefix: String, level: Int): String {
        val sb = StringBuilder()
        for (i in 0 until level) {
            sb.append(if (i == level - 1) "|$prefix" else "|   ")
        }
        return sb.toString()
    }

}