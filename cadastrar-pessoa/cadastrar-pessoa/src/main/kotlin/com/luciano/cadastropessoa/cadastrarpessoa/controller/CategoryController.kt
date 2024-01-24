package com.luciano.cadastropessoa.cadastrarpessoa.controller

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.CreateCategoryDTO
import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.UpdateCategoryDTO
import com.luciano.cadastropessoa.cadastrarpessoa.exception.CategoryNotFoundException
import com.luciano.cadastropessoa.cadastrarpessoa.model.Category
import com.luciano.cadastropessoa.cadastrarpessoa.service.CategoryService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categorys")
class CategoryController(private val categoryService: CategoryService) {
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun createCategory(@RequestBody @Valid categoryDTO: CreateCategoryDTO): CreateCategoryDTO {
        val category: Category = categoryService.createCategory(categoryDTO.toEntity())
        return CreateCategoryDTO.fromEntity(category)
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllCategorys(): List<CreateCategoryDTO> {
        val listCategorys: List<Category> = categoryService.getAllCategorys().also {
            if (!it.isEmpty()) {
                println("Lista de categorias vazia no Controller")
            }
        }

        return listCategorys.map { CreateCategoryDTO.fromEntity(it) }.toList()
    }
    @PutMapping("/{idCategory}")
    @ResponseStatus(HttpStatus.OK)
    fun updateCategoryWithId(
        @PathVariable idCategory: Long,
        @RequestBody @Valid
        updateCategoryDTO: UpdateCategoryDTO
    ): UpdateCategoryDTO {
        val category: Category = categoryService.updateCategoryWithId(idCategory, updateCategoryDTO.toEntity())
        return UpdateCategoryDTO.fromEntity(category)
    }
    @GetMapping("/{idCategory}")
    @ResponseStatus(HttpStatus.OK)
    fun getByIdCategory(@PathVariable idCategory: Long): CreateCategoryDTO {
        try {
            val category: Category = categoryService.getByIdCategory(idCategory)
            return CreateCategoryDTO.fromEntity(category)
        } catch (e: CategoryNotFoundException) {
            throw e
        }

    }
}
