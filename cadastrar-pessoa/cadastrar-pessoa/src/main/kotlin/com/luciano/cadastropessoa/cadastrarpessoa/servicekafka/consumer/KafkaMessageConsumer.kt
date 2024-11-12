package com.luciano.cadastropessoa.cadastrarpessoa.servicekafka.consumer

interface KafkaMessageConsumer {
    fun consumeMessage(message: String)
}