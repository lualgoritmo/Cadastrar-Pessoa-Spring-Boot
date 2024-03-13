package com.luciano.cadastropessoa.cadastrarpessoa.service

import com.luciano.cadastropessoa.cadastrarpessoa.model.Author
import org.springframework.data.domain.Page

interface AuthorService {
    fun createAuthor(author: Author): Author
    fun getAllAuthor(): List<Author>
    fun getPageAuthor(page: Int, size: Int): Page<Author>
    fun getByIdAuthor(idAuthor: Long): Author
    fun updateAuthorWithId(idAuthor: Long, updateAuthor: Author): Author
    fun deleteByIdAuthor(idAuthor: Long)

}