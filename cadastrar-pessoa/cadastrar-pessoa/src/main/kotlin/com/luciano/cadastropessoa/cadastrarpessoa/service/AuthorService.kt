package com.luciano.cadastropessoa.cadastrarpessoa.service

import com.luciano.cadastropessoa.cadastrarpessoa.exception.PersonNotFoundException
import com.luciano.cadastropessoa.cadastrarpessoa.model.Author
import com.luciano.cadastropessoa.cadastrarpessoa.repository.AuthorRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class AuthorService(private val personRepository: AuthorRepository) {
    fun createAuthor(person: Author): Author = personRepository.save(person)

    fun getAllAuthor(): List<Author> = personRepository.findAll()

    fun getByIdPerson(idPerson: Long): Author = personRepository.findById(idPerson).orElseThrow {
        PersonNotFoundException(idPerson)
    }

    @Transactional
    public fun updatePersonWitchId(idPerson: Long, updatePerson: Author): Author {
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
    fun deleteByIdAuthor(idPerson: Long) {
        try {
            personRepository.deleteById(idPerson)
        } catch (ex: Exception) {
            throw PersonNotFoundException(idPerson)
        }
    }

}
