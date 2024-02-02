package com.luciano.cadastropessoa.cadastrarpessoa.model

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.jetbrains.annotations.NotNull
@Entity
@Table(name = "tb_author")
data class Author(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     val idAuthor: Long? = null,
    @field:NotBlank(message = "O nome não pode estar em branco")
    @field:NotNull("O nome não pode ser nulo") val name: String,
//    @Column(unique = true)

    @field:NotBlank(message = "O e-mail não pode estar em branco")
    @field:Size(max = 200, message = "O nome da categoria não pode estar em branco")
    @field:Email(message = "O e-mail deve ser válido")
    val email: String,
    @field:NotBlank(message = "A descrição não pode estar em branco")
    @field:Size(max = 400, message = "A descrição deve ter no máximo 400 caracteres")
    val description: String,

    @OneToMany(mappedBy = "author", cascade = [CascadeType.ALL])
    @JsonBackReference
    val books: List<Book> = emptyList()
)
