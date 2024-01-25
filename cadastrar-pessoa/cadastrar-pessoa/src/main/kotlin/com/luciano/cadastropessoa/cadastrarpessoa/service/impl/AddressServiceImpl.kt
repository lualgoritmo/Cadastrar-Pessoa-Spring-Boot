package com.luciano.cadastropessoa.cadastrarpessoa.service.impl

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.CreateAddressDTO
import com.luciano.cadastropessoa.cadastrarpessoa.exception.AddressCardNotFoundException
import com.luciano.cadastropessoa.cadastrarpessoa.model.Address
import com.luciano.cadastropessoa.cadastrarpessoa.repository.AddressRepository
import com.luciano.cadastropessoa.cadastrarpessoa.service.AddressService
import com.luciano.cadastropessoa.cadastrarpessoa.service.ClientService
import com.luciano.cadastropessoa.cadastrarpessoa.service.StateService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrElse

@Service
class AddressServiceImpl(
        private val addressRepository: AddressRepository,
        private val stateService: StateService,
        private val clientService: ClientService
) : AddressService {
    @Transactional
    override fun createAddress(createAddressDTO: CreateAddressDTO): Address {
        val client = clientService.getByIdClient(createAddressDTO.clientId!!)
        val state = stateService.getStateById(createAddressDTO.stateId!!)

        val address: Address = createAddressDTO.toEntity(stateUF = state, client = client)
        return addressRepository.save(address)
    }

    override fun getAllAddress(): List<Address> = addressRepository.findAll()
    override fun getByIdAddress(idAddress: Long): Address {
        return addressRepository.findById(idAddress).getOrElse {
            throw AddressCardNotFoundException(idAddress)
        }
    }

    override fun updateAddress(idAddress: Long, address: Address): Address {
        TODO("Not yet implemented")
    }

    override fun deleteWithIdAddress(idAddress: Long) {
        TODO("Not yet implemented")
    }
}
