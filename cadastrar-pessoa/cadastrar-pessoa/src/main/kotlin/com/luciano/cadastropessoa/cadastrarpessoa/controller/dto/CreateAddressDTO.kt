package com.luciano.cadastropessoa.cadastrarpessoa.controller.dto

import com.luciano.cadastropessoa.cadastrarpessoa.model.Address
import com.luciano.cadastropessoa.cadastrarpessoa.model.StateUF

data class CreateAddressDTO(
    val cep: String,
    val road: String,
    val city: String,
    val numberResidence: Int,
    val complement: String,
    val countryId: Long,
    val stateId: Long

) {
    fun toEntity(stateUF: StateUF) = Address(
        idAddress = 0,
        cep = this.cep,
        road = this.road,
        city = this.city,
        numberResidence = this.numberResidence,
        complement = this.complement,
        state = stateUF
    )
    companion object {
        fun fromEntity(address: Address) = CreateAddressDTO(
            cep = address.cep,
            road = address.road,
            city = address.city,
            numberResidence = address.numberResidence,
            complement = address.complement,
            countryId = address.state.country.idCountry,
            stateId = address.state.idState
        )
    }
}
