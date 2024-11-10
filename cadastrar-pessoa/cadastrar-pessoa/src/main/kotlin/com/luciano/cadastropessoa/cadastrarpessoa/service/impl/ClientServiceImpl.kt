package com.luciano.cadastropessoa.cadastrarpessoa.service.impl

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.AddressResponseDTO
import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.FullClientDTO
import com.luciano.cadastropessoa.cadastrarpessoa.exception.AddressCardNotFoundException
import com.luciano.cadastropessoa.cadastrarpessoa.exception.ClientNotFoundException
import com.luciano.cadastropessoa.cadastrarpessoa.model.ClientUser
import com.luciano.cadastropessoa.cadastrarpessoa.repository.ClientRepository
import com.luciano.cadastropessoa.cadastrarpessoa.service.ClientService
import com.luciano.cadastropessoa.cadastrarpessoa.service.kafka.KafkaMessageProducerImpl
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrElse

@Service
class ClientServiceImpl(
    private val clientRepository: ClientRepository,
    private val kafkaMessageProducerImpl: KafkaMessageProducerImpl
) : ClientService {
    @Transactional
    override fun createClient(client: ClientUser): ClientUser {

        val savedClient = clientRepository.save(client)

        val topic = "client-create-topic"
        val message = "Cliente: criado com sucessos ${savedClient.name}, pronto para " +
                "cadastrar o endereço"

        val kafka = kafkaMessageProducerImpl.sendMessage(
            topic = topic,
            key = savedClient.idClient.toString(),
            message =message )

        return savedClient.apply {
            println("Esse aqui é do clientservice: $message, $kafka")
        }
    }
    @Transactional
    override fun getByIdClient(idClient: Long): ClientUser {
        return clientRepository.findById(idClient).getOrElse {
            throw ClientNotFoundException(idClient)
        }
    }
    override fun getFullClientById(idClient: Long): FullClientDTO {
        val client = clientRepository.findById(idClient).getOrElse {
            throw ClientNotFoundException(idClient)
        }

        val address = client.addresses.firstOrNull()?: throw AddressCardNotFoundException(idClient)
        val AddressResponseDTO = AddressResponseDTO(
                cep = address.cep,
                road = address.road,
                city = address.city,
                numberResidence = address.numberResidence,
                complement = address.complement
        )
        return FullClientDTO.fromEntity(client, address,AddressResponseDTO)
    }
    @Transactional
    override fun updateClient(idClient: Long, client: ClientUser): ClientUser {
        val existingClient: ClientUser = clientRepository.findById(idClient).orElseThrow {
            ClientNotFoundException(idClient)
        }

        val updateClient = existingClient.copy(
                email = client.email,
                name = client.name,
                surname = client.surname,
                document = client.document,
                phone = client.phone
        )
        return clientRepository.save(updateClient)
    }
    @Transactional
    override fun deleteClient(idClient: Long) {
        return clientRepository.deleteById(idClient)
    }
}
