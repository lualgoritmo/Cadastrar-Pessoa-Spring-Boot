package com.luciano.cadastropessoa.cadastrarpessoa.controller.dto

import com.luciano.cadastropessoa.cadastrarpessoa.model.Address
import com.luciano.cadastropessoa.cadastrarpessoa.model.ClientUser

class FullClientDTO(
        val email: String,
        val name: String,
        val surname: String,
        val document: String,
        val phone: String,
        val country: String,
        val state: String,
        val address: AddressResponseDTO
) {
    companion object {
        fun fromEntity(client: ClientUser, address: Address, responseAddressDTO: AddressResponseDTO) = FullClientDTO(
                email = client.email,
                name = client.name,
                surname = client.surname,
                document = client . document,
                phone = client.phone,
                country = address.state.country.name,
                state = address.state.name,
                address = responseAddressDTO
        )
    }
}
