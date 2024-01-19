package com.luciano.cadastropessoa.cadastrarpessoa.controller

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.CountryDTO
import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.ResponseCountryWithStatesDTO
import com.luciano.cadastropessoa.cadastrarpessoa.exception.CountryNotFoundException
import com.luciano.cadastropessoa.cadastrarpessoa.model.Country
import com.luciano.cadastropessoa.cadastrarpessoa.service.CountryService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/countrys")
class CountryController(private val countryServiceImpl: CountryService) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCountry(@RequestBody @Valid createCountryDTO: CountryDTO): CountryDTO {
        val country: Country = countryServiceImpl.createCountrie(createCountryDTO.toEntity())
        try {
            return CountryDTO.fromEntity(country)
        } catch (ex: MethodArgumentNotValidException) {
            throw ex
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllCountrys(): List<CountryDTO> {
        val countryList: List<Country> = countryServiceImpl.getAllCountries()
        if (countryList.isEmpty()) {
            println(" Lista de Countries vazia")
        }
        return countryList.map { CountryDTO.fromEntity(it) }.toList()
    }

    @GetMapping("/countriestate")
    @ResponseStatus(HttpStatus.OK)
    fun getAllCountriesWithStates(): List<ResponseCountryWithStatesDTO> = countryServiceImpl.getAllCountriesWithStates()

    @GetMapping("/{idCountry}")
    @ResponseStatus(HttpStatus.OK)
    fun getByIdCountry(@PathVariable idCountry: Long): CountryDTO {
        try {
            val country: Country = countryServiceImpl.getWithIdCountry(idCountry)
            return CountryDTO.fromEntity(country)
        } catch (ex: CountryNotFoundException) {
            throw ex
        }
    }

    @PutMapping("/{idCountry}")
    @ResponseStatus(HttpStatus.OK)
    fun updateWithIdCountry(
        @PathVariable idCountry: Long,
        @RequestBody @Valid updateCountryDTO: CountryDTO
    ): CountryDTO {
        val country: Country = countryServiceImpl.updateWithIdCountry(idCountry, updateCountryDTO.toEntity())
        return CountryDTO.fromEntity(country)
    }

    @DeleteMapping("/{idCountry}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteWithIdContry(@PathVariable idCountry: Long) {
        try {
            return countryServiceImpl.deleteWithIdContry(idCountry)
        } catch (ex: CountryNotFoundException) {
            throw ex
        }
    }

//    @GetMapping("/countryAllstates")
//    @ResponseStatus(HttpStatus.OK)
//    fun countrywithstatesDTO(): List<CountryWithStatesDTO> {
//        val countries = countryService.getAllCountries()
//        return countries.map { country ->
//            CountryWithStatesDTO(
//                name = country.name, states = country.states.map { state ->
//                StateDTO(name = state.name)
//
//            }
//            )
//        }
//    }
}
