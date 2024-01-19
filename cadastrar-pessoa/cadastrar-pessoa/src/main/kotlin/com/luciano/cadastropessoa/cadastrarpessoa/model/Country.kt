package com.luciano.cadastropessoa.cadastrarpessoa.model
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull

@Entity
@Table(name = "tb_countries")
data class Country(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idCountry: Long,
    @field:NotBlank(message = "O Pais não pode estar em branco")
    @field:NotNull("O nome do Pais não pode ser nulo")
    val name: String,

    @OneToMany(mappedBy = "country", cascade = [CascadeType.ALL])
    var states: List<StateUF> = emptyList()
)
