package com.luciano.cadastropessoa.cadastrarpessoa.service

import com.luciano.cadastropessoa.cadastrarpessoa.model.Author
import com.luciano.cadastropessoa.cadastrarpessoa.repository.AuthorRepository
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.AuthorServiceImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
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

        `when`(authorRepository.save(author)).thenReturn(expectedAuthor)

        val createAuthor = authorServiceImpl.createAuthor(author)

        assertEquals(createAuthor, expectedAuthor)
        assertEquals(createAuthor.idAuthor, author.idAuthor)
        assertEquals(createAuthor.name, "Luciano")
        assertEquals(createAuthor.email, author.email)
        verify(authorRepository, times(1)).save(author)
    }

//    @Test
//    fun `when create Author then return created Author`() {
//
//        val authorToSave: Author = Author(
//            1L,
//            "Luciano",
//            "luciano@lucianos.com",
//            "Autor experiente"
//        )
//
//        val savedAuthor: Author = Author(
//            1L,
//            "Luciano",
//            "luciano@lucianos.com",
//            "Autor experiente"
//        )
//
//        val captor = ArgumentCaptor.forClass(Author::class.java)
//
//        Mockito.`when`(authorRepository.save(captor.capture())).thenReturn(savedAuthor)
//
//        val createdAuthor = authorServiceImpl.createAuthor(authorToSave)
//
//        assertEquals(savedAuthor, createdAuthor)
//        assertEquals(savedAuthor.idAuthor, createdAuthor.idAuthor)
//        assertEquals(savedAuthor.name, createdAuthor.name)
//        assertEquals(savedAuthor.email, createdAuthor.email)
//
//        verify(authorRepository).save(authorToSave)
//
//        assertEquals(authorToSave, captor.value)
//    }

//    @Test
//    fun `When getallAuthor return list authores`() {
//        val author: Author = Author(
//            1L,
//            "Luciano",
//            "luciano@lucianos.com",
//            "Autor experiente"
//        )
//        val authorTwo: Author = Author(
//            2L,
//            "Maria",
//            "maria@lucianos.com",
//            "Autora experiente"
//        )
//        val authorTree = Author(
//            3L,
//            "Luciano",
//            "luciano@lucianos.com",
//            "Autor experiente"
//        )
////        authorServiceImpl.createAuthor(author)
////        authorServiceImpl.createAuthor(authorTwo)
////        authorServiceImpl.createAuthor(authorTree)
//
//        `when`(authorRepository.findAll()).thenReturn(listOf(author, authorTwo, authorTree))
//        val retrievedAuthors: List<Author> = authorServiceImpl.getAllAuthor()
//
//        assertEquals(3, retrievedAuthors.size)
//        assertEquals(author, retrievedAuthors[0])
//        assertEquals(authorTwo, retrievedAuthors[1])
//        assertEquals(authorTree, retrievedAuthors[2])
//        verify(authorRepository, times(1)).findAll()
//
//    }

    @Test
    fun `When getallAuthor return list authores`() {
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
        assertThat(retrievedAuthors[0]).isEqualTo(author)
        assertThat(retrievedAuthors[0]).isEqualTo(author)
        assertThat(retrievedAuthors[1]).isEqualTo(authorTwo)
        assertThat(retrievedAuthors[2]).isEqualTo(authorTree)
//        assertEquals(authorTwo, retrievedAuthors[1])
//        assertEquals(authorTree, retrievedAuthors[2])

        verify(authorRepository, times(1)).findAll()
    }

}
