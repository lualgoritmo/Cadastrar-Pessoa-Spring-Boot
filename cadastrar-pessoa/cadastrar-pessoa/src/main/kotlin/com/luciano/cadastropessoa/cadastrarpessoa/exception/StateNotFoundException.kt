package com.luciano.cadastropessoa.cadastrarpessoa.exception

class StateNotFoundException(idState: Long): RuntimeException("Esse id não existe: $idState")