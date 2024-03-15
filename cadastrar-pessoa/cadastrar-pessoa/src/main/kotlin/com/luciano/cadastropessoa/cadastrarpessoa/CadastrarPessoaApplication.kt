package com.luciano.cadastropessoa.cadastrarpessoa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
//Apenas para subir
@EnableFeignClients
@SpringBootApplication
class CadastrarPessoaApplication

fun main(args: Array<String>) {
	runApplication<CadastrarPessoaApplication>(*args)
	println("Hello, Word!")
}
