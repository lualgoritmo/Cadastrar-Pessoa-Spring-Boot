package com.luciano.cadastropessoa.cadastrarpessoa.kafka

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
@Component
class KafkaConsumer {
    @KafkaListener(topics = ["primeiro-topico"])
    fun receiveMessage(message: String) {

    }
}
