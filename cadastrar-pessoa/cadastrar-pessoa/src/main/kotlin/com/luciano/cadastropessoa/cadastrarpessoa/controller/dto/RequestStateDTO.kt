package com.luciano.cadastropessoa.cadastrarpessoa.controller.dto

import com.luciano.cadastropessoa.cadastrarpessoa.model.Country
import com.luciano.cadastropessoa.cadastrarpessoa.model.StateUF
import com.luciano.cadastropessoa.cadastrarpessoa.util.UniqueValue

data class RequireStateDTO(
    @field:UniqueValue(
        message = "Esse id já existe!",
        fieldName = "name",
        domainClass = StateUF::class
    )
    val name: String, val countryId: Long
) {
    fun toEntity() =
        StateUF(idState = 0, name = name, country = Country(idCountry = countryId, name = ""))
    companion object {
        fun fromEntity(state: StateUF) = RequireStateDTO(name = state.name, countryId = state.country.idCountry)
    }
}