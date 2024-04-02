package com.luciano.cadastropessoa.cadastrarpessoa.service.impl

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class HelloProducerImpl(private val kafkaTemplate: KafkaTemplate<String, String>) {
    fun sendMessage(message: String) = kafkaTemplate.send("primeiro-topico", message)
}
