package space.jinwoo.multimoduletest.trace

data class TraceStatus(
    val traceId: TraceId,
    val startTimeMs: Long,
    val message: String
)