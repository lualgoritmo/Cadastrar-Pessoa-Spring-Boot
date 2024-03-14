package com.luciano.cadastropessoa.cadastrarpessoa.controller

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.ResponseCEPDTO
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.ConsulteWebClientCepImpl
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/cepaddress")
class ConsulteCepController(private val consulteCep: ConsulteWebClientCepImpl) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    suspend fun consulteCep(@RequestParam("cep")cep: String): ResponseCEPDTO {
        return consulteCep.consulteViaCep(cep)
    }
}
