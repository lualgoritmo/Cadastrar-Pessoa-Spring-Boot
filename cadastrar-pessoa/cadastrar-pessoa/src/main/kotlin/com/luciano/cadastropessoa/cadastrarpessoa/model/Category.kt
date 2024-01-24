package com.luciano.cadastropessoa.cadastrarpessoa.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@Entity
@Table(name = "tb_category")
data class Category(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val idCategory: Long? = null,

        @Column(unique = true)
        @field:NotNull(message = "A categoria não pode ser null")
        @field:NotBlank(message = "O nome não pode estar em branco")
        val name: String,
        @OneToMany(mappedBy = "categoryId", cascade = [CascadeType.ALL])
        val book: List<Book> = emptyList()
)