package space.jinwoo.multimoduletest.trace.callback

fun interface TraceCallback<T>{
    fun call(): T
}