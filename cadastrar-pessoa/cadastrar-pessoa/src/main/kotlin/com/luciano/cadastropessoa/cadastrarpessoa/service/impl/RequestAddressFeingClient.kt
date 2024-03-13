package com.luciano.cadastropessoa.cadastrarpessoa.service.impl

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.ResponseCEPDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "solicitar-cep", url = "https://viacep.com.br")
interface RequestAddressFeingClient {
    @GetMapping("/ws/{cep}/json/", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getAddressByCep(@PathVariable cep: String): ResponseCEPDTO
}