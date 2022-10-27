package space.jinwoo.multimoduletest.trace.logtrace

import mu.KotlinLogging
import space.jinwoo.multimoduletest.trace.TraceId
import space.jinwoo.multimoduletest.trace.TraceStatus

private val log = KotlinLogging.logger {  }

class ThreadLocalLogTrace: LogTrace {
    companion object {
        const val START_PREFIX = "-->"
        const val COMPLETE_PREFIX = "<--"
        const val EX_PREFIX = "<X-"
    }

    private var traceIdHolder: ThreadLocal<TraceId> = ThreadLocal()

    override fun begin(message: String): TraceStatus {
        syncTraceId()
        val traceId = traceIdHolder.get()!!
        val startTimeMs = System.currentTimeMillis()

        log.info { "[${traceId.id}] ${addSpace(START_PREFIX, traceId.level)} $message" }
        return TraceStatus(traceId, startTimeMs, message)
    }


    private fun syncTraceId() {
        if (traceIdHolder.get() == null) {
            traceIdHolder.set(TraceId())
        }else {
            traceIdHolder.set(traceIdHolder.get()!!.createNextId())
        }
    }

    override fun end(traceStatus: TraceStatus): Unit {
        complete(traceStatus)
    }


    override fun exception(traceStatus: TraceStatus, e: Exception): Unit {
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
        releaseTraceId();
    }

    private fun releaseTraceId() {
        if (traceIdHolder.get()!!.isFirstLevel) {
            traceIdHolder.remove()
        } else {
            traceIdHolder.set(traceIdHolder.get()!!.createPreviousId())
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