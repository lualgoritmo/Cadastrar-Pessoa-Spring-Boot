package com.luciano.cadastropessoa.cadastrarpessoa.controller.dto

data class ResponseCountryWithStatesDTO(
    val name: String,
    val states: List<StateUFDTO>
)

