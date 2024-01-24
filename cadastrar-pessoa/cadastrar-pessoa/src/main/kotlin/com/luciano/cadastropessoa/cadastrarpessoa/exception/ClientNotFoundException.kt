package com.luciano.cadastropessoa.cadastrarpessoa.exception

class ClientNotFoundException(idClient: Long):RuntimeException("Esse id não existe: $idClient")