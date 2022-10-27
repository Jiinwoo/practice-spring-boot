package space.jinwoo.multimoduletest.trace

import java.util.*

class TraceId private constructor(
    val id: String,
    val level: Int,
) {

    companion object {
        private fun createId() = UUID.randomUUID().toString().substring(0, 8)
    }

    constructor() : this(createId(), 0)

    fun createNextId(): TraceId {
        return TraceId(id, level + 1)
    }

    fun createPreviousId(): TraceId {
        return TraceId(id, level - 1)
    }

    val isFirstLevel = level == 0


}