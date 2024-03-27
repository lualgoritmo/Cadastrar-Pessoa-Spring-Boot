package com.luciano.cadastropessoa.cadastrarpessoa.service

import com.luciano.cadastropessoa.cadastrarpessoa.build.ClientUserEntity
import com.luciano.cadastropessoa.cadastrarpessoa.model.ClientUser
import com.luciano.cadastropessoa.cadastrarpessoa.repository.ClientRepository
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.ClientServiceImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@ExtendWith(SpringExtension::class)
class ClientServiceImplTest {

    @InjectMocks
    private lateinit var clientServiceImpl: ClientServiceImpl

    @Mock
    private lateinit var clientRepository: ClientRepository

    @Test
    fun `when createClientUser is called, it should return one client`() {
        val clienteUser = ClientUserEntity().build()

        `when`(clientRepository.save(any())).thenReturn(clienteUser)

        val saveClient = clientServiceImpl.createClient(clienteUser)

        assertThat(saveClient).isInstanceOf(ClientUser::class.java)
        assertThat(saveClient).isEqualTo(clienteUser)
        assertThat(saveClient.idClient).isEqualTo(clienteUser.idClient)
        assertThat(saveClient.name).isEqualTo("Alfredo")

        verify(clientRepository, times(1)).save(saveClient)
    }

    @Test
    fun `when getByIdClient is called, it should return client with id`() {
        val clienteUser = ClientUserEntity().build()
        `when`(clientRepository.findById(any())).thenReturn(Optional.of(clienteUser))

        val clientWithId = clientServiceImpl.getByIdClient(clienteUser.idClient!!)

        assertThat(clientWithId.idClient).isEqualTo(clienteUser.idClient)
        assertThat(clientWithId.email).isEqualTo(clienteUser.email)
        assertThat(clientWithId.name).isEqualTo(clienteUser.name)
        assertThat(clientWithId.document).isEqualTo(clienteUser.document)
        assertThat(clientWithId.surname).isEqualTo(clienteUser.surname)
        assertThat(clientWithId.phone).isEqualTo(clienteUser.phone)
        assertThat(clientWithId).isInstanceOf(ClientUser::class.java)

        verify(clientRepository, times(1)).findById(clientWithId.idClient!!)
    }

    @Test
    fun `when updateClient is called, it should return update client`() {
        val existingClient = ClientUserEntity().build()

        val updateClient = ClientUser(
                idClient = existingClient.idClient,
                email = "novoemail@emails.com",
                name = "Novo Nome",
                surname = "Novo SobreNome",
                document = "111222333444555",
                phone = "14-9-9999-9999"
        )
        `when`(clientRepository.findById(existingClient.idClient!!)).thenReturn(Optional.of(existingClient))
        `when`(clientRepository.save(any(ClientUser::class.java))).thenAnswer { invocation ->
            val savedClient = invocation.arguments[0] as ClientUser
            return@thenAnswer savedClient
        }

        val newClient = clientServiceImpl.updateClient(existingClient.idClient!!, updateClient)

        assertThat(newClient).isNotNull
        assertThat(newClient).isInstanceOf(ClientUser::class.java)
        assertThat(newClient.idClient).isEqualTo(existingClient.idClient)
        assertThat(newClient.email).isEqualTo(updateClient.email)
        assertThat(newClient.name).isEqualTo(updateClient.name)
        assertThat(newClient.surname).isEqualTo(updateClient.surname)
        assertThat(newClient.document).isEqualTo(updateClient.document)
        assertThat(newClient.phone).isEqualTo(updateClient.phone)

        verify(clientRepository, times(1)).findById(existingClient.idClient!!)
        verify(clientRepository, times(1)).save(updateClient)
    }

    @Test
    fun `when deleteClients is called, it returns any`() {
        val clienteUser = ClientUserEntity().build()
        clientRepository.save(clienteUser)

        `when`(clientRepository.deleteById(any())).then { }

        clientServiceImpl.deleteClient(clienteUser.idClient!!)

        val deletedClient = clientRepository.findById(clienteUser.idClient!!).orElse(null)

        assertNull(deletedClient, "Não é para existir este id")

        verify(clientRepository, times(1)).deleteById(clienteUser.idClient!!)
    }

}
