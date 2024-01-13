package com.luciano.cadastropessoa.cadastrarpessoa.controller.dto

import com.luciano.cadastropessoa.cadastrarpessoa.model.Category

data class CategoryDTO(
    val name: String
) {
    fun toEntity() = Category(0, name = this.name)

    companion object {
        fun fromEntity(category: Category): CategoryDTO = CategoryDTO(name = category.name)
    }
}