package com.luciano.cadastropessoa.cadastrarpessoa.service.impl

import com.luciano.cadastropessoa.cadastrarpessoa.exception.AuthorNotFoundException
import com.luciano.cadastropessoa.cadastrarpessoa.model.Author
import com.luciano.cadastropessoa.cadastrarpessoa.repository.AuthorRepository
import com.luciano.cadastropessoa.cadastrarpessoa.service.AuthorService
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class AuthorServiceImpl(
        private val authorRepository: AuthorRepository,
) : AuthorService {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @Transactional
    override fun createAuthor(author: Author): Author =
            authorRepository.save(author)

    @Transactional
    override fun getAllAuthor(): List<Author> {
        val list = authorRepository.findAll()
        if (list.isNotEmpty()) {
            logger.info("ESTÁ LISTA NÃO ESTÁ VAZIA")
            logger.error("Não tem lista de autores")
        }
        return list
    }

    @Transactional
    override fun getByIdAuthor(idAuthor: Long): Author = authorRepository.findById(idAuthor)
            .orElseThrow { AuthorNotFoundException(idAuthor) }


    //    @Transactional
//    override fun updateAuthorWithId(idAuthor: Long, updateAuthor: Author): Author {
//        val existingAuthor: Author = authorRepository.findById(idAuthor)
//            .orElseThrow { AuthorNotFoundException(idAuthor) }
//
//        val updatedAuthor = existingAuthor.copy(
//            name = updateAuthor.email,
//            email = updateAuthor.email,
//            description = updateAuthor.description
//        )
//
//        return authorRepository.save(updatedAuthor)
//    }
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
