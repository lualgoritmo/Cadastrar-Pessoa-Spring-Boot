package com.luciano.cadastropessoa.cadastrarpessoa.controller

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.CreateClientDTO
import com.luciano.cadastropessoa.cadastrarpessoa.exception.ClientNotFoundException
import com.luciano.cadastropessoa.cadastrarpessoa.model.Client
import com.luciano.cadastropessoa.cadastrarpessoa.service.ClientService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/clients")
class ClientController(private val clientService: ClientService) {

    @PostMapping("/{idAddress}")
    @ResponseStatus(HttpStatus.CREATED)
    fun createClient(@RequestBody @Valid clientDTO: CreateClientDTO): CreateClientDTO {
        val client: Client = clientService.createClient(clientDTO.toEntity())
        return CreateClientDTO.fromEntity(client)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getByIdClient(@PathVariable idClient: Long): CreateClientDTO {
        try {
            val client: Client = clientService.getByIdClient(idClient)
            return CreateClientDTO.fromEntity(client)
        } catch (ex: ClientNotFoundException) {
            throw ex
        }
    }

    @PutMapping("/{idClient}/updates")
    @ResponseStatus(HttpStatus.OK)
    fun updateClient(@PathVariable idClient: Long, @RequestBody clientDTO: CreateClientDTO): CreateClientDTO {
        try {
            val updateClient = clientService.updateClient(idClient, clientDTO.toEntity())
            return CreateClientDTO.fromEntity(updateClient)
        } catch (ex: ClientNotFoundException) {
            throw ex
        }
    }
}
