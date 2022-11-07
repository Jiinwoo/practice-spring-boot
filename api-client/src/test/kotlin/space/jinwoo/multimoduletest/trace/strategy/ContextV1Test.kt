package space.jinwoo.multimoduletest.trace.strategy

import org.junit.jupiter.api.Test
import mu.KotlinLogging
import space.jinwoo.multimoduletest.trace.strategy.code.strategy.ContextV1
import space.jinwoo.multimoduletest.trace.strategy.code.strategy.Strategy
import space.jinwoo.multimoduletest.trace.strategy.code.strategy.StrategyLogic1
import space.jinwoo.multimoduletest.trace.strategy.code.strategy.StrategyLogic2

private val logger = KotlinLogging.logger { }

class ContextV1Test {
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

    // 전략패턴
    @Test
    fun strategy1() {
//        val strategyLogic1 = StrategyLogic1()
//        val context1 = ContextV1(strategyLogic1)
//        context1.execute()
//
//        val strategyLogic2 = StrategyLogic2()
//        val context2 = ContextV1(strategyLogic2)
//        context2.execute()
    }

    @Test
    fun strategy2() {
        // create ContextV1 with lambda
        val context1 = ContextV1 { logger.info { "비즈니스 로직 1 실행" } }
        context1.execute()
        val context2 = ContextV1 { logger.info { "비즈니스 로직 2 실행" } }
        context2.execute()
//        ContextV1(object : Strategy {
//            override fun call() {
//                logger.info { "비즈니스 로직 1 실행" }
//            }
//        }).execute()
//
//        ContextV1(object : Strategy {
//            override fun call() {
//                logger.info { "비즈니스 로직 2 실행" }
//            }
//        }).execute()


    }


}