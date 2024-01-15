import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.CategoryDTO
import com.luciano.cadastropessoa.cadastrarpessoa.model.Book
import com.luciano.cadastropessoa.cadastrarpessoa.util.UniqueValue
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.jetbrains.annotations.NotNull

data class
BookDTO(
    @field:NotBlank(message = "O nome não pode estar em branco")
    @field:NotNull("O nome não pode ser nulo")
    val title: String,

    @field:UniqueValue(
        message = "Este ISBN já está sendo usado!",
        fieldName = "isbnBook",
        domainClass = Book::class
    )
    val isbnBook: String,

    @field:NotBlank(message = "O resumo não pode estar em branco")
    @field:NotNull("O resumo não pode ser nulo")
    @field:Size(max = 500, message = "O resumo deve ter no máximo 500 caracteres")
    val resume: String,

    val summary: String? = null,

    @field:NotNull("O preço não pode ser nulo")
    val price: Double = 20.0,

    @field:NotBlank(message = "A data não pode estar em branco")
    @field:NotNull("A data não pode ser nula")
    val datePost: String,

    val categoryDTO: CategoryDTO
) {
    companion object {
        fun fromEntity(book: Book, categoryDTO: CategoryDTO): BookDTO {
            return BookDTO(
                title = book.title,
                isbnBook = book.isbnBook,
                resume = book.resume,
                summary = book.summary,
                price = book.price,
                datePost = book.datePost,
                categoryDTO = categoryDTO
            )
        }
    }
}
