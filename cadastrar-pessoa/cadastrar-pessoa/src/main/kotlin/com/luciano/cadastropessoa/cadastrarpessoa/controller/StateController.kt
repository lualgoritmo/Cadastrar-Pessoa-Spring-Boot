
package com.luciano.cadastropessoa.cadastrarpessoa.controller

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.RequireStateDTO
import com.luciano.cadastropessoa.cadastrarpessoa.exception.StateNotFoundException
import com.luciano.cadastropessoa.cadastrarpessoa.model.Country
import com.luciano.cadastropessoa.cadastrarpessoa.model.StateUF
import com.luciano.cadastropessoa.cadastrarpessoa.service.CountryService
import com.luciano.cadastropessoa.cadastrarpessoa.service.StateService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/states")
class StateController(private val stateService: StateService, private val countryService: CountryService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createState(@RequestBody @Valid createStateDTO: RequireStateDTO): RequireStateDTO {
        val country: Country = countryService.getWithIdCountry(createStateDTO.countryId)

        val newState = StateUF(idState = 0, name = createStateDTO.name, country = country)
        country.states = country.states.toMutableList().apply { add(newState) }

        val createState: StateUF = stateService.createState(newState)

        return RequireStateDTO.fromEntity(createState)
    }

//    fun createState(@RequestBody @Valid createStateDTO: CreateStateDTO): CreateStateDTO {
//        val country: Country = countryService.getWithIdCountry(createStateDTO.countryId)
//        val newState = StateUSA(idState = 0, name = "", countryId = country)
//        //country.states.add(newState)
//        val updateState = country.states.toMutableList().apply { add(newState) }
//        country.states = updateState
//        val createState: StateUSA = stateService.createState(newState)
//        return CreateStateDTO.fromEntity(createState)
//    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllStates(): List<RequireStateDTO> {
        val stateList: List<StateUF> = stateService.getAllStates()
        return stateList.map { RequireStateDTO.fromEntity(it) }
    }

    @GetMapping("/{idState}")
    @ResponseStatus(HttpStatus.OK)
    fun getWithIdState(@PathVariable idState: Long): RequireStateDTO {
        try {
            val state = stateService.getWithIdState(idState)
            return RequireStateDTO.fromEntity(state)
        } catch (ex: StateNotFoundException) {
            throw ex
        }
    }

    @DeleteMapping("/{idState}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteWithIdState(@PathVariable idState: Long) {
        try {
            return stateService.deleteWithIdState(idState)
        } catch (ex: StateNotFoundException) {
            throw ex
        }
    }
}