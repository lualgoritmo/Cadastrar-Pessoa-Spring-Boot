package com.luciano.cadastrarpessoa.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "tb_person")
data class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,
    val name: String,
    val email: String,
    val description: String
)