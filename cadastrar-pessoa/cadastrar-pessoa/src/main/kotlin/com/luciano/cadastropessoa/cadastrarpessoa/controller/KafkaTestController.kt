//package com.luciano.cadastropessoa.cadastrarpessoa.controller
//
//import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.AuthorTestProducer
//import org.springframework.http.HttpStatus
//import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.PathVariable
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.ResponseStatus
//import org.springframework.web.bind.annotation.RestController
//
//@RestController
//@RequestMapping("/kafka")
//class KafkaTestController(private val service: AuthorTestProducer) {
//    @GetMapping("/hello/name")
//    @ResponseStatus(HttpStatus.OK)
//    fun hello(@PathVariable name:String) = service.sendMessage("Funfando tudo: $name")
//
//}