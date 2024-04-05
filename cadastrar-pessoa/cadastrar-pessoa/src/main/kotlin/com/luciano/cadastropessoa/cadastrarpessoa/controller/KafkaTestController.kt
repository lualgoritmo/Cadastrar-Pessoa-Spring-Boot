package com.luciano.cadastropessoa.cadastrarpessoa.controller

import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.KafkaMessageProducerImpl
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/kafka")
class KafkaTestController(private val service: KafkaMessageProducerImpl) {
    @PostMapping("/sendmessage")
    @ResponseStatus(HttpStatus.CREATED)
    fun sendMessage(@RequestBody message: String) {
        val topic = "primeiro-topico"
        service.sendMessage(topic = topic, message = message)
    }

}