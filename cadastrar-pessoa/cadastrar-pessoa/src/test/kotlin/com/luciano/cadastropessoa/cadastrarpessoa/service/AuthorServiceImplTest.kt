package com.luciano.cadastropessoa.cadastrarpessoa.service

import com.luciano.cadastropessoa.cadastrarpessoa.model.Author
import com.luciano.cadastropessoa.cadastrarpessoa.repository.AuthorRepository
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.AuthorServiceImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
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

    @BeforeEach
    fun setUP() {
        authorServiceImpl = AuthorServiceImpl(authorRepository)
    }

    private fun authorObject() = Author(
            1L,
            "Luciano",
            "luciano@lucianos.com",
            "Autor experiente"
    )

    @Test
    fun `when create Book then return one Book`() {
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
        // Cria um autor diretamente no repositório
        val author: Author = authorObject()

        // Configura o comportamento do mock para retornar o autor criado
        `when`(authorRepository.findById(author.idAuthor!!)).thenReturn(Optional.of(author))

        // Obtém o autor pelo ID
        val retrievedAuthor = authorRepository.findById(author.idAuthor!!).orElse(null)

        // Verifica se o método findById foi chamado no mock
        verify(authorRepository).findById(author.idAuthor!!)

        // Verifica se o autor retornado é igual ao autor criado
        assertEquals(author, retrievedAuthor)
    }

    @Test
    fun `delete by id`() {
        val author: Author = authorObject()

        // Salva o autor no repositório
        authorRepository.save(author)

        // Configura o comportamento do mock para excluir o autor quando o método deleteById é chamado
        `when`(authorRepository.deleteById(author.idAuthor!!)).then { }

        // Chama o método de exclusão
        authorRepository.deleteById(author.idAuthor!!)

        // Verifica se o método deleteById foi chamado no mock
        verify(authorRepository, times(1)).deleteById(author.idAuthor!!)

        // Verifica se o autor não existe mais no repositório
        val deletedAuthor = authorRepository.findById(author.idAuthor!!).orElse(null)
        assertNull(deletedAuthor, "O autor deveria ter sido excluído.")
    }

}
