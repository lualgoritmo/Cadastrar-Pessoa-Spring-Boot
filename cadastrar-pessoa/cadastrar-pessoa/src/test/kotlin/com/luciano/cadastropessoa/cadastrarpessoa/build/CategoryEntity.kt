package com.luciano.cadastropessoa.cadastrarpessoa.build

import com.luciano.cadastropessoa.cadastrarpessoa.model.Category

data class CategoryEntity(
        val idCategory: Long = 1,
        val name: String = "Ação"
) {
    fun build() = Category(idCategory = idCategory, name = name)
}
