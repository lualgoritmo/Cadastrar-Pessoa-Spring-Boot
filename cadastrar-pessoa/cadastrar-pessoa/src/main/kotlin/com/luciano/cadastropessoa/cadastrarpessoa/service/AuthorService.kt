package com.luciano.cadastropessoa.cadastrarpessoa.service

import com.luciano.cadastropessoa.cadastrarpessoa.exception.PersonNotFoundException
import com.luciano.cadastropessoa.cadastrarpessoa.model.Author
import com.luciano.cadastropessoa.cadastrarpessoa.model.Category
import com.luciano.cadastropessoa.cadastrarpessoa.repository.AuthorRepository
import com.luciano.cadastropessoa.cadastrarpessoa.repository.CategoryRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class AuthorService(
    private val personRepository: AuthorRepository,
    private val categoryRepository: CategoryRepository
) {
    fun createAuthor(author: Author): Author = personRepository.save(author)
    fun createCategory(category: Category) = categoryRepository.save(category)
    fun getAllAuthor(): List<Author> = personRepository.findAll()

    fun getByIdPerson(idAuthor: Long): Author = personRepository.findById(idAuthor).orElseThrow {
        PersonNotFoundException(idAuthor)
    }

    @Transactional
    fun updatePersonWitchId(idPerson: Long, updatePerson: Author): Author {
        val existingPerson: Author = personRepository.findById(idPerson)
            .orElseThrow { PersonNotFoundException(idPerson) }

        val updatedPerson = existingPerson.copy(
            name = updatePerson.name,
            email = updatePerson.email,
            description = updatePerson.description
        )

        return personRepository.save(updatedPerson)
    }

    @Transactional
    fun deleteByIdAuthor(idAuthor: Long) {
        try {
            personRepository.deleteById(idAuthor)
        } catch (ex: Exception) {
            throw PersonNotFoundException(idAuthor)
        }
    }
    fun getAllCategorys(): List<Category> = categoryRepository.findAll()

}
