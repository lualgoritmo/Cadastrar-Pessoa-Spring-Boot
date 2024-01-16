package com.luciano.cadastropessoa.cadastrarpessoa.service

interface StateService {
    fun createState(state: StateService): StateService
    fun getAllStates(): List<StateService>
    fun getByIdState(idState: Long): StateService
    fun updateState(idState: Long, updateState: StateService)
    fun deleteByIdState()
}