package space.jinwoo.multimoduletest.trace.strategy.code.strategy
import mu.KotlinLogging

private val logger = KotlinLogging.logger { }
class StrategyLogic2 : Strategy {

    override fun apply() {
        logger.info { "비즈니스로직2 실행" }
    }
}