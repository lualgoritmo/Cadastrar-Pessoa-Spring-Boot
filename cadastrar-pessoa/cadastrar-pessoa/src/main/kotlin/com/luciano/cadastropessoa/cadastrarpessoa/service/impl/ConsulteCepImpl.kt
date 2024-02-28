package com.luciano.cadastropessoa.cadastrarpessoa.service.impl

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.ResponseCEPDTO
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder

@Service
class ConsulteCepImpl(private val clientWeb: WebClient) {
    suspend fun consulteViaCep(cep: String): ResponseCEPDTO {
        val uri = UriComponentsBuilder.fromUriString("https://viacep.com.br/ws/{cep}/json/")
                .buildAndExpand(cep)
                .toUri()

        return clientWeb.get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(ResponseCEPDTO::class.java)
                    .awaitFirstOrNull() ?: throw RuntimeException("DEU ERRO AQUI")

    }
}
