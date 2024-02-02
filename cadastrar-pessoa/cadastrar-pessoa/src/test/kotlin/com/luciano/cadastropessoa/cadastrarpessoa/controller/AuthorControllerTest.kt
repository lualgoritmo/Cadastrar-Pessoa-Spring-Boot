package com.luciano.cadastropessoa.cadastrarpessoa.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.luciano.cadastropessoa.cadastrarpessoa.build.AuthorEntity
import com.luciano.cadastropessoa.cadastrarpessoa.model.Author
import com.luciano.cadastropessoa.cadastrarpessoa.repository.AuthorRepository
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.AuthorServiceImpl
import jakarta.persistence.EntityManager
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.*
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
class AuthorControllerTest {

    @Autowired
    private lateinit var authorRepository: AuthorRepository

    @Mock
    private lateinit var authorServiceImpl: AuthorServiceImpl

    @Autowired
    private lateinit var mockmvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @BeforeEach
    fun setUp() {
        authorRepository.deleteAll()
        Mockito.reset(authorServiceImpl)
    }

    @Test
    fun `when createAuthor is called, it should return author`() {
        val authorEntity = AuthorEntity()
        val requestAuthorDTO = AuthorEntity.fromEntity(authorEntity)

        BDDMockito.given(authorServiceImpl.createAuthor(any()))
                .willReturn(authorEntity.build())


        mockmvc.perform(MockMvcRequestBuilders.post("/api/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestAuthorDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.equalTo(authorEntity.name)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.equalTo(authorEntity.email)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", Matchers.equalTo(authorEntity.description)))
    }

    @Test
    fun `when getByIdAuthor is called, it should return author`() {
        val author = AuthorEntity().build()
       authorRepository.save(author)

        given(authorServiceImpl.getByIdAuthor(author.idAuthor!!)).willReturn(author)

        mockmvc.perform(MockMvcRequestBuilders.get("/api/authors/{idAuthor}", author.idAuthor)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(jsonPath("$.name", Matchers.equalTo(author.name)))

    }

    @Test
    fun `when getAllAuthor is called, it should return list author`() {
        val listAuthorEntity: List<Author> = listOf(
                AuthorEntity().build(),
                AuthorEntity().build()
        )
        authorRepository.saveAll(listAuthorEntity)

        given(authorServiceImpl.getAllAuthor()).willReturn(listAuthorEntity)

        mockmvc.perform(MockMvcRequestBuilders.get("/api/authors")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(jsonPath("$.size()", Matchers.equalTo(1)))
                .andExpect(jsonPath("$[0].name", Matchers.equalTo(listAuthorEntity[0].name)))
                .andExpect(jsonPath("$[0].email", Matchers.equalTo(listAuthorEntity[0].email)))

        //verify(authorServiceImpl, times(1)).getAllAuthor()
    }

    @Test
    fun`when getByIdToDeleteAuthor is called, it should remove author`() {
        val author = AuthorEntity().build()
        authorRepository.save(author)

        given(authorServiceImpl.deleteByIdAuthor(author.idAuthor!!)).willAnswer{}

        mockmvc.perform(MockMvcRequestBuilders.delete(
                "/api/authors//{idAuthor}/deleteAuthor", author.idAuthor)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent)
        //verify(authorServiceImpl, times(1)).deleteByIdAuthor(author.idAuthor!!)
    }

}
