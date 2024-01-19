package com.luciano.cadastropessoa.cadastrarpessoa.controller

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.CreateBookDTO
import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.UpdateBookDTO
import com.luciano.cadastropessoa.cadastrarpessoa.exception.BookNotFoundException
import com.luciano.cadastropessoa.cadastrarpessoa.model.Book
import com.luciano.cadastropessoa.cadastrarpessoa.service.BookService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/books")
class BookController(private val bookService: BookService) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createsBook(@RequestBody @Valid bookDTO: CreateBookDTO): CreateBookDTO? {
        val book: Book = bookService.createBook(bookDTO)
        return CreateBookDTO.fromEntity(book)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllBooks(): List<CreateBookDTO> {
        val books: List<Book> = bookService.getAllBooks().also {
            if (it.isEmpty()) {
                println("Está lista está vazia!")
            }
            println("A lista possui ${it.size} itens")
        }
        return books.map { CreateBookDTO.fromEntity(it) }.toList()
    }

    @GetMapping("/{idBook}")
    @ResponseStatus(HttpStatus.OK)
    fun getBookWithId(@PathVariable idBook: Long): CreateBookDTO {
        try {
            val book = bookService.getByIdBook(idBook)
            return CreateBookDTO.fromEntity(book)
        } catch (ex: BookNotFoundException) {
            throw ex
        }
    }

    @PutMapping("/{idBook}/update")
    @ResponseStatus(HttpStatus.OK)
    fun updateBook(@PathVariable idBook: Long, @RequestBody @Valid updateBookDTO: UpdateBookDTO): UpdateBookDTO {
        val updateBook = bookService.updateWithBookId(idBook, updateBookDTO.toEntity())
        return UpdateBookDTO.fromEntity(updateBook)
    }

    @DeleteMapping("/{idBook}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBookWithId(@PathVariable idBook: Long) = bookService.deleteByIdBook(idBook)

}
