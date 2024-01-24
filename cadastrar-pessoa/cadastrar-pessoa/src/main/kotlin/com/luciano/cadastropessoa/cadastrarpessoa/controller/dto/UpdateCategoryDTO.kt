package com.luciano.cadastropessoa.cadastrarpessoa.controller.dto

import com.luciano.cadastropessoa.cadastrarpessoa.model.Category
import com.luciano.cadastropessoa.cadastrarpessoa.util.UniqueValue
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class UpdateCategoryDTO(

    @field:UniqueValue(
        message = "Esta categoria ja existe!",
        fieldName = "category",
        domainClass = Category::class
    )
    @field:NotBlank(message = "O nome não pode estar em branco")
    @field:NotNull(message = "O nome não pode ser nulo")
    val name: String
) {
    fun toEntity() = Category(name = this.name)

    companion object {
        fun fromEntity(category: Category) = UpdateCategoryDTO(name = category.name)
    }
}
