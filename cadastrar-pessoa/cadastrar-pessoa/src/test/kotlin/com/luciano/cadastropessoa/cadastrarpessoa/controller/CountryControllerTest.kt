package com.luciano.cadastropessoa.cadastrarpessoa.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.luciano.cadastropessoa.cadastrarpessoa.build.CountryEntity
import com.luciano.cadastropessoa.cadastrarpessoa.model.Country
import com.luciano.cadastropessoa.cadastrarpessoa.repository.CountryRepository
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.CountryServiceImpl
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.given
import org.mockito.kotlin.willAnswer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension::class)
class CountryControllerTest {

    @Mock
    private lateinit var countryServiceImpl: CountryServiceImpl

    @Autowired
    private lateinit var countryRepository: CountryRepository

    @Autowired
    private lateinit var mockMvc: MockMvc
    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @BeforeEach
    fun setUp() {
        countryRepository.deleteAll()
        Mockito.reset(countryServiceImpl)
    }

    @Test
    fun `when createCountry is called, it should return country`() {
        val country = CountryEntity().build()

        given(countryServiceImpl.createCountrie(country)).willReturn(country)

        mockMvc.perform(MockMvcRequestBuilders.post("/api/countrys")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(country)))
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andExpect(jsonPath("$.name", Matchers.equalTo(country.name)))
    }
    @Test
    fun`when getByIdCountry is called, it should return one country`() {
        val country = CountryEntity().build()
        countryRepository.save(country)

        given(countryServiceImpl.getWithIdCountry(country.idCountry!!)).willReturn(country)
        mockMvc.perform(MockMvcRequestBuilders.get("/api/countrys/{idCountry}", country.idCountry)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(country)))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(jsonPath("$.name", Matchers.equalTo(country.name)))

    }

    @Test
    fun`when getAllCountries is called, it should return list countries`() {
        val countries: List<Country> = listOf(
                CountryEntity().build(),
                CountryEntity().build(),
                CountryEntity().build()
        )
        countryRepository.saveAll(countries)

        given(countryServiceImpl.getAllCountries()).willReturn(countries)

        mockMvc.perform(MockMvcRequestBuilders.get("/api/countrys")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(countries)))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(jsonPath("$.size()", Matchers.equalTo(countries.size)))
                .andExpect(jsonPath("$[0].name", Matchers.equalTo(countries[0].name)))
    }
    @Test
    fun`when deleteWithIdContry is called, it should return anything`() {
        val country = CountryEntity().build()
        countryRepository.save(country)

        given(countryServiceImpl.deleteWithIdContry(country.idCountry!!)).willAnswer {  }
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/countrys/{idCountry}", country.idCountry)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(country)))
                .andExpect(MockMvcResultMatchers.status().isNoContent)
    }
}
