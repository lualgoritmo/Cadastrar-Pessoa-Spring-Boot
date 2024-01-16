package com.luciano.cadastropessoa.cadastrarpessoa.controller.dto

data class CreateCountriesDTO(
    val name: String,
    val states: List<StatesDTO>
)