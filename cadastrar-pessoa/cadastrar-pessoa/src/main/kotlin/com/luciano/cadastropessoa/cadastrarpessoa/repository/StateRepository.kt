package com.luciano.cadastropessoa.cadastrarpessoa.repository

import com.luciano.cadastropessoa.cadastrarpessoa.model.StateUF
import org.springframework.data.jpa.repository.JpaRepository

interface StateRepository : JpaRepository<StateUF, Long>