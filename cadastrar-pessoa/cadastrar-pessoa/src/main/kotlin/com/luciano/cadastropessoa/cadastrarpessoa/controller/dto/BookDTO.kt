package com.luciano.cadastropessoa.cadastrarpessoa.controller.dto

import com.luciano.cadastropessoa.cadastrarpessoa.model.Book

data class BookDTO(
    val title: String,
    val isbnBook: String,
    val resume: String,
    val summary: String?,
    val price: Double,
    val datePost: String
) {
    companion object {
        fun fromEntity(book: Book): BookDTO {
            return BookDTO(
                title = book.title,
                isbnBook = book.isbnBook,
                resume = book.resume,
                summary = book.summary,
                price = book.price,
                datePost = book.datePost
            )
        }
    }

}
