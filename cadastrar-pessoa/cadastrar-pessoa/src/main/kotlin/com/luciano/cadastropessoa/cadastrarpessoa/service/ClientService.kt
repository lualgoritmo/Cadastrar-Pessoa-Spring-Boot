package com.luciano.cadastropessoa.cadastrarpessoa.service

import com.luciano.cadastropessoa.cadastrarpessoa.model.Client

interface ClientService {
    fun createClient(client: Client): Client

    fun getByIdClient(idClient: Long): Client

    fun updateClient(idClient: Long, client: Client): Client

    fun deleteClient(idClient: Long)
}