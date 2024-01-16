package com.luciano.cadastropessoa.cadastrarpessoa.controller

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.CreateCountryDTO
import com.luciano.cadastropessoa.cadastrarpessoa.exception.CountryNotFoundException
import com.luciano.cadastropessoa.cadastrarpessoa.model.Country
import com.luciano.cadastropessoa.cadastrarpessoa.service.CountryService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/countrys")
class CountryController(private val countryService: CountryService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCountry(@RequestBody @Valid createCountryDTO: CreateCountryDTO): CreateCountryDTO {
        val country: Country = countryService.createCountrie(createCountryDTO.toEntity())
        try {
            return CreateCountryDTO.fromEntity(country)
        } catch (ex: MethodArgumentNotValidException) {
            throw ex
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllCountrys(): List<CreateCountryDTO> {
        val countryList: List<Country> = countryService.getAllCountries()
        if (countryList.isEmpty()) {
            println(" Lista de Countries vazia")
        }
        return countryList.map { CreateCountryDTO.fromEntity(it) }.toList()
    }

    @GetMapping("/{idCountry}")
    @ResponseStatus(HttpStatus.OK)
    fun getByIdCountry(@PathVariable idCountry: Long): CreateCountryDTO {
        try {
            val country: Country = countryService.getWithIdCountry(idCountry)
            return CreateCountryDTO.fromEntity(country)
        } catch (ex: CountryNotFoundException) {
            throw ex
        }
    }

    @PutMapping("/{idCountry}")
    @ResponseStatus(HttpStatus.OK)
    fun updateWithIdCountry(
        @PathVariable idCountry: Long,
        @RequestBody @Valid updateCountryDTO: CreateCountryDTO
    ): CreateCountryDTO {
        val country: Country = countryService.updateWithIdCountry(idCountry, updateCountryDTO.toEntity())
        return CreateCountryDTO.fromEntity(country)
    }

    @DeleteMapping("/{idCountry}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteWithIdContry(@PathVariable idCountry: Long) {
        try {
            return countryService.deleteWithIdContry(idCountry)
        } catch (ex: CountryNotFoundException) {
            throw ex
        }
    }
}
