package space.jinwoo.multimoduletest.trace.template.code

import mu.KotlinLogging

private val logger = KotlinLogging.logger { }

class SubClassLogic1 : AbstractTemplate() {
    override fun call() {
        logger.info { "비즈니스 로직 1" }
    }
}