package com.luciano.cadastropessoa.cadastrarpessoa.controller.dto

import com.luciano.cadastropessoa.cadastrarpessoa.model.ClientUser
import com.luciano.cadastropessoa.cadastrarpessoa.util.UniqueValue
import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull

data class CreateClientDTO(
        @field:UniqueValue(
                message = "Este e-mail já está sendo usado!",
                fieldName = "email",
                domainClass = ClientUser::class
        )
        val email: String,
        @field:NotNull("O nome não pode ser nulo")
        @field:NotBlank(message = "O nome não pode estar em branco")
        val name: String,
        @field:NotNull("O sobrenome não pode ser nulo")
        @field:NotBlank(message = "O sosbrenome não pode estar em branco")
        val surname: String,
        @field:NotNull("O documento não pode ser nulo")
        @field:NotBlank(message = "O documento não pode estar em branco")
        val document: String,
        @field:NotNull("O telefone não pode ser nulo")
        @field:NotBlank(message = "O telefone não pode estar em branco")
        val phone: String
) {
    fun toEntity() = ClientUser(
            idClient = 0,
            email = email,
            name = name,
            surname = surname,
            document = document,
            phone = phone,
            addresses = emptyList()
    )

    companion object {
        fun fromEntity(client: ClientUser) = CreateClientDTO(
                email = client.email,
                name = client.name,
                surname = client.surname,
                document = client.document,
                phone = client.phone
        )

    }
}
