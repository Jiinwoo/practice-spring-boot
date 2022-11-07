package space.jinwoo.multimoduletest.trace.callback

import space.jinwoo.multimoduletest.trace.TraceStatus
import space.jinwoo.multimoduletest.trace.logtrace.LogTrace

class TraceTemplate(
    private val trace: LogTrace
) {

    fun<T> execute(message: String, callback: TraceCallback<T>): T{
        var status: TraceStatus? = null
        try {
            status = trace.begin(message)
            val result = callback.call()
            trace.end(status)
            return result
        } catch (e: Exception) {
            trace.exception(status!!, e)
            throw e
        }
    }

}
