package com.luciano.cadastropessoa.cadastrarpessoa.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.jetbrains.annotations.NotNull
import java.util.*

@Entity
@Table(name = "tb_book")
data class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idBook: Long? = null,

    @field:NotBlank(message = "O nome não pode estar em branco")
    @field:NotNull("O nome não pode ser nulo")
    val title: String,

    @field:NotNull("O ISBN não pode ser nulo")
    @field:NotBlank(message = "O ISBN não pode estar em branco")
    val isbnBook: Int,

    @field:NotBlank(message = "O resume não pode estar em branco")
    @field:NotNull("O resume não pode ser nulo")
    @field:Size(max = 500, message = "A descrição deve ter no máximo 400 caracteres")
    val resume: String,

    @field:NotBlank(message = "O summary não pode estar em branco")
    @field:NotNull("O summary não pode ser nulo")
    val summary: String? = null,

    @field:NotBlank(message = "O preço não pode estar em branco")
    @field:NotNull("O preço não pode ser nulo")
    val price: Double = 20.0,

    @field:NotBlank(message = "A data não pode estar em branco")
    @field:NotNull("A data não pode ser nulo")
    val datePost: Calendar,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "author_id")
    val author: Author
)