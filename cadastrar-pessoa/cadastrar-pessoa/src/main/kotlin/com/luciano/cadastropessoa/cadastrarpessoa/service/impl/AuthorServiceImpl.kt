package com.luciano.cadastropessoa.cadastrarpessoa.service.impl

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.AuthorDTO
import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.BookDTO
import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.CategoryDTO
import com.luciano.cadastropessoa.cadastrarpessoa.exception.AuthorNotFoundException
import com.luciano.cadastropessoa.cadastrarpessoa.model.Author
import com.luciano.cadastropessoa.cadastrarpessoa.model.Category
import com.luciano.cadastropessoa.cadastrarpessoa.repository.AuthorRepository
import com.luciano.cadastropessoa.cadastrarpessoa.repository.CategoryRepository
import com.luciano.cadastropessoa.cadastrarpessoa.service.AuthorService
import jakarta.transaction.Transactional
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class AuthorServiceImpl(
    private val authorRepository: AuthorRepository,
    private val categoryRepository: CategoryRepository
) : AuthorService {
    @Transactional
    override fun createAuthor(author: Author): Author = authorRepository.save(author)
    override fun getAllAuthor(): List<Author> = authorRepository.findAll()

    @Transactional
    override fun getByIdAuthor(idAuthor: Long): Author = authorRepository.findById(idAuthor)
        .orElseThrow { AuthorNotFoundException(idAuthor) }

    @Transactional
    override fun updateAuthorWithId(idAuthor: Long, updateAuthor: Author): Author {
        val existingAuthor: Author = authorRepository.findById(idAuthor)
            .orElseThrow { AuthorNotFoundException(idAuthor) }

        val updatedAuthor = existingAuthor.copy(
            name = updateAuthor.name,
            email = updateAuthor.email,
            description = updateAuthor.description
        )

        return authorRepository.save(updatedAuthor)
    }

    @Transactional
    override fun deleteByIdAuthor(idAuthor: Long) {
        try {
            authorRepository.deleteById(idAuthor)
        } catch (ex: EmptyResultDataAccessException) {
            throw AuthorNotFoundException(idAuthor)
        }
    }

}
