package com.luciano.cadastropessoa.cadastrarpessoa.build

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.RequestAuthorDTO
import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.UpdateAuthorDTO
import com.luciano.cadastropessoa.cadastrarpessoa.model.Author

data class AuthorEntity(
        val idAuthor: Long = 1L,
        val name: String = "Luciano",
        val email: String = "luciano@lucianos.com",
        val description: String = "Autor experiente"

) {
    fun build() = Author(
            idAuthor = idAuthor,
            name = name,
            email = email,
            description = description
    )
    fun buildUpdate() = UpdateAuthorDTO(
            name = name,
            email = email,
            description = description
    )
    companion object {
        fun fromEntity(author: AuthorEntity) = RequestAuthorDTO(
                name = author.name,
                email = author.email,
                description = author.description
        )
    }

}
