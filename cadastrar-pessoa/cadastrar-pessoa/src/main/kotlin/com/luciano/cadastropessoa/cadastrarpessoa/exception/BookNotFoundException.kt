package com.luciano.cadastropessoa.cadastrarpessoa.exception

class BookNotFoundException(id: Long): RuntimeException("NÃO EXISTE ESSE ID TESTE ERRO: $id")