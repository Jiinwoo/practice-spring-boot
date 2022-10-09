package space.jinwoo.multimoduletest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class ApiClientApplication {
    @Bean
    fun getRestTemplate(): RestTemplate? {
        return RestTemplate()
    }
}

fun main(args: Array<String>) {
    runApplication<ApiClientApplication>(*args)
}
