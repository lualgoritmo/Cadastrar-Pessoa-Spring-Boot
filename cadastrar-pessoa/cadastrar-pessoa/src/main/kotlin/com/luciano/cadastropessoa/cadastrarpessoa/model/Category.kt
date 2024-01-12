package com.luciano.cadastropessoa.cadastrarpessoa.model

import com.luciano.cadastropessoa.cadastrarpessoa.util.UniqueValue
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "tb_category")
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val idCategory: Long,
    @field:UniqueValue(
        message = "Esta categoria ja existe!",
        fieldName = "category",
        domainClass = Category::class
    )
    @field:NotBlank(message = "O nome não pode estar em branco")
    @Column(unique = true) val name: String
)