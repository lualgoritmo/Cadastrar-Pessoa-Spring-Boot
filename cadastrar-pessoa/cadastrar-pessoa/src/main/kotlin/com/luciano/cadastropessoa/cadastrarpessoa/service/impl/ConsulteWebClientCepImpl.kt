package com.luciano.cadastropessoa.cadastrarpessoa.service.impl

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.ResponseCEPDTO
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder


@Service
class ConsulteWebClientCepImpl(
        private val clientWeb: WebClient,
        @Value("\${via_cep_uri}")
        val viaCepURI: String
) {
    suspend fun consulteViaCep(cep: String): ResponseCEPDTO {
        val uri = UriComponentsBuilder.fromUriString(viaCepURI)
                .buildAndExpand(cep)
                .toUri()

        return clientWeb.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(ResponseCEPDTO::class.java)
                .awaitFirstOrNull() ?: throw RuntimeException("DEU ERRO AQUI")

    }
}
