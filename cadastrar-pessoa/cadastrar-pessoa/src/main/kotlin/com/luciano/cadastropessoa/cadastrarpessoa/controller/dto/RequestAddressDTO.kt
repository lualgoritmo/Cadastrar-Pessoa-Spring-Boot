package com.luciano.cadastropessoa.cadastrarpessoa.controller.dto

data class RequestAddressDTO(
        val cep: String,
//        val logradouro: String,
//        val complemento: String,
//        val bairro: String,
//        val uf: String
)
data class ResponseCEP(
        val cep: String,
        val logradouro: String,
        val complemento: String,
        val bairro: String,
        val uf: String
)