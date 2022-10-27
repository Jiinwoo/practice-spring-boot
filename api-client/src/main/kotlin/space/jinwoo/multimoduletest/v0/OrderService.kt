package space.jinwoo.multimoduletest.v0

import org.springframework.stereotype.Service
import space.jinwoo.multimoduletest.v1.OrderRepositoryV1

@Service
class OrderService(
    private val orderRepository: OrderRepositoryV1
) {
    fun orderItem(itemId: String) {
        orderRepository.save(itemId)
    }
}