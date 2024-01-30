package com.luciano.cadastropessoa.cadastrarpessoa.service

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.CreateBookDTO
import com.luciano.cadastropessoa.cadastrarpessoa.model.Author
import com.luciano.cadastropessoa.cadastrarpessoa.model.Book
import com.luciano.cadastropessoa.cadastrarpessoa.model.Category
import com.luciano.cadastropessoa.cadastrarpessoa.repository.BookRepository
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.AuthorServiceImpl
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.BookServiceImpl
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.CategoryServiceImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BookServiceImplTest {
    @InjectMocks
    private lateinit var bookServiceImpl: BookServiceImpl

    @Mock
    private lateinit var bookRepository: BookRepository

    @Mock
    private lateinit var authorServiceImpl: AuthorServiceImpl

    @Mock
    private lateinit var categoryServiceImpl: CategoryServiceImpl

    private fun authorObject() = Author(idAuthor = 1L, name = "Luciano", email = "luciano@lucianos.com", description = "Autor experiente")
    private fun categoryObject() = Category(idCategory = 1, name = "Ação")

    private fun bookObject() = CreateBookDTO(
            title = "Primeiro Livro",
            isbnBook = "123456",
            resume = "Um bom livro",
            summary = null,
            price = 20.0,
            datePost = "10/10/2020",
            authorId = authorObject().idAuthor,
            categoryId = categoryObject().idCategory
    )

    @Test
    fun `When createBook is called, it must save a book`() {
        val book = bookObject()
        val author = authorObject()
        val category = categoryObject()

        `when`(authorServiceImpl.getByIdAuthor(author.idAuthor!!)).thenReturn(author)
        `when`(categoryServiceImpl.getByIdCategory(category.idCategory!!)).thenReturn(category)
        `when`(bookRepository.save(any())).thenReturn(book.toEntity(author, category))

        val savedBook = bookServiceImpl.createBook(bookObject())

        assertThat(savedBook).isInstanceOf(Book::class.java)
        assertThat(savedBook).isNotNull
        assertThat(savedBook.authorId.idAuthor).isEqualTo(1)

        verify(authorServiceImpl, times(1)).getByIdAuthor(author.idAuthor!!)
        verify(categoryServiceImpl, times(1)).getByIdCategory(category.idCategory!!)
        verify(bookRepository, times(1)).save(any())
    }

    @Test
    fun `when getAllBooks is called, it should return a list of books`() {
        `when`(bookRepository.findAll()).thenReturn(returnListBooks())
        val newListBook = bookRepository.findAll()

        assertThat(newListBook).containsExactlyInAnyOrderElementsOf(returnListBooks())
        assertThat(newListBook).isNotNull.isNotEmpty

        verify(bookRepository, times(1)).findAll()
    }

    private fun returnListBooks(): List<Book> = listOf(
            Book(
                    idBook = 1,
                    title = "Primeiro Livro",
                    isbnBook = "123456",
                    resume = "Um bom livro",
                    summary = null,
                    price = 20.0,
                    datePost = "10/10/2020",
                    authorId = authorObject(),
                    categoryId = categoryObject()
            ),
            Book(
                    idBook = 2,
                    title = "Segundo Livro",
                    isbnBook = "123456",
                    resume = "Um bom livro",
                    summary = null,
                    price = 20.0,
                    datePost = "10/10/2020",
                    authorId = authorObject(),
                    categoryId = categoryObject()
            ),
            Book(
                    idBook = 3,
                    title = "Terceiro Livro",
                    isbnBook = "123456",
                    resume = "Um bom livro",
                    summary = null,
                    price = 20.0,
                    datePost = "10/10/2020",
                    authorId = authorObject(),
                    categoryId = categoryObject()
            )
    )

}
