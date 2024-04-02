package com.luciano.cadastropessoa.cadastrarpessoa.service.impl

import org.springframework.http.HttpStatus
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
@Service
class HelloConsumerImpl {
    @KafkaListener(topics = ["primeiro-topico"], groupId = "group-1")
    fun receiverMensage(message: String) = println("Está é a mensagem: $message")
}

@RestController
@RequestMapping("/kafka")
class HelloController(private val helloProducerImpl: HelloProducerImpl) {

    @GetMapping("/hello/{name}")
    @ResponseStatus(HttpStatus.OK)
    fun getHello(@PathVariable name: String) = helloProducerImpl.sendMessage("Hello $name")

}
