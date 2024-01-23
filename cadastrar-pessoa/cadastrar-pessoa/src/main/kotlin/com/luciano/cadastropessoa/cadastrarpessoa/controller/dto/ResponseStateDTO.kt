package com.luciano.cadastropessoa.cadastrarpessoa.controller.dto

import com.luciano.cadastropessoa.cadastrarpessoa.model.Country
import com.luciano.cadastropessoa.cadastrarpessoa.model.StateUF

data class ResponseStateDTO(val name: String) {
    fun toEntity() = StateUF(idState = 0,
            name = name,
            country = Country(0, name = "", states = emptyList())
    )

    companion object {
        fun fromEntity(state: StateUF) = ResponseStateDTO(
                name = state.name
        )
    }

}