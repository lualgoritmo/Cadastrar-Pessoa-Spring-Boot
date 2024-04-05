package com.luciano.cadastropessoa.cadastrarpessoa.service.impl

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaMessageProducerImpl(private val kafkaTemplate: KafkaTemplate<String, String>) {
    fun sendMessage(topic: String, message: String) {
        kafkaTemplate.send(topic, message)
    }
}

