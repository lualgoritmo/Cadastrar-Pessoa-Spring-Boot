package com.luciano.cadastropessoa.cadastrarpessoa.build

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.CreateCategoryDTO
import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.UpdateAuthorDTO
import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.UpdateCategoryDTO
import com.luciano.cadastropessoa.cadastrarpessoa.model.Category

data class CategoryEntity(
        val idCategory: Long = 1L,
        val name: String = "Ação"
) {
    fun build() = Category(
            idCategory = idCategory,
            name = name
    )
    fun buildUpdate() = UpdateCategoryDTO(name = name)
    companion object {
        fun fromEntity(categoryEntity: CategoryEntity) = CreateCategoryDTO(
                name = categoryEntity.name,
        )
    }
}
