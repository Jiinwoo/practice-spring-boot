package space.jinwoo.multimoduletest.trace.threadlocal

import mu.KotlinLogging
import org.junit.jupiter.api.Test
import space.jinwoo.multimoduletest.trace.threadlocal.code.ThreadLocalService

private val log = KotlinLogging.logger {  }

class ThreadLocalServiceTest {

    val threadLocalService: ThreadLocalService = ThreadLocalService()
    @Test
    fun field() {
        log.info { "main start" }
        val userA = Runnable {
            threadLocalService.logic("userA")
        }
        val userB = Runnable {
            threadLocalService.logic("userB")
        }

        val threadA = Thread(userA)
        threadA.name = "thread-A"
        val threadB = Thread(userB)
        threadB.name = "thread-B"

        threadA.start()
//        sleep(2000)
        threadB.start()
        sleep(3000)
        log.info { "main end" }
    }

    private fun sleep(i: Int) {
        try {
            Thread.sleep(i.toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

}