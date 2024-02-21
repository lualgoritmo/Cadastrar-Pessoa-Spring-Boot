package com.luciano.cadastropessoa.cadastrarpessoa.controller.dto

data class RequireCEPDTO(val cep: String)
data class ResponseCEPDTO(
        val cep: String,
        val logradouro: String,
        val localidade: String,
        val complemento: String,
        val bairro: String,
        val ddd: String
)