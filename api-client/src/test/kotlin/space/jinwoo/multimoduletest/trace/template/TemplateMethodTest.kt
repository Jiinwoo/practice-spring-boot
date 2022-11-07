package space.jinwoo.multimoduletest.trace.template

import com.fasterxml.jackson.databind.AbstractTypeResolver
import mu.KotlinLogging
import org.junit.jupiter.api.Test
import space.jinwoo.multimoduletest.trace.template.code.AbstractTemplate
import space.jinwoo.multimoduletest.trace.template.code.SubClassLogic1
import space.jinwoo.multimoduletest.trace.template.code.SubClassLogic2

private val logger = KotlinLogging.logger {}

class TemplateMethodTest {

    @Test
    fun templateMethodV0() {
        logic1()
        logic2()
    }

    private fun logic1() {
        val startTime = System.currentTimeMillis()
        // 비즈니스 로직 실행
        logger.info { "비즈니스 로직 1 실행" }
        // 비즈니스 로직 종료
        val endTime = System.currentTimeMillis()
        logger.info { "실행 시간 : ${endTime - startTime}ms" }
    }

    private fun logic2() {
        val startTime = System.currentTimeMillis()
        // 비즈니스 로직 실행
        logger.info { "비즈니스 로직 2 실행" }
        // 비즈니스 로직 종료
        val endTime = System.currentTimeMillis()
        logger.info { "실행 시간 : ${endTime - startTime}ms" }
    }


    @Test
    fun templateMethodV1() {
        val logic1: AbstractTemplate = SubClassLogic1()
        logic1.execute()
        val logic2: AbstractTemplate = SubClassLogic2()
        logic2.execute()
    }

    @Test
    fun templateMethodV2() {
        val logic1 = object : AbstractTemplate() {
            override fun call() {
                logger.info { "비즈니스 로직 1 실행" }
            }
        }.execute()
        val logic2 = object : AbstractTemplate() {
            override fun call() {
                logger.info { "비즈니스 로직 2 실행" }
            }
        }.execute()

    }

}