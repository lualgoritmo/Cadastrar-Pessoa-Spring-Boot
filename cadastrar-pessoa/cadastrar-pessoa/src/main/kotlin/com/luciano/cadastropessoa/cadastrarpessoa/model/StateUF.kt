package com.luciano.cadastropessoa.cadastrarpessoa.model
import jakarta.persistence.*

@Entity
@Table(name = "tb_states")
data class StateUF(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idState: Long,
    val name: String,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "country_id")
    val country: Country,
)
