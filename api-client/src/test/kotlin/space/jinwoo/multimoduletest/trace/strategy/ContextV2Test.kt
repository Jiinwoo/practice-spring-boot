package space.jinwoo.multimoduletest.trace.strategy

import org.junit.jupiter.api.Test

import mu.KotlinLogging
import space.jinwoo.multimoduletest.trace.strategy.code.strategy.*

private val logger = KotlinLogging.logger { }

class ContextV2Test {

    @Test
    fun `test`() {
        val context = ContextV2()
        context.execute(StrategyLogic1())
        context.execute(StrategyLogic2())
    }
}