package com.luciano.cadastropessoa.cadastrarpessoa.controller

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.RequireCEPDTO
import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.ResponseCEPDTO
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.RequestAddressClient
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/address")
class RequestAddressController(private val requestAddressClient: RequestAddressClient) {
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    fun getAddressByCep(@RequestBody requestCep: RequireCEPDTO): ResponseCEPDTO {
        return requestAddressClient.getAddressByCep(requestCep.cep)
    }

}
