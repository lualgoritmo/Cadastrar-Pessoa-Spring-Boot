package com.luciano.cadastrarpessoa.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CadastrarpessoaspringbootApplication

fun main(args: Array<String>) {
	runApplication<CadastrarpessoaspringbootApplication>(*args)
	println("Hello, Word!")
}
