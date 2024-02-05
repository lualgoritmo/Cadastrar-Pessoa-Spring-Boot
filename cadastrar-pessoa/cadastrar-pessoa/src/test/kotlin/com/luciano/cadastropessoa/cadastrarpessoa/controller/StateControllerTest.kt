package com.luciano.cadastropessoa.cadastrarpessoa.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.luciano.cadastropessoa.cadastrarpessoa.build.CountryEntity
import com.luciano.cadastropessoa.cadastrarpessoa.build.StateEntity
import com.luciano.cadastropessoa.cadastrarpessoa.repository.StateRepository
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.StateServiceImpl
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension::class)
class StateControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var stateServiceImpl: StateServiceImpl

    @Autowired
    private lateinit var stateRepository: StateRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @BeforeEach
    fun setUp() {
        stateRepository.deleteAll()
    }

    @Test
    fun `when createState is called, it should return state`() {
        val createState = StateEntity(name = "BA", country = CountryEntity().build())

        given(stateServiceImpl.createState(any())).willReturn(createState.build())

        mockMvc.perform(MockMvcRequestBuilders.post("/api/states")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createState)))
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(createState.name))

       verify(stateServiceImpl, times(1)).createState(any())
    }
    @Test
    fun`when getWithIdState is called, it should return one state`() {
        val state = StateEntity(name = "BA", country = CountryEntity().build()).build()

        given(stateServiceImpl.getStateById(state.idState!!)).willReturn(state)

        mockMvc.perform(MockMvcRequestBuilders.get("/api/states/{idState}", state.idState)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(state)))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.equalTo(state.name)))

        verify(stateServiceImpl, times(1)).getStateById(state.idState!!)
    }


    @Test
    fun`when deleteWithIdState is called, it should returns nothing`() {
        val state = StateEntity(name = "BA", country = CountryEntity().build()).build()

        given(stateServiceImpl.deleteWithIdState(state.idState!!)).willAnswer {}

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/states/{idState}", state.idState)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(state)))
                .andExpect(MockMvcResultMatchers.status().isNoContent)

        verify(stateServiceImpl, times(1)).deleteWithIdState(state.idState!!)

    }
}
