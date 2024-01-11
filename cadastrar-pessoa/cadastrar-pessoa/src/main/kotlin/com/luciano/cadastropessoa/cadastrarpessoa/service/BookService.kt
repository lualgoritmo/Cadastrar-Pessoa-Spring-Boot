package com.luciano.cadastropessoa.cadastrarpessoa.service

import com.luciano.cadastropessoa.cadastrarpessoa.model.Book

interface BookService {
    fun createBook(book: Book): Book
    fun getAllBooks(): List<Book>
    fun getByIdBook(idBook: Long): Book
    fun updateWithBookId(idBook: Long, book: Book): Book
    fun deleteByIdBook(idBook: Long)

}
