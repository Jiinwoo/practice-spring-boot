package space.jinwoo.multimoduletest.trace.strategy.code.strategy

import mu.KotlinLogging

private val logger = KotlinLogging.logger { }

class ContextV1(
    private val strategy: ()->Unit
) {

    fun execute() {
        val startTime = System.currentTimeMillis()
        // 비즈니스 로직 실행
        strategy.invoke()
        // 비즈니스 로직 종료
        val endTime = System.currentTimeMillis()
        logger.info { "실행 시간 : ${endTime - startTime}ms" }
    }

}