package com.luciano.cadastropessoa.cadastrarpessoa.controller.dto

data class PageResponse<T>(
        val content: List<T>,
        val page: Int,
        val totalPages: Int,
        val totalElements: Long
)
