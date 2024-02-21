package com.luciano.cadastropessoa.cadastrarpessoa.service.impl

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.RequestDTO
import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.ResponseDTORequire
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
@FeignClient(name = "solicitacao-analise", url = "http://localhost:9999")
interface RequestUser {
    @PostMapping("/api/solicitacao",consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createRequest(@RequestBody solicitacaoDTO: RequestDTO): ResponseDTORequire

}
