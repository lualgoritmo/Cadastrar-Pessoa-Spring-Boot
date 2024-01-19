package com.luciano.cadastropessoa.cadastrarpessoa.model
import jakarta.persistence.*

@Entity
@Table(name = "tb_payment")
data class Client(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idClient: Long,
    val email: String,
    val name: String,
    val surname: String,
    val document: String,

    @ManyToOne(cascade = [CascadeType.ALL])
    val address: Address
)
