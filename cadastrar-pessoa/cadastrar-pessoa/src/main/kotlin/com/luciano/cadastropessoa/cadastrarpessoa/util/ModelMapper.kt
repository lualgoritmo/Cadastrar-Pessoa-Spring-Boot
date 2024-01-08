package com.luciano.cadastropessoa.cadastrarpessoa.util

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.PersonDTO
import com.luciano.cadastropessoa.cadastrarpessoa.model.Person

fun convertPersonDTOTOPerson(personDTO: PersonDTO): Person {
    require(
        !personDTO.name.isNullOrBlank() || !personDTO.email.isNullOrBlank() ||
                !personDTO.description.isNullOrBlank()
    ) { "Campo vazio!" }
    return Person(0, personDTO.name, personDTO.email, personDTO.description)
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
