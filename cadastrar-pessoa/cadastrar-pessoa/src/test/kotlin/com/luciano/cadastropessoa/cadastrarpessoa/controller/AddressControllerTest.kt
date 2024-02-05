package com.luciano.cadastropessoa.cadastrarpessoa.controller

import AddressController
import com.fasterxml.jackson.databind.ObjectMapper
import com.luciano.cadastropessoa.cadastrarpessoa.build.ClientUserEntity
import com.luciano.cadastropessoa.cadastrarpessoa.build.StateEntity
import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.CreateAddressDTO
import com.luciano.cadastropessoa.cadastrarpessoa.model.AddressUser
import com.luciano.cadastropessoa.cadastrarpessoa.repository.AddressRepository
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.AddressServiceImpl
import org.hamcrest.Matchers
import org.hibernate.validator.internal.util.Contracts.assertNotNull
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension::class)
class AddressControllerTest {

    @Mock
    private lateinit var addressServiceImpl: AddressServiceImpl

    @Mock
    private lateinit var addressRepository: AddressRepository

    @Autowired
    private lateinit var mockmvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper


    @BeforeEach
    fun setUp() {
        addressRepository.deleteAll()
            mockmvc = MockMvcBuilders
                    .standaloneSetup(AddressController(addressServiceImpl))
                    .build()
    }
    @Test
    fun `when createAddress is called, it should return address`() {
        val client = ClientUserEntity().build()
        val state = StateEntity().build()

        val createAddress = AddressUser(
                cep = "123456789",
                road = "rua de teste",
                city = "teste",
                numberResidence = "1111111",
                complement = "perto de teste",
                state = state,
                client = client
        )

        given(addressServiceImpl.createAddress(any()))
                .willReturn(createAddress)

        val content = objectMapper.writeValueAsString(CreateAddressDTO.fromEntity(createAddress))

        mockmvc.perform(post("/api/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content ?: "Está vindo nulo"))
                .andExpect(status().isCreated)
                .andExpect(jsonPath("$.cep", Matchers.equalTo(createAddress.cep)))
                .andExpect(jsonPath("$.road", Matchers.equalTo(createAddress.road)))
                .andExpect(jsonPath("$.city", Matchers.equalTo(createAddress.city)))
                .andExpect(jsonPath("$.numberResidence", Matchers.equalTo(createAddress.numberResidence)))
                .andExpect(jsonPath("$.complement", Matchers.equalTo(createAddress.complement)))
    }


//    @Test
//    fun `when createAddress is called, it should return address`() {
//        // Given
//        val client = ClientUserEntity().build()
//        val state = StateEntity().build()
//
//        val createAddress = CreateAddressDTO(
//                cep = "123456789",
//                road = "rua de teste",
//                city = "teste",
//                numberResidence = "1111111",
//                complement = "perto de teste",
//                stateId = state.idState,
//                clientId = client.idClient
//        ).toEntity(client = client, stateUF = state)
//
//        given(addressServiceImpl.createAddress(ArgumentMatchers.any()))
//                .willReturn(createAddress)
//
//        // When/Then
//        mockmvc.perform(post("/api/address")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(mapOf("createAddressDTO" to createAddress))))
//                .andExpect(status().isCreated)
//                .andExpect(jsonPath("$.cep", Matchers.equalTo(createAddress.cep)))
//                .andExpect(jsonPath("$.road", Matchers.equalTo(createAddress.road)))
//                .andExpect(jsonPath("$.city", Matchers.equalTo(createAddress.city)))
//                .andExpect(jsonPath("$.numberResidence", Matchers.equalTo(createAddress.numberResidence)))
//                .andExpect(jsonPath("$.complement", Matchers.equalTo(createAddress.complement)))
//
//        // Adicione verificações adicionais conforme necessário para outros campos
//    }


//    @Test
//    fun `when createAddress is called, it should return address`() {
//        val client = ClientUserEntity().build()
//        val state = StateEntity().build()
//
//        val createAddress = AddressEntity(client = client, state = state).build()
//        given(addressServiceImpl.createAddress(CreateAddressDTO.fromEntity(createAddress))).willReturn(createAddress)
//
//        val content = objectMapper.writeValueAsString(createAddress)
//        assertNotNull(content, "Serialized content should not be null")
//
//        mockmvc.perform(post("/api/address")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content))
//                .andExpect(status().isCreated)
//                .andExpect(jsonPath("$.cep", Matchers.equalTo(createAddress.cep)))
//    }

    @Test
    fun `should delete address by id`() {
        val idAddressToDelete = 1L

        mockmvc.perform(delete("/api/address/$idAddressToDelete/deleteAddress"))
                .andExpect(status().isNoContent)

        val idAddressCaptor = argumentCaptor<Long>()

        verify(addressServiceImpl).deleteWithIdAddress(idAddressCaptor.capture())

        assertNotNull(idAddressCaptor.firstValue)
        assertEquals(idAddressToDelete, idAddressCaptor.firstValue)
    }
}
