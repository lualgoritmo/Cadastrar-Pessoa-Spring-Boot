package com.luciano.cadastropessoa.cadastrarpessoa.service

import com.luciano.cadastropessoa.cadastrarpessoa.model.StateUF

interface StateService {
    fun createState(state: StateUF): StateUF
    fun getAllStates(): List<StateUF>
    fun getStateById(idState: Long): StateUF
    fun updateWithIdState(idState: Long, updateState: StateUF): StateUF
    fun deleteWithIdState(idState: Long)
}