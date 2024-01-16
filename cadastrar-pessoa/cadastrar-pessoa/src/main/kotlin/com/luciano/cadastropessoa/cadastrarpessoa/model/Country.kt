package com.luciano.cadastropessoa.cadastrarpessoa.model

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@Entity
@Table(name = "tb_countries")
data class Country(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idCountry: Long,
    @field:NotBlank(message = "O Pais não pode estar em branco")
    @field:NotNull(message = "O noem do Pais não pode ser nulo")
    val name: String,

    @OneToMany(mappedBy = "counterId", cascade = [CascadeType.ALL])
    @JsonBackReference
    val states: List<States> = emptyList()
)
