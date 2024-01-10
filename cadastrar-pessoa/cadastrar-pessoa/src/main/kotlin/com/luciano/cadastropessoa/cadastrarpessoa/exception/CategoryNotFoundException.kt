package com.luciano.cadastropessoa.cadastrarpessoa.exception

class CategoryNotFoundException(idCategory: Long) : RuntimeException("Esse id de categoria não existe! $idCategory") {
}