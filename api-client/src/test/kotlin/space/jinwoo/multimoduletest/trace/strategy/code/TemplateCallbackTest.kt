package space.jinwoo.multimoduletest.trace.strategy.code

import mu.KotlinLogging
import org.junit.jupiter.api.Test
import space.jinwoo.multimoduletest.trace.strategy.code.template.TimeLogTemplate

private val logger = KotlinLogging.logger { }
class TemplateCallbackTest {

    @Test
    fun callbackV1() {
        val template = TimeLogTemplate()
        template.execute {
            logger.info { "callbackV1" }
        }
        template.execute {
            logger.info { "callbackV2" }
        }

    }

}