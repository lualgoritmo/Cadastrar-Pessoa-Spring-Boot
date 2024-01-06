package com.luciano.cadastrarpessoa.demo.exception

class PersonNotFoundException(id: Long) : RuntimeException("Cardholder não encontrado id: $id")
