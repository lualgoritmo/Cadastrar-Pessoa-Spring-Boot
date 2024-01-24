package com.luciano.cadastropessoa.cadastrarpessoa.controller

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.CreateAddressDTO
import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.ResponseAddressDTO
import com.luciano.cadastropessoa.cadastrarpessoa.model.Address
import com.luciano.cadastropessoa.cadastrarpessoa.service.AddressService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/address")
class AddressController(private val addressService: AddressService) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createAddress(@RequestBody @Valid createAddressDTO: CreateAddressDTO): CreateAddressDTO {
        val address = addressService.createAddress(createAddressDTO)
        return CreateAddressDTO.fromEntity(address)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllAddress(): List<ResponseAddressDTO> {
        val listAddress: List<Address> = addressService.getAllAddress().also {
            if (it.isEmpty()) {
                println("Lista vazia de Address")
            }
        }

        return listAddress.map { ResponseAddressDTO.fromToEntity(it) }.toList()
    }

}
