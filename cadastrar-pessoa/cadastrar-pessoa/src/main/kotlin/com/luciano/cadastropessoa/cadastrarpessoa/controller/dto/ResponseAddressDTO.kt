package com.luciano.cadastropessoa.cadastrarpessoa.controller.dto

import com.luciano.cadastropessoa.cadastrarpessoa.model.Address
import com.luciano.cadastropessoa.cadastrarpessoa.model.ClientUser
import com.luciano.cadastropessoa.cadastrarpessoa.model.StateUF

class ResponseAddressDTO(
        val cep: String,
        val road: String,
        val city: String,
        val numberResidence: String,
        val complement: String,
        val state: String
) {
    fun toEntity(state: StateUF, client: ClientUser) = Address(
            cep = this.cep,
            road = this.road,
            city = this.city,
            numberResidence = this.numberResidence,
            complement = this.complement,
            state = state,
            client = client
    )

    companion object {
        fun fromToEntity(address: Address): ResponseAddressDTO {

            return ResponseAddressDTO(
                    cep = address.cep,
                    road = address.road,
                    city = address.city,
                    numberResidence = address.numberResidence,
                    complement = address.complement,
                    state = address.state.name
            )
        }
    }
}
