package com.luciano.cadastropessoa.cadastrarpessoa.service

import com.luciano.cadastropessoa.cadastrarpessoa.model.Author

interface AuthorService {
    fun createAuthor(author: Author): Author
    fun getAllAuthor(): List<Author>
    fun getByIdAuthor(idAuthor: Long): Author
    fun updateAuthorWithId(idAuthor: Long, updatePerson: Author): Author
    fun deleteByIdAuthor(idAuthor: Long)

}