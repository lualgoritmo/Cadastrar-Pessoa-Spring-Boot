package com.luciano.cadastropessoa.cadastrarpessoa.model

import jakarta.persistence.*

@Entity
@Table(name = "tb_states")
data class States(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val idStates: Long,
    val name: String,
    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "counter_id")
    val counterId: Country
)
