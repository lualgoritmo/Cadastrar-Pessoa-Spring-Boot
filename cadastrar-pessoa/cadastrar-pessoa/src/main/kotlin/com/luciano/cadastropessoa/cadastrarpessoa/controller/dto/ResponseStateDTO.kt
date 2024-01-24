package com.luciano.cadastropessoa.cadastrarpessoa.controller.dto

import com.luciano.cadastropessoa.cadastrarpessoa.model.Country
import com.luciano.cadastropessoa.cadastrarpessoa.model.StateUF

data class ResponseStateDTO(val name: String) {
    fun toEntity(country: Country) = StateUF(
            name = name,
            country = country
    )

    companion object {
        fun fromEntity(state: StateUF) = ResponseStateDTO(
                name = state.name
        )
    }

}