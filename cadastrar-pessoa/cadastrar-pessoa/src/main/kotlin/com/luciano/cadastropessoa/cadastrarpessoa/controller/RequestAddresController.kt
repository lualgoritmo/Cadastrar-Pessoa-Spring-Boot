package com.luciano.cadastropessoa.cadastrarpessoa.controller

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.ResponseCEPDTO
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.RequestAddressFeingClient
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/address")
class RequestAddressController(private val requestAddressClient: RequestAddressFeingClient) {
    @GetMapping("/{cep}")
    @ResponseStatus(HttpStatus.OK)
    fun getCepById(@PathVariable cep: String): ResponseCEPDTO = requestAddressClient.getAddressByCep(cep)

}
