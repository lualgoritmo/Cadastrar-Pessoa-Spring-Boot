package com.luciano.cadastropessoa.cadastrarpessoa.service

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.CreateAddressDTO
import com.luciano.cadastropessoa.cadastrarpessoa.model.AddressUser

interface AddressService {
    fun createAddress(address: CreateAddressDTO): AddressUser
    fun getAllAddress(): List<AddressUser>
    fun getByIdAddress(idAddress: Long): AddressUser
    fun updateAddress(idAddress: Long, address: AddressUser): AddressUser
    fun deleteWithIdAddress(idAddress: Long)
}