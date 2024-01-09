package com.luciano.cadastropessoa.cadastrarpessoa.repository

import com.luciano.cadastropessoa.cadastrarpessoa.model.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : JpaRepository<Category, Long>