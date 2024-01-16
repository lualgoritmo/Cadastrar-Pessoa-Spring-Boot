package com.luciano.cadastropessoa.cadastrarpessoa.controller.dto

import BookDTO
import com.luciano.cadastropessoa.cadastrarpessoa.model.Author

data class AuthorResponse(

    val name: String,

    val email: String,

    val description: String,
    val categories: List<CategoryDTO>,
    val books: List<BookDTO>
) {
    fun toEntity() = Author(idAuthor = 0, name = this.name, this.email, this.description)

    companion object {
        fun fromEntity(author: Author, categoryDTOList: List<CategoryDTO>): AuthorResponse {
            return AuthorResponse(
                name = author.name,
                email = author.email,
                description = author.description,
                categories = categoryDTOList,
                books = author.books.map { BookDTO.fromEntity(it, CategoryDTO(name = it.categoryId.name)) }
            )
        }
    }
}
