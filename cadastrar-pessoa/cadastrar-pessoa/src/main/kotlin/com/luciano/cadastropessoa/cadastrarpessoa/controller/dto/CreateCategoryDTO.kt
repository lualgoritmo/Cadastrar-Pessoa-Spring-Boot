package com.luciano.cadastropessoa.cadastrarpessoa.controller.dto

import com.luciano.cadastropessoa.cadastrarpessoa.model.Category
import com.luciano.cadastropessoa.cadastrarpessoa.util.UniqueValue

data class CreateCategoryDTO(
        @field:UniqueValue(
                message = "Essa Categoria  já existe!",
                fieldName = "name",
                domainClass = Category::class
        )
        val name: String
) {
    fun toEntity() = Category(name = this.name)

    companion object {
        fun fromEntity(category: Category) =
                CreateCategoryDTO(name = category.name)
    }
}