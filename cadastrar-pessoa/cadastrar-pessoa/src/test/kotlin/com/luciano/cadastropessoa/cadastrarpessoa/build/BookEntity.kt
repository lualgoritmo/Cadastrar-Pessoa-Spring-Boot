package com.luciano.cadastropessoa.cadastrarpessoa.build

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.CreateBookDTO
import com.luciano.cadastropessoa.cadastrarpessoa.model.Author
import com.luciano.cadastropessoa.cadastrarpessoa.model.Book
import com.luciano.cadastropessoa.cadastrarpessoa.model.Category

data class BookEntity(
        val idBook: Long = 1,
        val title: String = "Livro Build",
        val isbnBook: String = "123456",
        val resume: String = "Resumo Build",
        val summary: String? = null,
        val price: Double = 20.0,
        val datePost: String = "10/10/2022",
        val authorId: Author,
        val categoryId: Category
) {

    fun build() = Book(
            idBook = idBook,
            title = title,
            isbnBook = isbnBook,
            resume = resume,
            summary = null,
            price = price,
            datePost = datePost,
            authorId = authorId,
            categoryId = categoryId
    )

    companion object {
        fun fromEntity(bookEntity: BookEntity) = CreateBookDTO(
                title = bookEntity.title,
                isbnBook = bookEntity.isbnBook,
                resume = bookEntity.resume,
                summary = null,
                price = bookEntity.price,
                datePost = bookEntity.datePost,
                authorId = bookEntity.authorId.idAuthor,
                categoryId = bookEntity.categoryId.idCategory
        )
    }
}
