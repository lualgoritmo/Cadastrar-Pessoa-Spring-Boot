package com.luciano.cadastropessoa.cadastrarpessoa.model

import jakarta.persistence.*

@Entity
@Table(name = "tb_states")
data class StateUF(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val idState: Long? = null,
        val name: String,

        @ManyToOne
        @JoinColumn(name = "country_id")
        val country: Country,
)
