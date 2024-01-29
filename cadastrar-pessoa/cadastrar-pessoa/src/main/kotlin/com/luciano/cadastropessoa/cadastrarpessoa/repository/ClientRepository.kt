package com.luciano.cadastropessoa.cadastrarpessoa.repository

import com.luciano.cadastropessoa.cadastrarpessoa.model.ClientUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository : JpaRepository<ClientUser, Long>