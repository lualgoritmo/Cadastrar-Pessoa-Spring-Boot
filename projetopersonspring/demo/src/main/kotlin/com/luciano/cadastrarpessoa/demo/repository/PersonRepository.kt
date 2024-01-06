package com.luciano.cadastrarpessoa.demo.repository

import com.luciano.cadastrarpessoa.demo.model.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : JpaRepository<Person, Long>