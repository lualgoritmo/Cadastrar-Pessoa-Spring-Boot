package com.luciano.cadastropessoa.cadastrarpessoa.service

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.CreateAddressDTO
import com.luciano.cadastropessoa.cadastrarpessoa.model.Address

interface AddressService {
    fun createAddress(address: CreateAddressDTO): Address
    fun getAllAddress(): List<Address>
    fun getByIdAddress(idAddress: Long): Address
    fun updateAddress(idAddress: Long, address: Address): Address
    fun deleteWithIdAddress(idAddress: Long)
}