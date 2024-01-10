package com.luciano.cadastropessoa.cadastrarpessoa.controller.dto

import UniqueValue
import com.luciano.cadastropessoa.cadastrarpessoa.model.Author
import com.luciano.cadastropessoa.cadastrarpessoa.model.Category
import jakarta.validation.constraints.NotBlank

data class CreateCategoryDTO(
    @field:NotBlank(message = "O nome não pode estar em branco")

    @field:UniqueValue(
        message = "Este Email já está sendo usado!",
        fieldName = "name",
        domainClass = Author::class
    )
    val name: String
) {
    fun toEntity() = Category(idCategory = null, name = this.name)

    companion object { fun fromEntity(category: Category) =
            CreateCategoryDTO(name = category.name)
    }
}