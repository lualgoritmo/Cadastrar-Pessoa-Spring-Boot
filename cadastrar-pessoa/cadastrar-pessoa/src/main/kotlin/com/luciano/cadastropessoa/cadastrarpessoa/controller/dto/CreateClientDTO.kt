package com.luciano.cadastropessoa.cadastrarpessoa.controller.dto

import com.luciano.cadastropessoa.cadastrarpessoa.model.Client
import com.luciano.cadastropessoa.cadastrarpessoa.util.UniqueValue
import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull

data class CreateClientDTO(
        @field:UniqueValue(
                message = "Este e-mail já está sendo usado!",
                fieldName = "email",
                domainClass = Client::class
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
    fun toEntity() = Client(
            idClient = 0,
            email = this.email,
            name = this.name,
            surname = this.surname,
            document = this.document,
            phone = this.phone,
            addresses = emptyList()
    )

    companion object {
        fun fromEntity(client: Client) = CreateClientDTO(
                email = client.email,
                name = client.name,
                surname = client.surname,
                document = client.document,
                phone = client.phone
        )
    }
}
