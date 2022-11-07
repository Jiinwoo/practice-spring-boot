package space.jinwoo.multimoduletest.trace.template.code

import mu.KotlinLogging


private val logger = KotlinLogging.logger {  }


abstract class AbstractTemplate {

    fun execute() {
        val startTime = System.currentTimeMillis()
        // 비즈니스 로직 실행
        call()
        // 비즈니스 로직 종료
        val endTime = System.currentTimeMillis()
        logger.info { "실행 시간 : ${endTime - startTime}ms" }
    }


    protected abstract fun call()

}