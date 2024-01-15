package com.luciano.cadastropessoa.cadastrarpessoa.service

import com.luciano.cadastropessoa.cadastrarpessoa.model.Author
import com.luciano.cadastropessoa.cadastrarpessoa.repository.AuthorRepository
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.AuthorServiceImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AuthorServiceImplTest {
    @InjectMocks
    private lateinit var authorServiceImpl: AuthorServiceImpl


    @Mock
    private lateinit var authorRepository: AuthorRepository

    @BeforeEach
    fun setUP() {
        authorServiceImpl = AuthorServiceImpl(authorRepository)
    }

    @Test
    fun `when create Book then return one Book`() {
        val author: Author = Author(
            1L,
            "Luciano",
            "luciano@lucianos.com",
            "Autor experiente"
        )

        val expectedAuthor = author

        Mockito.`when`(authorRepository.save(author)).thenReturn(expectedAuthor)

        val createAuthor = authorServiceImpl.createAuthor(author)

        assertEquals(createAuthor, expectedAuthor)
        assertEquals(createAuthor.idAuthor, author.idAuthor)
        assertEquals(createAuthor.name, "Luciano")
        assertEquals(createAuthor.email, author.email)
        verify(authorRepository, times(1)).save(author)
    }

}
