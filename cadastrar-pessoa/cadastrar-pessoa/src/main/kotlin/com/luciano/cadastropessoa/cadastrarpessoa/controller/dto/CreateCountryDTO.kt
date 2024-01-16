package com.luciano.cadastropessoa.cadastrarpessoa.controller.dto

import com.luciano.cadastropessoa.cadastrarpessoa.model.Country
import com.luciano.cadastropessoa.cadastrarpessoa.util.UniqueValue

data class CreateCountryDTO(
    @field:UniqueValue(
        message = "Esse País já existe!",
        fieldName = "name",
        domainClass = Country::class
    )
    val name: String
) {
    fun toEntity() = Country(idCountry = 0, name = this.name)

    companion object {
        fun fromEntity(country: Country) = CreateCountryDTO(name = country.name)
    }
}