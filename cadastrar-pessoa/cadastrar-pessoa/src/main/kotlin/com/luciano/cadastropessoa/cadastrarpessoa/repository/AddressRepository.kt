package com.luciano.cadastropessoa.cadastrarpessoa.repository

import com.luciano.cadastropessoa.cadastrarpessoa.model.Address
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
@Repository
interface AddressRepository : JpaRepository<Address, Long>
