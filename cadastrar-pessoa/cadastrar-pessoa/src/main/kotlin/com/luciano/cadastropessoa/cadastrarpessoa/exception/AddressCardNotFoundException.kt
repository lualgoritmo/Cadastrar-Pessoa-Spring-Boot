package com.luciano.cadastropessoa.cadastrarpessoa.exception

class AddressCardNotFoundException(idAddress:Long): RuntimeException("Este ID não existe: $idAddress")