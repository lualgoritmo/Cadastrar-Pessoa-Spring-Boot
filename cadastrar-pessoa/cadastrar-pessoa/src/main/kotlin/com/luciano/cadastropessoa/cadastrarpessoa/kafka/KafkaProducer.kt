package com.luciano.cadastropessoa.cadastrarpessoa.kafka

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component


@Component
class KafkaProducer {
    @Autowired
    private val producerKafka: KafkaTemplate<String, String>? = null
    fun sendMessage(message: String) {
        producerKafka?.send("Enviando mensagem para", message)
    }
}
