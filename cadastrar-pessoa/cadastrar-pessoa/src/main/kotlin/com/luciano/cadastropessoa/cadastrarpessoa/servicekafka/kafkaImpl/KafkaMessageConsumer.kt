package com.luciano.cadastropessoa.cadastrarpessoa.servicekafka.kafkaImpl

import com.luciano.cadastropessoa.cadastrarpessoa.servicekafka.consumer.KafkaMessageConsumer
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

//@Service
//class KafkaMessageConsumerImpl : KafkaMessageConsumer {
//    @KafkaListener(topics = ["client-create-topic"], groupId = "group-1")
//    override fun consumeMessage(message: String) {
//        println("ESSA MENSAGEM É DO KafkaMessageConsumer: $message")
//    }
//
//}
