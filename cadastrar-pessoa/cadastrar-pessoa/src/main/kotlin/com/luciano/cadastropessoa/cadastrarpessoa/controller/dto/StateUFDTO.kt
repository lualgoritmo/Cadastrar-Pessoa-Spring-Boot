package com.luciano.cadastropessoa.cadastrarpessoa.controller.dto

import com.luciano.cadastropessoa.cadastrarpessoa.model.Country
import com.luciano.cadastropessoa.cadastrarpessoa.model.StateUF

data class StateUFDTO(val name: String, val idCountry: Long) {
    fun toEntity(country: Country) = StateUF(
            name = name,
            country = country
    )
}
