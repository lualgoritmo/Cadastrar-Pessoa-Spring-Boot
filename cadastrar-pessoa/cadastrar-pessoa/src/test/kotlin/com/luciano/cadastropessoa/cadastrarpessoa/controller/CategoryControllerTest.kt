package com.luciano.cadastropessoa.cadastrarpessoa.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.luciano.cadastropessoa.cadastrarpessoa.build.CategoryEntity
import com.luciano.cadastropessoa.cadastrarpessoa.model.Category
import com.luciano.cadastropessoa.cadastrarpessoa.repository.CategoryRepository
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.CategoryServiceImpl
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.kotlin.given
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
class CategoryControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Mock
    private lateinit var categoryServiceImpl: CategoryServiceImpl

    @Autowired
    private lateinit var categoryRepository: CategoryRepository
    @BeforeEach
    fun setUp() {
        categoryRepository.deleteAll()
    }
    @Test
    fun `when createCategory is called, it should return Category`() {
        val category = CategoryEntity()
        val createCategory = CategoryEntity.fromEntity(category)

        given(categoryServiceImpl.createCategory(any())).willReturn(category.build())

        mockMvc.perform(MockMvcRequestBuilders.post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createCategory)))
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.equalTo(category.name)))
    }

    @Test
    fun `when getByIdCategory is called, it should return one category`() {
        val categoryEntity = CategoryEntity().build()

        categoryRepository.save(categoryEntity)

        given(categoryServiceImpl.getByIdCategory(categoryEntity.idCategory!!)).willReturn(categoryEntity)

        mockMvc.perform(MockMvcRequestBuilders.get(
                "/categories/{idCategory}", categoryEntity.idCategory)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CategoryEntity())))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(categoryEntity.name))

    }

    @Test
    fun `when updateCategoryWithId is called, it should return new category`() {
        val category = CategoryEntity().build()
        categoryRepository.save(category)

        val updateCategory = Category(
                idCategory = category.idCategory,
                name = "Novo Nome"
        )
        given(categoryServiceImpl.updateCategoryWithId(idCategory = category.idCategory!!, category = updateCategory))
                .willReturn(updateCategory)

        mockMvc.perform(MockMvcRequestBuilders.get(
                "/categories/{idCategory}", category.idCategory)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateCategory)))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(category.name))

    }

}
