package com.luciano.cadastropessoa.cadastrarpessoa.service

import com.luciano.cadastropessoa.cadastrarpessoa.model.Category

interface CategoryService {
    fun createCategory(category: Category): Category
    fun getAllCategorys(): List<Category>
    fun getByIdCategory(idCategory: Long): Category
    fun deleteByIdCategory(idCategory: Long)
}