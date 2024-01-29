package com.luciano.cadastropessoa.cadastrarpessoa.service

import com.luciano.cadastropessoa.cadastrarpessoa.model.Country
import com.luciano.cadastropessoa.cadastrarpessoa.model.StateUF
import com.luciano.cadastropessoa.cadastrarpessoa.repository.StateRepository
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.StateServiceImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
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

    @BeforeEach
    fun setUP() {
        stateServiceImpl = StateServiceImpl(stateRepository)
    }

    private fun countryObject() = Country(idCountry = 1, "Brasil")
    private fun stateObject() = StateUF(idState = 1, name = "SP", country = countryObject())

    @Test
    fun `when the createState method is called it must return a state`() {
        val state = stateObject()

        `when`(stateRepository.save(state)).thenReturn(state)

        val saveState = stateServiceImpl.createState(state)

        assertThat(saveState).isEqualTo(state)
        assertThat(saveState).isNotNull

        // Verifica se o método save foi chamado no mock stateRepository
        verify(stateRepository, times(1)).save(saveState)
    }

    @Test
    fun `when the getAllState method is called, retur list State`() {

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
    fun `when the getStateById method is called it must return a specific state`() {
        val state = stateObject()

        `when`(stateRepository.findById(state.idState!!)).thenReturn(Optional.of(state))

        // Obtém o estado pelo ID
        val testState = stateServiceImpl.getStateById(state.idState!!)

        // Extrai o valor do Optional retornado pelo repositório
        val retrievedState = stateRepository.findById(state.idState!!).orElse(null)

        // Verifica se o estado retornado é igual ao estado recuperado do repositório
        assertThat(testState).isEqualTo(retrievedState)
        // Verifica se o estado retornado não é nulo
        assertThat(testState).isNotNull
        // Verifica se o estado recuperado não é nulo
        assertThat(retrievedState).isNotNull
        // Verifica se o estado recuperado é igual ao estado original
        assertThat(retrievedState).isEqualTo(state)
    }

    @Test
    fun `when updateWithIdState is called, it should update the state successfully`() {
        // Dados de exemplo
        val existingState = StateUF(
                1L,
                "SP",
                Country(1L, "Brazil")
        )
        val updateState = StateUF(
                1L,
                "Updated State",
                Country(1L, "Brazil")
        )

        // Configuração do mock para simular o estado existente no repositório
        `when`(stateRepository.findById(1L)).thenReturn(Optional.of(existingState))

        // Configuração do mock para simular o salvamento do estado atualizado
        `when`(stateRepository.save(updateState)).thenReturn(updateState)

        // Chamada ao método que está sendo testado
        val updatedState = stateServiceImpl.updateWithIdState(1L, updateState)

        // Verificações
        assertEquals(updateState, updatedState, "O estado atualizado deve ser igual ao esperado")

        // Verifica se o método findById foi chamado corretamente no mock
        verify(stateRepository).findById(1L)

        // Verifica se o método save foi chamado corretamente no mock
        verify(stateRepository).save(updateState)
    }

    @Test
    fun `deleted with id`() {
        val stateUF = stateObject()

        stateRepository.save(stateUF)

        `when`(stateRepository.deleteById(stateUF.idState!!)).then { }
        stateServiceImpl.deleteWithIdState(stateUF.idState!!)

        val deleteState = stateRepository.findById(stateUF.idState!!).orElse(null)

        assertNull(deleteState, "Este State não deveria existir!")
        verify(stateRepository, times(1)).deleteById(stateUF.idState!!)
    }
}
