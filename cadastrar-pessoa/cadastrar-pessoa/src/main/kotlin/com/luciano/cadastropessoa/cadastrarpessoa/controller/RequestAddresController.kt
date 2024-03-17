package com.luciano.cadastropessoa.cadastrarpessoa.controller

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.RequireCEPDTO
import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.ResponseCEPDTO
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.RequestAddressFeingClient
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/address")
class RequestAddressController(private val requestAddressClient: RequestAddressFeingClient) {
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    fun getAddressByCep(@RequestBody requestCep: RequireCEPDTO): ResponseCEPDTO {
        return requestAddressClient.getAddressByCep(requestCep.cep)
    }

}
