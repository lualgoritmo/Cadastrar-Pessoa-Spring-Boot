package com.luciano.cadastropessoa.cadastrarpessoa.build

import com.luciano.cadastropessoa.cadastrarpessoa.model.Country

data class CountryEntity(
        val idCountry: Long = 1,
        val name: String = "Brasil"
) {
    fun build() = Country(idCountry = idCountry, name = name)

}
