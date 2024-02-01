package com.luciano.cadastropessoa.cadastrarpessoa.build

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.CreateAddressDTO
import com.luciano.cadastropessoa.cadastrarpessoa.model.AddressUser
import com.luciano.cadastropessoa.cadastrarpessoa.model.ClientUser
import com.luciano.cadastropessoa.cadastrarpessoa.model.StateUF

data class AddressEntity(
        val idAddress: Long = 1,
        val cep: String = "12345-123",
        val road: String = "Exemplo de rua",
        val city: String = "Cidade",
        val numberResidence: String = "123",
        val complement: String = "Complemento",
        val state: StateUF,
        val client: ClientUser
) {
    fun build() = AddressUser(
            idAddress = idAddress,
            cep = cep,
            road = road,
            city = city,
            numberResidence = numberResidence,
            complement = complement,
            state = state,
            client = client
    )

    companion object {
        fun fromEntity(addressUser: AddressUser) = CreateAddressDTO(
                cep = addressUser.cep,
                road = addressUser.road,
                city = addressUser.city,
                numberResidence = addressUser.numberResidence,
                complement = addressUser.complement,
                stateId = addressUser.state.idState,
                clientId = addressUser.client.idClient
        )
    }
}

