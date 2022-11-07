package space.jinwoo.multimoduletest.trace.strategy.code.strategy
import mu.KotlinLogging

private val logger = KotlinLogging.logger { }

class ContextV2 {

    fun execute(strategy: Strategy) {
        val startTime = System.currentTimeMillis()
        // 비즈니스 로직 실행
        strategy.apply()
        // 비즈니스 로직 종료
        val endTime = System.currentTimeMillis()
        logger.info { "실행 시간 : ${endTime - startTime}ms" }
    }

}