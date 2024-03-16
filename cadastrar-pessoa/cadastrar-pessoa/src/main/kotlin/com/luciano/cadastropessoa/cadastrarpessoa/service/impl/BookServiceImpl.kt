package com.luciano.cadastropessoa.cadastrarpessoa.service.impl

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.CreateBookDTO
import com.luciano.cadastropessoa.cadastrarpessoa.exception.BookNotFoundException
import com.luciano.cadastropessoa.cadastrarpessoa.model.Author
import com.luciano.cadastropessoa.cadastrarpessoa.model.Book
import com.luciano.cadastropessoa.cadastrarpessoa.model.Category
import com.luciano.cadastropessoa.cadastrarpessoa.repository.BookRepository
import com.luciano.cadastropessoa.cadastrarpessoa.service.AuthorService
import com.luciano.cadastropessoa.cadastrarpessoa.service.BookService
import com.luciano.cadastropessoa.cadastrarpessoa.service.CategoryService
import jakarta.transaction.Transactional
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class BookServiceImpl(
        private val bookRepository: BookRepository,
        private val authorService: AuthorService,
        private val categoryService: CategoryService
) : BookService {
    @Transactional
    override fun createBook(bookDTO: CreateBookDTO): Book {
        try {
            val author: Author = authorService.getByIdAuthor(bookDTO.authorId!!)
            val category: Category = categoryService.getByIdCategory(bookDTO.categoryId!!)
            return bookRepository.save(bookDTO.toEntity(author, category))
        } catch (ex: EmptyResultDataAccessException) {
            throw BookNotFoundException(bookDTO.authorId!!)
        }

    }
    override fun getAllBooks(): List<Book> = bookRepository.findAll()
    @Transactional
    override fun getByIdBook(idBook: Long): Book {
        try {
            return bookRepository.findById(idBook).orElseThrow {
                BookNotFoundException(idBook)
            }
        } catch (ex: Exception) {
            throw ex
        }
    }
    @Transactional
    override fun updateWithBookId(idBook: Long, book: Book): Book {
        val existingBook: Book = bookRepository.findById(idBook).orElseThrow {
            throw BookNotFoundException(idBook)
        }

        val updateBook = existingBook.copy(
            title = book.title,
            isbnBook = book.isbnBook,
            summary = book.summary,
            resume = book.resume,
            price = book.price,
            datePost = book.datePost,
            author = book.author
        )
        return bookRepository.save(updateBook)
    }
    override fun deleteByIdBook(idBook: Long) {
        try {
            return bookRepository.deleteById(idBook)
        } catch (ex: EmptyResultDataAccessException) {
            throw BookNotFoundException(idBook)
        }

    }
}
