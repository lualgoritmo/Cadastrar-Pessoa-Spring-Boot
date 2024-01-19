
package com.luciano.cadastropessoa.cadastrarpessoa.controller

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.ResponseAddressDTO
import com.luciano.cadastropessoa.cadastrarpessoa.model.Address
import com.luciano.cadastropessoa.cadastrarpessoa.service.AddressService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/address")
class AddressController(private val addressService: AddressService) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createAddress(addressDTO: ResponseAddressDTO): ResponseAddressDTO {
        val address: Address = addressService.createAddress(addressDTO.toEntity())
        return ResponseAddressDTO.fromToEntity(address, addressDTO.country)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllAddress(): List<Address> = addressService.getAllAddress()

}
