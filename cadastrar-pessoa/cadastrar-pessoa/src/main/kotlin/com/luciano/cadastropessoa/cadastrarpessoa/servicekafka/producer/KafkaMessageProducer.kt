package com.luciano.cadastropessoa.cadastrarpessoa.servicekafka.producer

interface KafkaMessageProducer {
    fun sendMessage(topic: String, key: String, message: String): String
}