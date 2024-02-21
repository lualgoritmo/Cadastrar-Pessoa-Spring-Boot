package com.luciano.cadastropessoa.cadastrarpessoa.service.impl

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.ResponseCEPDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "solicitar-cep", url = "viacep.com.br")
interface RequestAddressClient {
    @GetMapping("/ws/{cep}/json/")
    fun getAddressByCep(@PathVariable cep: String): ResponseCEPDTO
}