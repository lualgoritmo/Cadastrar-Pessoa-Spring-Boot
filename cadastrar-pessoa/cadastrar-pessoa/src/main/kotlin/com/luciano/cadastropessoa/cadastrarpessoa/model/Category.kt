package com.luciano.cadastropessoa.cadastrarpessoa.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Entity
@Table(name = "tb_category")
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val idCategory: Long?,
    @field:NotBlank(message = "O nome não pode estar em branco")
    @field:Size(max = 200, message = "O nome da categoria não pode estar em branco")
    val name: String) {

}