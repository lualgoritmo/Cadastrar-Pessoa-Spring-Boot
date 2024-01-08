package com.luciano.cadastropessoa.cadastrarpessoa.model

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

@Entity
@Table(name = "tb_person")
data class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,
    @field:NotEmpty(message = "O nome não pode estar em branco")
    val name: String,
    @field:Email(message = "O e-mail deve ser válido")
    val email: String,
    @field:NotEmpty(message = "A descrição não pode estar em branco")
    @field:Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres")
    val description: String
)