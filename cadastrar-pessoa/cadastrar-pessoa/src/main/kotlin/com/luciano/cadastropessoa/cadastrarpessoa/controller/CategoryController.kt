package com.luciano.cadastropessoa.cadastrarpessoa.controller

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.CreateCategoryDTO
import com.luciano.cadastropessoa.cadastrarpessoa.model.Category
import com.luciano.cadastropessoa.cadastrarpessoa.service.AuthorService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categorys")
class CategoryController(private val authorService: AuthorService) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCategory(@RequestBody @Valid categoryDTO: CreateCategoryDTO): CreateCategoryDTO {
        val category: Category = authorService.createCategory(categoryDTO.toEntity())
        return CreateCategoryDTO.fromEntity(category)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllCategorys(): List<CreateCategoryDTO> {

        val listCategorys: List<Category> = authorService.getAllCategorys()

        if(listCategorys.isEmpty()) {
            println("Lista de categorias vazia no Controller")
        }

        return listCategorys.map { CreateCategoryDTO.fromEntity(it) }.toList()
    }
}