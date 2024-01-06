package com.luciano.cadastrarpessoa.demo.util

import com.luciano.cadastrarpessoa.demo.controller.dto.PersonDTO
import com.luciano.cadastrarpessoa.demo.model.Person

fun convertPersonDTOTOPerson(personDTO: PersonDTO): Person {
    require(
        !personDTO.name.isNullOrBlank() || !personDTO.email.isNullOrBlank() ||
                !personDTO.description.isNullOrBlank()
    ) { "Campo vazio!" }
    return Person(null, personDTO.name, personDTO.email, personDTO.description)
}

fun convertPersonTOPersonDTO(person: Person): PersonDTO {
    if (person.name == "" || person.email == "" || person.description == "") {
        println("O DTO não existe")
    }
//    require(
//        !person.name.isNullOrBlank() || !person.email.isNullOrBlank() ||
//                !person.description.isNullOrBlank()
//    ) { "Campo vazio!" }
    return PersonDTO(person.name, person.email, person.description)
}
