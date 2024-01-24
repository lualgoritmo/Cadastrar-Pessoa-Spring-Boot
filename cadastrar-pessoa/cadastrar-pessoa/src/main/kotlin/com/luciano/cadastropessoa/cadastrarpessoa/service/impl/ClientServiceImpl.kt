package com.luciano.cadastropessoa.cadastrarpessoa.service.impl

import com.luciano.cadastropessoa.cadastrarpessoa.exception.ClientNotFoundException
import com.luciano.cadastropessoa.cadastrarpessoa.model.Client
import com.luciano.cadastropessoa.cadastrarpessoa.repository.ClientRepository
import com.luciano.cadastropessoa.cadastrarpessoa.service.ClientService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrElse

@Service
class ClientServiceImpl(private val clientRepository: ClientRepository) : ClientService {

    @Transactional
    override fun createClient(client: Client): Client {
        return clientRepository.save(client)

    }

    @Transactional
    override fun getByIdClient(idClient: Long): Client {
        return clientRepository.findById(idClient).getOrElse {
            throw ClientNotFoundException(idClient)
        }
    }

    @Transactional
    override fun updateClient(idClient: Long, client: Client): Client {
        val existingClient: Client = clientRepository.findById(idClient).orElseThrow {
            ClientNotFoundException(idClient)
        }

        val updateClient = existingClient.copy(
                email = client.email,
                name = client.name,
                surname = client.surname,
                document = client.document
        )
        return clientRepository.save(updateClient)
    }

    @Transactional
    override fun deleteClient(idClient: Long) {
        return clientRepository.deleteById(idClient)
    }
}
