package com.luciano.cadastropessoa.cadastrarpessoa.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.luciano.cadastropessoa.cadastrarpessoa.build.AuthorEntity
import com.luciano.cadastropessoa.cadastrarpessoa.build.BookEntity
import com.luciano.cadastropessoa.cadastrarpessoa.build.CategoryEntity
import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.CreateBookDTO
import com.luciano.cadastropessoa.cadastrarpessoa.repository.AuthorRepository
import com.luciano.cadastropessoa.cadastrarpessoa.repository.BookRepository
import com.luciano.cadastropessoa.cadastrarpessoa.repository.CategoryRepository
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.BookServiceImpl
import org.hamcrest.Matchers
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

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension::class)
class BookControllerTest {
    @MockBean
    private lateinit var bookServiceImpl: BookServiceImpl

    @MockBean
    private lateinit var bookRepository: BookRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `when createBook is called, it should return book`() {
        val author = AuthorEntity().build()
        val category = CategoryEntity().build()

        val createBook = CreateBookDTO(
                title = "Livro Build",
                isbnBook = "123456",
                resume = "Resumo Build",
                summary = null,
                price = 20.0,
                datePost = "10/10/2022",
                authorId = author.idAuthor,
                categoryId = category.idCategory
        )

        given(bookServiceImpl.createBook(any())).willReturn(createBook.toEntity(author, category))

        mockMvc.perform(MockMvcRequestBuilders.post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createBook)))
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.equalTo(createBook.title)))
    }

    @Test
    fun `when getBookWithId is called, it should return one book`() {
        val author = AuthorEntity().build()
        val category = CategoryEntity().build()

        val book = BookEntity(authorId = author, categoryId = category).build()
        bookRepository.save(book)

        given(bookServiceImpl.getByIdBook(book.idBook!!)).willReturn(book)

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/{idBook}", book.idBook)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.equalTo(book.title)))

        verify(bookServiceImpl, times(1)).getByIdBook(book.idBook!!)
    }

    @Test
    fun`when deleteBookWithId is called, it should return`() {
        val author = AuthorEntity().build()
        val category = CategoryEntity().build()

        val book = BookEntity(authorId = author, categoryId = category).build()
        bookRepository.save(book)

        given(bookServiceImpl.deleteByIdBook(any())).will {  }

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/{idBook}/delete", book.idBook)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent)

        verify(bookServiceImpl, times(1)).deleteByIdBook(book.idBook!!)
    }
}
