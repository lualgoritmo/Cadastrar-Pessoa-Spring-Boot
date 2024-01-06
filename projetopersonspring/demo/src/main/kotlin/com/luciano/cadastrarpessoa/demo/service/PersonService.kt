package com.luciano.cadastrarpessoa.demo.service

import com.luciano.cadastrarpessoa.demo.exception.PersonNotFoundException
import com.luciano.cadastrarpessoa.demo.model.Person
import com.luciano.cadastrarpessoa.demo.repository.PersonRepository
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
    fun deleteByIdPerson(idPerson: Long) {
        try {
            personRepository.deleteById(idPerson)
        } catch (ex: Exception) {
            throw PersonNotFoundException(idPerson)
        }
    }

}
