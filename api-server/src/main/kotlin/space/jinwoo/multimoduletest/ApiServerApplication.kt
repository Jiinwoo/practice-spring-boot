package space.jinwoo.multimoduletest

import brave.baggage.CorrelationScopeConfig.SingleCorrelationField
import brave.baggage.CorrelationScopeCustomizer
import brave.baggage.CorrelationScopeDecorator
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean


@SpringBootApplication
class ApiServerApplication {

    @Bean
    fun makeCorrelationFieldsDirty(): CorrelationScopeCustomizer? {
        return CorrelationScopeCustomizer { b: CorrelationScopeDecorator.Builder ->
            val configs = b.configs()
            b.clear()

            configs.forEach { config ->
                if (config is SingleCorrelationField) {
                    if(!config.readOnly()) {
                        b.add(config.toBuilder().dirty().build())
                    }
                }
                b.add(config)
            }
        }
    }

}

fun main(args: Array<String>) {
    runApplication<ApiServerApplication>(*args)
}
