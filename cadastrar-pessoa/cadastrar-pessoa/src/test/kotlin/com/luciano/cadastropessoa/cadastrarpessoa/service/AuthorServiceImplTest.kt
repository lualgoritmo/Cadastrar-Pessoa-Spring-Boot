package com.luciano.cadastropessoa.cadastrarpessoa.service

import com.luciano.cadastropessoa.cadastrarpessoa.exception.AuthorNotFoundException
import com.luciano.cadastropessoa.cadastrarpessoa.model.Author
import com.luciano.cadastropessoa.cadastrarpessoa.repository.AuthorRepository
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.AuthorServiceImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class AuthorServiceImplTest {
    @InjectMocks
    private lateinit var authorServiceImpl: AuthorServiceImpl

    @Mock
    private lateinit var authorRepository: AuthorRepository

    private fun authorObject() = Author(idAuthor = 1L, name = "Luciano", email = "luciano@lucianos.com", description = "Autor experiente")
    @Test
    fun `when create author then return one author`() {
        val author: Author = authorObject()

        val expectedAuthor = author

        `when`(authorRepository.save(author)).thenReturn(expectedAuthor)

        val createAuthor = authorServiceImpl.createAuthor(author)

        assertEquals(createAuthor, expectedAuthor)
        assertEquals(createAuthor.idAuthor, author.idAuthor)
        assertEquals(createAuthor.name, "Luciano")
        assertEquals(createAuthor.email, author.email)
        verify(authorRepository, times(1)).save(author)
    }
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
    @Test
    fun `when get author with id then return one author`() {
        val author: Author = authorObject()

        `when`(authorRepository.findById(author.idAuthor!!)).thenReturn(Optional.of(author))
        val authorReturn = authorServiceImpl.getByIdAuthor(author.idAuthor!!)

        assertThat(author).isEqualTo(authorReturn)
        verify(authorRepository, times(1)).findById(author.idAuthor!!)
    }
    @Test
    fun `when get non-existing author by id then throw AuthorNotFoundException`() {
        `when`(authorRepository.findById(1L)).thenReturn(Optional.empty())

        assertThrows<AuthorNotFoundException> { authorServiceImpl.getByIdAuthor(1L) }
    }
    @Test
    fun `when executing the updateAuthorWithId method, it must return a new author`() {
        val existingAuthor = authorObject()

        val updateAuthor = Author(
                idAuthor = 1,
                name = "Novo Nome",
                email = "novo@email.com",
                description = "nova descrição"
        )
        `when`(authorRepository.findById(existingAuthor.idAuthor!!)).thenReturn(Optional.of(existingAuthor))
        `when`(authorRepository.save(updateAuthor)).thenReturn(updateAuthor)

        val newAuthor = authorServiceImpl.updateAuthorWithId(existingAuthor.idAuthor!!, updateAuthor)

        assertThat(newAuthor).isNotNull
        assertThat(newAuthor).isEqualTo(updateAuthor)
        assertThat(newAuthor.idAuthor).isEqualTo(updateAuthor.idAuthor)
        assertThat(newAuthor.name).isEqualTo(updateAuthor.name)
        assertThat(newAuthor.email).isEqualTo(updateAuthor.email)

        verify(authorRepository, times(1)).findById(existingAuthor.idAuthor!!)
        verify(authorRepository, times(1)).save(updateAuthor)
    }
    @Test
    fun `when executing deleteWithIdState, it returns nothing`() {
        val author: Author = authorObject()

        authorRepository.save(author)

        `when`(authorRepository.deleteById(author.idAuthor!!)).then { }
        authorServiceImpl.deleteByIdAuthor(author.idAuthor!!)

        val deletedAuthor = authorRepository.findById(author.idAuthor!!).orElse(null)
        assertNull(deletedAuthor, "O autor deveria ter sido excluído.")

        verify(authorRepository, times(1)).deleteById(author.idAuthor!!)
    }

}
