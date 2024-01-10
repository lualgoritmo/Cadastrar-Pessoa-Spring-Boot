package com.luciano.cadastropessoa.cadastrarpessoa.service.impl

import com.luciano.cadastropessoa.cadastrarpessoa.exception.AuthorNotFoundException
import com.luciano.cadastropessoa.cadastrarpessoa.exception.CategoryNotFoundException
import com.luciano.cadastropessoa.cadastrarpessoa.model.Category
import com.luciano.cadastropessoa.cadastrarpessoa.repository.CategoryRepository
import com.luciano.cadastropessoa.cadastrarpessoa.service.CategoryService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CategoryServiceImpl(private val categoryRepository: CategoryRepository) : CategoryService {
    @Transactional
    override fun createCategory(category: Category): Category = categoryRepository.save(category)
    @Transactional
    override fun getAllCategorys(): List<Category> = categoryRepository.findAll()
    @Transactional
    override fun getByIdCategory(idCategory: Long): Category = categoryRepository.findById(idCategory).orElseThrow {
        throw CategoryNotFoundException(idCategory)
    }

    @Transactional
    override fun deleteByIdCategory(idCategory: Long) {
        try {
            categoryRepository.deleteById(idCategory)
        } catch (ex: Exception) {
            throw CategoryNotFoundException(idCategory)
        }
    }
}
