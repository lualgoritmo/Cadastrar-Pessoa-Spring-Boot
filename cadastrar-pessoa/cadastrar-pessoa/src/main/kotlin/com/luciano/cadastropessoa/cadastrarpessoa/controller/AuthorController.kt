package com.luciano.cadastropessoa.cadastrarpessoa.controller

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.CreateAuthorDTO
import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.UpdateAuthorDTO
import com.luciano.cadastropessoa.cadastrarpessoa.exception.PersonNotFoundException
import com.luciano.cadastropessoa.cadastrarpessoa.model.Author
import com.luciano.cadastropessoa.cadastrarpessoa.service.AuthorService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/peoples")
class AuthorController(private val authorService: AuthorService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createAuthor(@RequestBody @Valid createAuthorDTO: CreateAuthorDTO): CreateAuthorDTO {
        val person: Author = authorService.createAuthor(createAuthorDTO.toEntity())
        return CreateAuthorDTO.fromEntity(person)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllAuthor(): List<CreateAuthorDTO> {
        val authorList: List<Author> = authorService.getAllAuthor()

        if (authorList.isEmpty()) {
            println("Lista Vania no getAllPerson do controller")
        }

        return authorList.map { CreateAuthorDTO.fromEntity(it) }.toList()
    }

    @GetMapping("/{idAuthor}")
    @ResponseStatus(HttpStatus.OK)
    fun getByIdAuthor(@PathVariable idAuthor: Long): CreateAuthorDTO {
        try {
            val author: Author = authorService.getByIdPerson(idAuthor)
            return CreateAuthorDTO.fromEntity(author)
        } catch (e: PersonNotFoundException) {
            println("Id não encontrado aqui $idAuthor")
            throw e
        }
    }

    @PutMapping("/{idAuthor}/updates")
    @ResponseStatus(HttpStatus.CREATED)
    public fun updateAuthorWitchId(@PathVariable idAuthor: Long, @RequestBody @Valid updateAuthorDTO: UpdateAuthorDTO
    ): UpdateAuthorDTO {
        val updateAuthor = authorService.updatePersonWitchId(idAuthor, updateAuthorDTO.toEntity())
        return UpdateAuthorDTO.fromEntity(updateAuthor)
    }

    @DeleteMapping("/{idAuthor}/deletePerson")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun getByIdToDeleteAuthor(@PathVariable idAuthor: Long) = authorService.deleteByIdAuthor(idAuthor)

}
