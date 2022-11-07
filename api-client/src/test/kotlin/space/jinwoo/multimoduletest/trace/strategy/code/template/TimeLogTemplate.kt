package space.jinwoo.multimoduletest.trace.strategy.code.template

import mu.KotlinLogging

private val logger = KotlinLogging.logger { }
class TimeLogTemplate {

    fun execute(callback: Callback) {
        val startTime = System.currentTimeMillis()
        // 비즈니스 로직 실행
        callback.invoke()
        // 비즈니스 로직 종료
        val endTime = System.currentTimeMillis()
        logger.info { "실행 시간 : ${endTime - startTime}ms" }
    }

}