package com.luciano.cadastropessoa.cadastrarpessoa.controller

import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.RequestClientDTO
import com.luciano.cadastropessoa.cadastrarpessoa.controller.dto.ResponseDTORequire
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.RequestUserImpl
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/solicitacao")
class RequestUserController(private val userClient: RequestUserImpl) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createRequest(@RequestBody requestDTO: RequestClientDTO):ResponseDTORequire {
        return userClient.createRequest(requestDTO)
    }

    //No caso, usar o FeignClient, é uma consulta a um  microserviço?
    //Ou usar o FeignClient é ums microserviço?
    /*Eu entendi que usar o FeignClient,é para consumir uma api/serviço externo,
    certo?
    Se esse o caso, por exemplo, se tenho uma api de cadastro, e preciso usar a api viacep(serviço externo),
    seria também usando o FeignClient, única forma?
    Eu fiquei com dúvida aqui:
    @FeignClient(name = "solicitacao-analise", url = "http://localhost:9999", path = "/api/solicitacao")
    url, eu sei que é a servers da api, o path é o requestMapping no cocontroller e na interface(e um padrão?)*/

}
