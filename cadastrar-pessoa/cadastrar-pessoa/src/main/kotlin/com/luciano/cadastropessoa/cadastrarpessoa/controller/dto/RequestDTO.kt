package com.luciano.cadastropessoa.cadastrarpessoa.controller.dto

data class RequestDTO(
        val documento: String,
        val nome: String,
        val idProposta: String
)

data class ResponseDTORequire(
        val documento: String,
        val nome: String,
        val idProposta: String,
        val resultadoSolicitacao: SolicitacionResult
)

enum class SolicitacionResult {
    COM_RESTRICAO,
    SEM_RESTRICAO
}

