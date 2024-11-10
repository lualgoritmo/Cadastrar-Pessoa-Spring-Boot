package com.luciano.cadastropessoa.cadastrarpessoa.controller

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.CreateClientDTO
import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.FullClientDTO
import com.luciano.cadastropessoa.cadastrarpessoa.exception.ClientNotFoundException
import com.luciano.cadastropessoa.cadastrarpessoa.model.ClientUser
import com.luciano.cadastropessoa.cadastrarpessoa.service.ClientService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/clientîes/")
class ClientController(private val clientService: ClientService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createClient(@RequestBody @Valid clientDTO: CreateClientDTO): CreateClientDTO {
        val client: ClientUser = clientService.createClient(clientDTO.toEntity())
        return CreateClientDTO.fromEntity(client)
    }

    @GetMapping("/{idClient}")
    @ResponseStatus(HttpStatus.OK)
    fun getByIdClient(@PathVariable idClient: Long): CreateClientDTO {
        try {
            val client: ClientUser = clientService.getByIdClient(idClient)
            return CreateClientDTO.fromEntity(client)
        } catch (ex: ClientNotFoundException) {
            throw ex
        }
    }
    @GetMapping("/{idClient}/full")
    @ResponseStatus(HttpStatus.OK)
    fun getFullClientById(@PathVariable idClient: Long): FullClientDTO = clientService.getFullClientById(idClient)

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
