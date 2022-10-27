package space.jinwoo.multimoduletest.trace.logtrace

import space.jinwoo.multimoduletest.trace.TraceStatus

interface LogTrace {
    fun begin(message: String): TraceStatus
    fun end(traceStatus: TraceStatus): Unit
    fun exception(traceStatus: TraceStatus, ex: Exception): Unit
}