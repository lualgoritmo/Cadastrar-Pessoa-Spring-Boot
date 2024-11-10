package com.luciano.cadastropessoa.cadastrarpessoa.service.kafka

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaMessageConsumer {

    @KafkaListener(topics = ["client-create-topic"], groupId = "group-1")
    fun consumeMessage(message: String) {
        println("ESSA MENSAGEM É DO KafkaMessageConsumer: $message")
    }
}
