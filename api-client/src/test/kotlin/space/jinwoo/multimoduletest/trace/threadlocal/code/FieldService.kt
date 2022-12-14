package space.jinwoo.multimoduletest.trace.threadlocal.code

import mu.KotlinLogging


private val log = KotlinLogging.logger {}

class FieldService {

    var nameStore: String? = null

    fun logic(name: String) {
        log.info { "์ ์ฅ name=${name} -> nameStore=${nameStore}" }
        nameStore = name
        sleep(1000)
        log.info { "์กฐํ nameStore=$nameStore" }
    }

    private fun sleep(i: Int) {
        Thread.sleep(i.toLong())
    }

}