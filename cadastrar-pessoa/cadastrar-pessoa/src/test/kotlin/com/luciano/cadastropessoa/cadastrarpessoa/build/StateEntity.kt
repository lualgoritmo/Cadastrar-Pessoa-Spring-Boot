package com.luciano.cadastropessoa.cadastrarpessoa.build

import com.luciano.cadastropessoa.cadastrarpessoa.model.Country
import com.luciano.cadastropessoa.cadastrarpessoa.model.StateUF

data class StateEntity(
        val idState: Long = 1,
        val name: String = "BA",
        val country: Country = CountryEntity().build()
) {
    fun build() = StateUF(
            idState = this.idState,
            name = this.name,
            country = country
    )
}