package space.jinwoo.multimoduletest.trace.threadlocal.code

import mu.KotlinLogging

private val log = KotlinLogging.logger { }

class ThreadLocalService {

    var nameStore: ThreadLocal<String?> = ThreadLocal()

    fun logic(name: String): String? {
        log.info { "저장 name=${name} -> nameStore=${nameStore.get()}" }
        nameStore.set(name)
        sleep(1000)
        log.info { "조회 nameStore=${nameStore.get()}" }
        return nameStore.get()
    }

    private fun sleep(i: Int) {
        Thread.sleep(i.toLong())
    }

}