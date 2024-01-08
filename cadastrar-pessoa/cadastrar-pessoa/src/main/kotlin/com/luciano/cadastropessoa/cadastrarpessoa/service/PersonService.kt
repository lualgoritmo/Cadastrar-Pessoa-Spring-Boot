package com.luciano.cadastropessoa.cadastrarpessoa.service

import com.luciano.cadastropessoa.cadastrarpessoa.exception.PersonNotFoundException
import com.luciano.cadastropessoa.cadastrarpessoa.model.Person
import com.luciano.cadastropessoa.cadastrarpessoa.repository.PersonRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class PersonService(private val personRepository: PersonRepository) {

    @Transactional
    fun createPerson(person: Person): Person = personRepository.save(person)

    @Transactional
    fun getAllPerson(): List<Person> = personRepository.findAll()

    @Transactional
    fun getByIdPerson(idPerson: Long): Person = personRepository.findById(idPerson).orElseThrow {
        PersonNotFoundException(idPerson)
    }

    @Transactional
    public fun updatePersonWitchId(idPerson: Long, updatePerson: Person): Person {
        val existingPerson: Person = personRepository.findById(idPerson)
            .orElseThrow { PersonNotFoundException(idPerson) }

        val updatedPerson = existingPerson.copy(
            name = updatePerson.name,
            email = updatePerson.email,
            description = updatePerson.description
        )
        return personRepository.save(updatedPerson)
    }

    @Transactional
    fun deleteByIdPerson(idPerson: Long) {
        try {
            personRepository.deleteById(idPerson)
        } catch (ex: Exception) {
            throw PersonNotFoundException(idPerson)
        }
    }

}
