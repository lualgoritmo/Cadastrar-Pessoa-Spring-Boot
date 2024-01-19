package com.luciano.cadastropessoa.cadastrarpessoa.service.impl

import com.luciano.cadastropessoa.cadastrarpessoa.exception.StateNotFoundException
import com.luciano.cadastropessoa.cadastrarpessoa.model.StateUF
import com.luciano.cadastropessoa.cadastrarpessoa.repository.StateRepository
import com.luciano.cadastropessoa.cadastrarpessoa.service.StateService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class StateServiceImpl(private val stateRepository: StateRepository) : StateService {
    override fun createState(state: StateUF): StateUF = stateRepository.save(state)
    override fun getAllStates(): List<StateUF> = stateRepository.findAll()
    @Transactional
    override fun getWithIdState(idState: Long): StateUF {
        return stateRepository.findById(idState).orElseThrow {
            throw StateNotFoundException(idState)
        }
    }
    override fun updateWithIdState(idState: Long, updateState: StateUF): StateUF {
        val existingState = stateRepository.findById(idState).orElseThrow {
            StateNotFoundException(idState)
        }
        val newState = existingState.copy(name = updateState.name)
        return stateRepository.save(newState)
    }
    @Transactional
    override fun deleteWithIdState(idState: Long) {
        try {
            stateRepository.deleteById(idState)
        } catch (ex: StateNotFoundException) {
            throw ex
        }
    }

}
