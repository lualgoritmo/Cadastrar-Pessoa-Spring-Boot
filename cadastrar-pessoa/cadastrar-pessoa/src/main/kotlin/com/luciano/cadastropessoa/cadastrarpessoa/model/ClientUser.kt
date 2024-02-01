package com.luciano.cadastropessoa.cadastrarpessoa.model

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import jakarta.validation.constraints.Email

@Entity
@Table(name = "tb_client")
data class ClientUser(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val idClient: Long? = null,
        @Email
        val email: String,
        val name: String,
        val surname: String,
        val document: String,
        val phone: String,

        @OneToMany(mappedBy = "client", cascade = [CascadeType.ALL])
        @JsonBackReference
        val addresses: List<AddressUser> = emptyList()

)
