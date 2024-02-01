package com.luciano.cadastropessoa.cadastrarpessoa.service

import com.luciano.cadastropessoa.cadastrarpessoa.build.AddressEntity
import com.luciano.cadastropessoa.cadastrarpessoa.build.ClientUserEntity
import com.luciano.cadastropessoa.cadastrarpessoa.build.StateEntity
import com.luciano.cadastropessoa.cadastrarpessoa.model.AddressUser
import com.luciano.cadastropessoa.cadastrarpessoa.repository.AddressRepository
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.AddressServiceImpl
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.ClientServiceImpl
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.StateServiceImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AddressServiceImplTest {
    @InjectMocks
    private lateinit var addressServiceImpl: AddressServiceImpl

    @Mock
    private lateinit var addressRepository: AddressRepository

    @Mock
    private lateinit var clientServiceImpl: ClientServiceImpl

    @Mock
    private lateinit var stateServiceImpl: StateServiceImpl

    @Test
    fun `when createAddress is called, it must save address`() {
        val client = ClientUserEntity().build()
        val state = StateEntity().build()
        val address = AddressEntity(client = client, state = state).build()

        `when`(clientServiceImpl.getByIdClient(client.idClient!!)).thenReturn(client)
        `when`(stateServiceImpl.getStateById(state.idState!!)).thenReturn(state)
        `when`(addressRepository.save(any(AddressUser::class.java))).thenReturn(address)

        val createAddress = addressServiceImpl.createAddress(AddressEntity.fromEntity(address))

        assertThat(createAddress).isInstanceOf(AddressUser::class.java)
        assertThat(createAddress).isNotNull

        verify(clientServiceImpl, times(1)).getByIdClient(client.idClient!!)
        verify(stateServiceImpl, times(1)).getStateById(state.idState!!)
        verify(addressRepository, times(1)).save(any())
    }

}
