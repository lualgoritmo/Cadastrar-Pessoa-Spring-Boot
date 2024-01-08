package com.luciano.cadastropessoa.cadastrarpessoa.exception

class PersonNotFoundException(id: Long) : RuntimeException("Person não encontrado id: $id")
