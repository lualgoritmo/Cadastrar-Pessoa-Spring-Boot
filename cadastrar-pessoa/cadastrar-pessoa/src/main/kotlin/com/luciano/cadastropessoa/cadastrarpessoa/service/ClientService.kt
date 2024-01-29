package com.luciano.cadastropessoa.cadastrarpessoa.service

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.FullClientDTO
import com.luciano.cadastropessoa.cadastrarpessoa.model.ClientUser

interface ClientService {
    fun createClient(client: ClientUser): ClientUser

    fun getByIdClient(idClient: Long): ClientUser
    fun getFullClientById(idClient: Long): FullClientDTO
    fun updateClient(idClient: Long, client: ClientUser): ClientUser

    fun deleteClient(idClient: Long)
}