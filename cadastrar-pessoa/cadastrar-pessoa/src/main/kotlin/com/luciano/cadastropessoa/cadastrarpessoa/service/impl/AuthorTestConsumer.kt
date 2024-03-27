package com.luciano.cadastropessoa.cadastrarpessoa.service.impl

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class AuthorTestConsumer {
    @KafkaListener(topics = ["hello-word"], groupId = "group-1")
    fun receiveMessage(message:String) {
        println("Mensagem recebida: $message")
    }
}