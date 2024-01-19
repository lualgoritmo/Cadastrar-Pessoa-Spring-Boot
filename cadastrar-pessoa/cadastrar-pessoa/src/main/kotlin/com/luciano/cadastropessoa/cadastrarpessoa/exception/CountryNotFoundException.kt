package com.luciano.cadastropessoa.cadastrarpessoa.exception

class CountryNotFoundException(idCountry:Long): RuntimeException("Não existe o id do Country: $idCountry")