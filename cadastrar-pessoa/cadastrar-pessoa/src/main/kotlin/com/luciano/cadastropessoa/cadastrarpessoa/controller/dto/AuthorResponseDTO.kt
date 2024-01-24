package com.luciano.cadastropessoa.cadastrarpessoa.controller.dto

import BookDTO
import com.luciano.cadastropessoa.cadastrarpessoa.model.Author
import com.luciano.cadastropessoa.cadastrarpessoa.util.UniqueValue
import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull

data class AuthorResponseDTO(
        @field:NotBlank(message = "O nome não pode estar em branco")
        @field:NotNull("O nome não pode ser nulo")
        val name: String,
        @field:UniqueValue(
                message = "Essa email  já existe!",
                fieldName = "email",
                domainClass = Author::class
        )
        val email: String,
        @field:NotBlank(message = "O nome não pode estar em branco")
        @field:NotNull("O nome não pode ser nulo")
        val description: String,
        val categories: List<CategoryDTO>,
        val books: List<BookDTO>
) {
    fun toEntity() = Author(name = this.name, email = this.email, description = this.description)

    companion object {
        fun fromEntity(author: Author, categoryDTOList: List<CategoryDTO>): AuthorResponseDTO {
            return AuthorResponseDTO(
                    name = author.name,
                    email = author.email,
                    description = author.description,
                    categories = categoryDTOList,
                    books = author.books.map { BookDTO.fromEntity(it, CategoryDTO(name = it.categoryId.name)) }
            )
        }
    }
}
