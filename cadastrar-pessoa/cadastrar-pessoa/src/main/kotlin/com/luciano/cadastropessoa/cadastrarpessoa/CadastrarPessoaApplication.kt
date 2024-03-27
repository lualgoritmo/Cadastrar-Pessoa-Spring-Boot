package com.luciano.cadastropessoa.cadastrarpessoa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.kafka.annotation.EnableKafka

@EnableKafka
@EnableFeignClients
@SpringBootApplication
class CadastrarPessoaApplication
fun main(args: Array<String>) {
	runApplication<CadastrarPessoaApplication>(*args)
	println("Hello, Word!")
}
