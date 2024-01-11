package com.luciano.cadastropessoa.cadastrarpessoa.model

import UniqueValue
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "tb_category")
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val idCategory: Long?,
//    @field:UniqueValue(message = "Este nome de categoria já está sendo usado!", fieldName = "name",
//        domainClass = Category::class)
    @field:NotBlank(message = "O nome não pode estar em branco")
    @Column(unique = true)
    val name: String) {

}