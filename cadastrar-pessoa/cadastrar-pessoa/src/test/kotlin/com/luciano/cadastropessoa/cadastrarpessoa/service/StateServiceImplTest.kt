package com.luciano.cadastropessoa.cadastrarpessoa.service

import com.luciano.cadastropessoa.cadastrarpessoa.build.CountryEntity
import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.RequireStateDTO
import com.luciano.cadastropessoa.cadastrarpessoa.model.Country
import com.luciano.cadastropessoa.cadastrarpessoa.model.StateUF
import com.luciano.cadastropessoa.cadastrarpessoa.repository.StateRepository
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.CountryServiceImpl
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.StateServiceImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class StateServiceImplTest {
    @InjectMocks
    private lateinit var stateServiceImpl: StateServiceImpl

    @Mock
    private lateinit var stateRepository: StateRepository
    @Mock
    private lateinit var countryService: CountryServiceImpl
    private fun countryObject() = Country(idCountry = 1, "Brasil")
    private fun stateObject() = StateUF(idState = 1, name = "SP", country = countryObject())

    @Test
    fun `when the createState method is called, it must return a state`() {
        val country = CountryEntity().build()
        val createStateDTO = RequireStateDTO("BA", countryId = country.idCountry)

        `when`(countryService.getWithIdCountry(country.idCountry!!)).thenReturn(country)

        val stateToSalve = createStateDTO.toEntity(country)

        `when`(stateRepository.save(stateToSalve)).thenReturn(stateToSalve)

        val saveState = stateServiceImpl.createState(createStateDTO)

        assertThat(saveState).isEqualTo(stateToSalve)
        assertThat(saveState).isNotNull

        verify(stateRepository, times(1)).save(saveState)
    }


    @Test
    fun `when the getAllState method is called, return list State`() {

        val stateUF: StateUF = StateUF(
                1L,
                "BA",
                countryObject()
        )
        val stateUFTwo: StateUF = StateUF(
                2L,
                "ES",
                countryObject()
        )
        val stateUFTree = StateUF(
                3L,
                "SP",
                countryObject()
        )

        `when`(stateRepository.findAll()).thenReturn(listOf(stateUF, stateUFTwo, stateUFTree))

        val allStates = stateServiceImpl.getAllStates()

        assertThat(allStates).containsExactly(stateUF, stateUFTwo, stateUFTree)
        assertThat(allStates.size).isEqualTo(3)
        assertThat(allStates[2].idState).isEqualTo(3)
        assertThat(allStates[2].name).isEqualTo("SP")
    }

    @Test
    fun `when the getStateById method is called, it must return a specific state`() {
        val state = stateObject()

        `when`(stateRepository.findById(state.idState!!)).thenReturn(Optional.of(state))

        val stateReturn = stateServiceImpl.getStateById(state.idState!!)

        assertThat(state).isEqualTo(stateReturn)
        assertThat(stateReturn).isNotNull
        assertThat(state).isNotNull
        assertThat(stateReturn).isEqualTo(state)
        verify(stateRepository, times(1)).findById(state.idState!!)
    }
    @Test
    fun `when updateWithIdState is called, it should update the state successfully`() {
        val existingState = StateUF(
                1L,
                "SP",
                Country(1L, "Brasil")
        )

        val updateState = StateUF(
                1L,
                "Updated State",
                Country(1L, "Brasil")
        )

        `when`(stateRepository.findById(existingState.idState!!)).thenReturn(Optional.of(existingState))

        `when`(stateRepository.save(updateState)).thenReturn(updateState)

        val newState = stateServiceImpl.updateWithIdState(existingState.idState!!, updateState)

        assertThat(existingState).isNotNull
        assertThat(updateState).isNotNull
        assertThat(newState).isEqualTo(updateState)
        assertThat(newState.name).isEqualTo("Updated State")
        assertThat(newState.country.name).isEqualTo("Brasil")
        verify(stateRepository, times(1)).findById(1L)
        verify(stateRepository, times(1)).save(updateState)
    }


    @Test
    fun `when executing deleteWithIdState, it returns nothing`() {
        val stateUF = stateObject()
        stateRepository.save(stateUF)

        `when`(stateRepository.deleteById(stateUF.idState!!)).then { }
        stateServiceImpl.deleteWithIdState(stateUF.idState!!)

        val deleteState = stateRepository.findById(stateUF.idState!!).orElse(null)

        assertNull(deleteState, "Este State não deveria existir!")
        verify(stateRepository, times(1)).deleteById(stateUF.idState!!)
    }
}
