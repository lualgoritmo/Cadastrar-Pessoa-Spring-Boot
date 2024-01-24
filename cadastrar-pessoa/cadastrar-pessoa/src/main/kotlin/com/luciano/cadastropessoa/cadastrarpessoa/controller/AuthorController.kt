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
class AuthorController(private val authorService: AuthorService) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createAuthor(@RequestBody @Valid requestAuthorDTO: RequestAuthorDTO): RequestAuthorDTO {
        val author: Author = authorService.createAuthor(requestAuthorDTO.toEntity())
        return RequestAuthorDTO.fromEntity(author)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllAuthor(): List<RequestAuthorDTO> {
        val authorList: List<Author> = authorService.getAllAuthor()

        if (authorList.isEmpty()) {
            println("Lista vazia no getAllPerson do controller")
        }

        return authorList.map { RequestAuthorDTO.fromEntity(it) }.toList()
    }

    @GetMapping("/{idAuthor}")
    @ResponseStatus(HttpStatus.OK)
    fun getByIdAuthor(@PathVariable idAuthor: Long): RequestAuthorDTO {
        try {
            val author: Author = authorService.getByIdAuthor(idAuthor)
            return RequestAuthorDTO.fromEntity(author)
        } catch (e: AuthorNotFoundException) {
            println("Id não encontrado aqui $idAuthor")
            throw e
        }
    }

    @PutMapping("/{idAuthor}/updates")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateAuthorWitchId(
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
    fun getInfoComplete(): List<AuthorResponseDTO> {
        return authorService.getAllAuthor().map { author ->
            val categoriesDTO = author.books.map { it.categoryId.name }.distinct().map { CategoryDTO(name = it) }
            author.books.map { book ->
                BookDTO.fromEntity(book, CategoryDTO(name = book.categoryId.name))
            }

            AuthorResponseDTO.fromEntity(author, categoriesDTO)
        }
    }

//    @GetMapping("/inforcomplete")
//    @ResponseStatus(HttpStatus.OK)
//    fun getInfoComplete(): List<AuthorDTO> {
//        return authorService.getAllAuthor().map { author ->
//            val categoriesDTO = author.books.map { it.categoryId.name }.distinct().map { CategoryDTO(name = it) }
//            val booksDTO = author.books.map { book ->
//                BookDTO.fromEntity(book, CategoryDTO(name = book.categoryId.name))
//            }
//
//            AuthorDTO(
//                name = author.name,
//                email = author.email,
//                description = author.description,
//                category = categoriesDTO,
//                books = booksDTO
//            )
//        }
//    }




//    @GetMapping("/inforcomplete")
//    @ResponseStatus(HttpStatus.OK)
//    fun getInfoComplete(): List<AuthorDTO> {
//        val authors: List<Author> = authorService.getAllAuthor()
//
//        return authors.map { author ->
//            val booksDTO = author.books.map { book ->
//                BookDTO(
//                    title = book.title,
//                    isbnBook = book.isbnBook,
//                    resume = book.resume,
//                    summary = book.summary,
//                    price = book.price,
//                    datePost = book.datePost,
//                )
//            }
//            val categoryDTO: CategoryDTO = CategoryDTO(name = author.books.categoryId.name)
//            AuthorDTO(
//                name = author.name,
//                email = author.email,
//                description = author.description,
//                category = categoryDTO,
//                books = booksDTO,
//            )
//        }
//    }

}
