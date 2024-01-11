package com.luciano.cadastropessoa.cadastrarpessoa.service.impl

import com.luciano.cadastropessoa.cadastrarpessoa.exception.AuthorNotFoundException
import com.luciano.cadastropessoa.cadastrarpessoa.model.Book
import com.luciano.cadastropessoa.cadastrarpessoa.repository.BookRepository
import com.luciano.cadastropessoa.cadastrarpessoa.service.BookService
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class BookServiceImpl(private val bookRepository: BookRepository) : BookService {
    override fun createBook(book: Book) = bookRepository.save(book)
    override fun getAllBooks(): List<Book> = bookRepository.findAll()

    override fun getByIdBook(idBook: Long): Book {
        return bookRepository.findById(idBook).orElseThrow {
            AuthorNotFoundException(idBook)
        }
    }

    override fun updateWithBookId(idBook: Long, book: Book): Book {
        TODO("Not yet implemented")
    }

    override fun deleteByIdBook(idBook: Long) {
        try {
            return bookRepository.deleteById(idBook)
        } catch (ex: EmptyResultDataAccessException) {
            throw AuthorNotFoundException(idBook)
        }

    }
}
