package com.luciano.cadastropessoa.cadastrarpessoa.service.impl

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class AuthorTestProducer {
    private val kafkaTemplete: KafkaTemplate<String, String>? = null
    fun sendMessage(message:String) {
        kafkaTemplete?.send("hello-word", message)
    }
}