package com.luciano.cadastropessoa.cadastrarpessoa.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.luciano.cadastropessoa.cadastrarpessoa.build.CountryEntity
import com.luciano.cadastropessoa.cadastrarpessoa.build.StateEntity
import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.RequireStateDTO
import com.luciano.cadastropessoa.cadastrarpessoa.model.StateUF
import com.luciano.cadastropessoa.cadastrarpessoa.repository.CountryRepository
import com.luciano.cadastropessoa.cadastrarpessoa.repository.StateRepository
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.CountryServiceImpl
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.StateServiceImpl
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.AdditionalMatchers.eq
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
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

    @Mock
    private lateinit var stateServiceImpl: StateServiceImpl

    @Mock
    private lateinit var countryServiceImpl: CountryServiceImpl

    @Autowired
    private lateinit var stateRepository: StateRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Mock
    private lateinit var countryRepository: CountryRepository

    @BeforeEach
    fun setUp() {
        stateRepository.deleteAll()
    }

    @Test
    fun `when createState`() {
        val country = CountryEntity().build()

        val createState = StateEntity(
                name = "Bahia",
                country = country
        ).build()

        given(stateServiceImpl.createState(any())).willReturn(createState)

        mockMvc.perform(MockMvcRequestBuilders.post("/api/states")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createState)))
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(createState.name))
    }



}
