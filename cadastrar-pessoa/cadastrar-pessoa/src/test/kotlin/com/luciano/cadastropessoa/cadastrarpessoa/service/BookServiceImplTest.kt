package com.luciano.cadastropessoa.cadastrarpessoa.service

import com.luciano.cadastropessoa.cadastrarpessoa.build.AuthorEntity
import com.luciano.cadastropessoa.cadastrarpessoa.build.BookEntity
import com.luciano.cadastropessoa.cadastrarpessoa.build.CategoryEntity
import com.luciano.cadastropessoa.cadastrarpessoa.model.Book
import com.luciano.cadastropessoa.cadastrarpessoa.repository.BookRepository
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.AuthorServiceImpl
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.BookServiceImpl
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.CategoryServiceImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.InvocationInterceptor.Invocation
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

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

    @Test
    fun `When createBook is called, it must save a book`() {
        val author = AuthorEntity().build()
        val category = CategoryEntity().build()
        val bookEntity = BookEntity(authorId = author, categoryId = category)

        `when`(authorServiceImpl.getByIdAuthor(author.idAuthor!!)).thenReturn(author)
        `when`(categoryServiceImpl.getByIdCategory(category.idCategory!!)).thenReturn(category)
        `when`(bookRepository.save(any())).thenReturn(bookEntity.build())

        val savedBook = bookServiceImpl.createBook(BookEntity.fromEntity(bookEntity = bookEntity))

        assertThat(savedBook).isInstanceOf(Book::class.java)
        assertThat(savedBook).isNotNull
        assertThat(savedBook.authorId.idAuthor).isEqualTo(bookEntity.idBook)

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

    @Test
    fun `when getByIdBook is called, it should return one book`() {
        val book = BookEntity(authorId = AuthorEntity().build(), categoryId = CategoryEntity().build())
        `when`(bookRepository.findById(any())).thenReturn(Optional.of(book.build()))
        val bookId = bookServiceImpl.getByIdBook(book.idBook!!)

        assertThat(bookId).isInstanceOf(Book::class.java)
        assertThat(bookId).isEqualTo(book.build())
        assertThat(bookId.idBook).isEqualTo(book.idBook)

        verify(bookRepository, times(1)).findById(1)
    }

    @Test
    fun `when updateBook is called, it should return new book`() {
        val author = AuthorEntity().build()
        val category = CategoryEntity().build()
        val existingBook = BookEntity(authorId = author, categoryId = category).build()

        val updateBook = Book(
                idBook = existingBook.idBook,
                title = "Novo Livro Build",
                isbnBook = "666666666",
                resume = "Novo Resumo Build",
                summary = null,
                price = 50.0,
                datePost = "01/01/2018",
                authorId = author,
                categoryId = category
        )

        `when`(bookRepository.findById(existingBook.idBook!!)).thenReturn(Optional.of(existingBook))
        `when`(bookRepository.save(any())).thenAnswer { invocation ->
            val savedBook = invocation.arguments[0] as Book
            savedBook
        }

        val newBook = bookServiceImpl.updateWithBookId(existingBook.idBook!!, updateBook)
        assertThat(newBook).isInstanceOf(Book::class.java)
        assertThat(newBook).isEqualTo(updateBook)

        verify(bookRepository, times(1)).findById(existingBook.idBook!!)
        verify(bookRepository, times(1)).save(any())
    }


    @Test
    fun `when deleteByIdBook is called, it should return any`() {
        val author = AuthorEntity().build()
        val category = CategoryEntity().build()
        val bookEntity = BookEntity(authorId = author, categoryId = category)

        bookRepository.save(bookEntity.build())

        `when`(bookRepository.deleteById(bookEntity.idBook)).then { }

        bookServiceImpl.deleteByIdBook(bookEntity.idBook)

        val deletedBook = bookRepository.findById(bookEntity.idBook).orElse(null)

        assertNull(deletedBook, "O book não deveria existir")

        verify(bookRepository, times(1)).deleteById(bookEntity.idBook)
    }

    private fun returnListBooks(): List<Book> {
        val author = AuthorEntity()
        val category = CategoryEntity()
        return listOf(
                Book(
                        idBook = 1,
                        title = "Primeiro Livro",
                        isbnBook = "123456",
                        resume = "Um bom livro",
                        summary = null,
                        price = 20.0,
                        datePost = "10/10/2020",
                        authorId = author.build(),
                        categoryId = category.build()
                ),
                Book(
                        idBook = 2,
                        title = "Segundo Livro",
                        isbnBook = "123456",
                        resume = "Um bom livro",
                        summary = null,
                        price = 20.0,
                        datePost = "10/10/2020",
                        authorId = author.build(),
                        categoryId = category.build()
                ),
                Book(
                        idBook = 3,
                        title = "Terceiro Livro",
                        isbnBook = "123456",
                        resume = "Um bom livro",
                        summary = null,
                        price = 20.0,
                        datePost = "10/10/2020",
                        authorId = author.build(),
                        categoryId = category.build()
                )
        )
    }
}


