package com.luciano.cadastropessoa.cadastrarpessoa.controller

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.*
import com.luciano.cadastropessoa.cadastrarpessoa.exception.AuthorNotFoundException
import com.luciano.cadastropessoa.cadastrarpessoa.model.Author
import com.luciano.cadastropessoa.cadastrarpessoa.service.AuthorService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/authors")
class
AuthorController(private val authorService: AuthorService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createAuthor(@RequestBody @Valid createAuthorDTO: CreateAuthorDTO): CreateAuthorDTO {
        val author: Author = authorService.createAuthor(createAuthorDTO.toEntity())
        return CreateAuthorDTO.fromEntity(author)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllAuthor(): List<CreateAuthorDTO> {
        val authorList: List<Author> = authorService.getAllAuthor()

        if (authorList.isEmpty()) {
            println("Lista vazia no getAllPerson do controller")
        }

        return authorList.map { CreateAuthorDTO.fromEntity(it) }.toList()
    }

    @GetMapping("/{idAuthor}")
    @ResponseStatus(HttpStatus.OK)
    fun getByIdAuthor(@PathVariable idAuthor: Long): CreateAuthorDTO {
        try {
            val author: Author = authorService.getByIdAuthor(idAuthor)
            return CreateAuthorDTO.fromEntity(author)
        } catch (e: AuthorNotFoundException) {
            println("Id não encontrado aqui $idAuthor")
            throw e
        }
    }

    @PutMapping("/{idAuthor}/updates")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public fun updateAuthorWitchId(
        @PathVariable idAuthor: Long, @RequestBody @Valid updateAuthorDTO: UpdateAuthorDTO
    ): UpdateAuthorDTO {
        val updateAuthor = authorService.updateAuthorWithId(idAuthor, updateAuthorDTO.toEntity())
        return UpdateAuthorDTO.fromEntity(updateAuthor)
    }

    @DeleteMapping("/{idAuthor}/deleteAuthor")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun getByIdToDeleteAuthor(@PathVariable idAuthor: Long) = authorService.deleteByIdAuthor(idAuthor)

    @GetMapping("/inforcomplete")
    @ResponseStatus(HttpStatus.OK)
    fun getInfoComplete(): List<AuthorDTO> {
        val authors: List<Author> = authorService.getAllAuthor()

        return authors.map { author ->
            val booksDTO = author.books.map { book ->
                BookDTO(
                    title = book.title,
                    isbnBook = book.isbnBook,
                    resume = book.resume,
                    summary = book.summary,
                    price = book.price,
                    datePost = book.datePost,
                )
            }
            val categoryDTO: CategoryDTO = CategoryDTO(name = author.books.first().categoryId.name)
            AuthorDTO(
                name = author.name,
                email = author.email,
                description = author.description,
                category = categoryDTO,
                books = booksDTO,
            )
        }
    }

}
