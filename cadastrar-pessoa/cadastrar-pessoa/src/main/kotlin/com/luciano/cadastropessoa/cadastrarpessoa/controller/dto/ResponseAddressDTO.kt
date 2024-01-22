package com.luciano.cadastropessoa.cadastrarpessoa.controller.dto

import com.luciano.cadastropessoa.cadastrarpessoa.model.Address
import com.luciano.cadastropessoa.cadastrarpessoa.model.StateUF

class ResponseAddressDTO(
    val cep: String,
    val road: String,
    val city: String,
    val numberResidence: String,
    val complement: String,
    val country: CountryDTO,
    val state: StateUF
) {
    fun toEntity() = Address(
        idAddress = 0,
        cep = this.cep,
        road = this.road,
        city = this.city,
        numberResidence = this.numberResidence,
        complement = this.complement,
        state = this.state
    )

    companion object {
        fun fromToEntity(address: Address, countryDTO: CountryDTO) =
            ResponseAddressDTO(
                cep = address.cep,
                road = address.road,
                city = address.city,
                numberResidence = address.numberResidence,
                complement = address.complement,
                country = countryDTO,
                state = address.state
            )
    }
}
