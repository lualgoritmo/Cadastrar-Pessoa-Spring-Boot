package com.luciano.cadastropessoa.cadastrarpessoa.model

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Entity
@Table(name = "tb_person")
data class Author(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,
    @field:NotBlank(message = "O nome não pode estar em branco")
    val name: String,
    @field:Email(message = "O e-mail deve ser válido")
    val email: String,
    @field:NotBlank(message = "A descrição não pode estar em branco")
    @field:Size(max = 400, message = "A descrição deve ter no máximo 400 caracteres")
    val description: String
)