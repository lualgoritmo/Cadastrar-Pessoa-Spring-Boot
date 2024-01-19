package com.luciano.cadastropessoa.cadastrarpessoa.service

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.ResponseCountryWithStatesDTO
import com.luciano.cadastropessoa.cadastrarpessoa.model.Country

interface CountryService {
    fun createCountrie(countries: Country): Country
    fun getAllCountries(): List<Country>
    fun getWithIdCountry(idCountry: Long): Country
    fun getAllCountriesWithStates(): List<ResponseCountryWithStatesDTO>
    fun updateWithIdCountry(idCountry: Long, updateCountry: Country): Country
    fun deleteWithIdContry(idCountry: Long)
}