package com.luciano.cadastrarpessoa.demo.controller

import com.luciano.cadastrarpessoa.demo.controller.dto.PersonDTO
import com.luciano.cadastrarpessoa.demo.exception.PersonNotFoundException
import com.luciano.cadastrarpessoa.demo.model.Person
import com.luciano.cadastrarpessoa.demo.service.PersonService
import com.luciano.cadastrarpessoa.demo.util.convertPersonDTOTOPerson
import com.luciano.cadastrarpessoa.demo.util.convertPersonTOPersonDTO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/people")
class PersonController(private val personService: PersonService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPerson(@RequestBody personDTO: PersonDTO): PersonDTO {
        val person: Person = personService.createPerson(convertPersonDTOTOPerson(personDTO))
        if (person.name == "" || person.name == null || person.email == "" || person.email == null
            || person.description == "" || person.description == null
        ) {
            println("Person não criado no controller")
        }
        return convertPersonTOPersonDTO(person)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllPerson(): List<PersonDTO> {
        val personList: List<Person> = personService.getAllPerson()
        if (personList.isNullOrEmpty()) {
            println("Lista Vania no get do controller")
        }
        return personList.map { convertPersonTOPersonDTO(it) }.toList()
    }

    @GetMapping("/{idPerson}")
    @ResponseStatus(HttpStatus.OK)
    fun getByIdPerson(@PathVariable idPerson: Long): PersonDTO {
        try {
            val person: Person = personService.getByIdPerson(idPerson)
            return convertPersonTOPersonDTO(person)
        } catch (e: PersonNotFoundException) {
            println("Id não encontrado aqui $idPerson")
            throw e
        }
    }

    @DeleteMapping("/{idPerson}/deletePerson")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun getByIdToDeletePerson(@PathVariable idPerson: Long) {
        if (idPerson != null) {
            personService.deleteByIdPerson(idPerson)
        }
        else {
            throw PersonNotFoundException(idPerson)
        }
    }

}
