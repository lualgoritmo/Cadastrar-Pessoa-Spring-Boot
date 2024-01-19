package com.luciano.cadastropessoa.cadastrarpessoa.repository

import com.luciano.cadastropessoa.cadastrarpessoa.model.Country
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CountryRepository:JpaRepository<Country, Long> {
    @Query("SELECT DISTINCT c FROM Country c LEFT JOIN FETCH c.states")
    fun findAllWithStates(): List<Country>
}