package com.luciano.cadastropessoa.cadastrarpessoa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CadastrarPessoaApplication

fun main(args: Array<String>) {
	runApplication<CadastrarPessoaApplication>(*args)
	println("Hello, Word!")
}
