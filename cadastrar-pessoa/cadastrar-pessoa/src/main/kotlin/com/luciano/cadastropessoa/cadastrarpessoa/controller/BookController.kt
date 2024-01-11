package com.luciano.cadastropessoa.cadastrarpessoa.controller

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.CreateBookDTO
import com.luciano.cadastropessoa.cadastrarpessoa.model.Book
import com.luciano.cadastropessoa.cadastrarpessoa.service.BookService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/books")
class BookController(private val bookService: BookService) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createsBook(bookDTO: CreateBookDTO): CreateBookDTO {
        val book: Book = bookService.createBook(bookDTO.toEntity())
        return CreateBookDTO.fromEntity(book)
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllBooks(): List<CreateBookDTO> {
        val books: List<Book> = bookService.getAllBooks()
        if (books.isEmpty()) {
            println("A lista está vazia!")
        }
        return books.map { CreateBookDTO.fromEntity(it) }.toList()
    }

    @GetMapping("/idBook")
    @ResponseStatus(HttpStatus.OK)
    fun getBookWithId(@PathVariable idBook: Long): CreateBookDTO {
        try {
            val book = bookService.getByIdBook(idBook)
            return CreateBookDTO.fromEntity(book)
        } catch (e: Exception) {
            println("Id não existente")
            throw e
        }
    }
    @DeleteMapping("/idBooks/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBookWithId(@PathVariable idBook: Long) = bookService.deleteByIdBook(idBook)

}
