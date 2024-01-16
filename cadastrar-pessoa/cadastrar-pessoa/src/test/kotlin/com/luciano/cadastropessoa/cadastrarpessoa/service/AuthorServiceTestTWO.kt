package com.luciano.cadastropessoa.cadastrarpessoa.service

import com.luciano.cadastropessoa.cadastrarpessoa.model.Author
import com.luciano.cadastropessoa.cadastrarpessoa.repository.AuthorRepository
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.AuthorServiceImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AusthorServiceTestTWO {
    private lateinit var authorServiceImpl: AuthorServiceImpl
    private lateinit var authorRepository: AuthorRepository
    @BeforeEach
    fun setUp() {
        authorRepository = mock(AuthorRepository::class.java)
        authorServiceImpl = AuthorServiceImpl(authorRepository)
    }

    @Test
    fun shouldReturnCreatedAuthorWhenCreateAuthor() {
        val authorToSave: Author = Author(
            1L,
            "Luciano",
            "luciano@lucianos.com",
            "Autor experiente"
        )

        val savedAuthor: Author = Author(
            1L,
            "Luciano",
            "luciano@lucianos.com",
            "Autor experiente"
        )

        `when`(authorRepository.save(any(Author::class.java))).thenReturn(savedAuthor)

        val createdAuthor = authorServiceImpl.createAuthor(authorToSave)

        assertEquals(savedAuthor, createdAuthor)
        assertEquals(savedAuthor.idAuthor, createdAuthor.idAuthor)
        assertEquals(savedAuthor.name, createdAuthor.name)
        assertEquals(savedAuthor.email, createdAuthor.email)

        verify(authorRepository).save(authorToSave)
    }
    @Test
    fun shouldReturnAllAuthors() {
        val author: Author = Author(
            1L,
            "Luciano",
            "luciano@lucianos.com",
            "Autor experiente"
        )
        val authorTwo: Author = Author(
            2L,
            "Maria",
            "maria@lucianos.com",
            "Autora experiente"
        )
        val authorTree = Author(
            3L,
            "Luciano",
            "luciano@lucianos.com",
            "Autor experiente"
        )

        `when`(authorRepository.findAll()).thenReturn(listOf(author, authorTwo, authorTree))

        val retrievedAuthors: List<Author> = authorServiceImpl.getAllAuthor()

        assertEquals(3, retrievedAuthors.size)
        assertThat(retrievedAuthors.size).isEqualTo(3)
        assertThat(retrievedAuthors[0].name).isEqualTo("Luciano")
        assertThat(retrievedAuthors[0].email).isEqualTo(author.email)
        assertThat(retrievedAuthors[0].description).isEqualTo(author.description)
        assertEquals(author, retrievedAuthors[0])
        assertEquals(authorTwo, retrievedAuthors[1])
        assertEquals(authorTree, retrievedAuthors[2])
        verify(authorRepository, times(1)).findAll()
    }
}
