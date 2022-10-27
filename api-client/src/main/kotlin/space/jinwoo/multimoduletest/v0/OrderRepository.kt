package space.jinwoo.multimoduletest.v0

import org.springframework.stereotype.Repository

@Repository
class OrderRepository {

    fun save(itemId: String) {
        // save order
        if (itemId == "ex") {
            throw IllegalStateException("예외 발생")
        }
        sleep(1000L)
    }

    private fun sleep (millis: Long) {
        Thread.sleep(millis)
    }
}