package com.luciano.cadastropessoa.cadastrarpessoa.controller.dto

import com.luciano.cadastropessoa.cadastrarpessoa.model.AddressUser
import com.luciano.cadastropessoa.cadastrarpessoa.model.ClientUser
import com.luciano.cadastropessoa.cadastrarpessoa.model.StateUF
import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull

data class CreateAddressDTO(
        @field:NotBlank(message = "O cep não pode estar em branco")
        @field:NotNull("O ceps não pode ser nulo")
        val cep: String,

        @field:NotBlank(message = "A rua não pode estar em branco")
        @field:NotNull("A rua não pode ser nulo")
        val road: String,

        @field:NotBlank(message = "A cidade  não pode estar em branco")
        @field:NotNull("A cidade não pode ser nulo")
        val city: String,

        @field:NotBlank(message = "O número da residência não pode estar em branco")
        @field:NotNull("O número da residência não pode ser nulo")
        val numberResidence: String,

        @field:NotBlank(message = "O nome não pode estar em branco")
        @field:NotNull("O nome não pode ser nulo")
        val complement: String,
        val stateId: Long?,
        val clientId: Long?
) {
    fun toEntity(stateUF: StateUF, client: ClientUser) = AddressUser(
            cep = this.cep,
            road = this.road,
            city = this.city,
            numberResidence = this.numberResidence,
            complement = this.complement,
            state = stateUF,
            client = client
    )
    companion object {
        fun fromEntity(address: AddressUser) = CreateAddressDTO(
                cep = address.cep,
                road = address.road,
                city = address.city,
                numberResidence = address.numberResidence,
                complement = address.complement,
                stateId = address.state.idState,
                clientId = address.client.idClient
        )
    }
}
