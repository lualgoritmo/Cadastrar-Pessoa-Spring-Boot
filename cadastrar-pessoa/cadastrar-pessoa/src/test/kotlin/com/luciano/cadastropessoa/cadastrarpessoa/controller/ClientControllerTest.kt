package com.luciano.cadastropessoa.cadastrarpessoa.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.luciano.cadastropessoa.cadastrarpessoa.build.ClientUserEntity
import com.luciano.cadastropessoa.cadastrarpessoa.model.ClientUser
import com.luciano.cadastropessoa.cadastrarpessoa.repository.ClientRepository
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.AddressServiceImpl
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.ClientServiceImpl
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension::class)
class ClientControllerTest {
    @Autowired
    private lateinit var addressServiceImpl: AddressServiceImpl

    @Autowired
    private lateinit var clientRepository: ClientRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Mock
    private lateinit var clientServiceImpl: ClientServiceImpl

    @Test
    fun `when createClient is called, it should return clientUser`() {
        val client = ClientUserEntity().build()

        given(clientServiceImpl.createClient(any())).willReturn(client)

        mockMvc.perform(MockMvcRequestBuilders.post("/api/clientîes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(client)))
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(client.name))
    }

    @Test
    fun `when getByIdClient is called, it should one clientuser`() {
        val client = ClientUserEntity().build()

        clientRepository.save(client)

        given(clientServiceImpl.getByIdClient(client.idClient!!)).willReturn(client)

        mockMvc.perform(MockMvcRequestBuilders.get("/api/clientîes/{idClient}", client.idClient)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(client)))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.equalTo(client.name)))
    }

    @Test
    fun `when updateClient is called, it should return update client`() {
        val client = ClientUserEntity().build()
        clientRepository.save(client)

        val updateClient = ClientUser(

                idClient = client.idClient,
                email = "update@update.com",
                name = "Alfredo",
                surname = " Carlos Eite",
                document = "123456789",
                phone = "27-1234-1234"
        )

        given(clientServiceImpl.updateClient(
                idClient = client.idClient!!,
                updateClient)
        ).willReturn(updateClient)

        mockMvc.perform(MockMvcRequestBuilders.put("/api/clientîes/{idClient}/updates", client.idClient)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateClient)))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.equalTo(updateClient.name)))
    }

}
