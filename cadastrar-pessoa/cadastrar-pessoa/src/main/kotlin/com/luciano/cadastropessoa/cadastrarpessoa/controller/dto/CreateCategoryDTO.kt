package com.luciano.cadastropessoa.cadastrarpessoa.controller.dto

import com.luciano.cadastropessoa.cadastrarpessoa.model.Category
import jakarta.validation.constraints.NotBlank

data class CreateCategoryDTO(
    @field:NotBlank(message = "O nome não pode estar em branco")
    val name: String
) {
    fun toEntity() = Category(idCategory = null, name = this.name)

    companion object { fun fromEntity(category: Category) =
            CreateCategoryDTO(name = category.name)
    }
}