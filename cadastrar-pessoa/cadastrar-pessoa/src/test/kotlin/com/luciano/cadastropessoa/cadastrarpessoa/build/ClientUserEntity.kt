package com.luciano.cadastropessoa.cadastrarpessoa.build

import com.luciano.cadastropessoa.cadastrarpessoa.model.Address
import com.luciano.cadastropessoa.cadastrarpessoa.model.ClientUser

data class ClientUserEntity(
        val idClient: Long = 1,
        val email: String = "luciano@luciano.com",
        val name: String = "Alfredo",
        val surname: String = " Carlos Eite",
        val document: String = "123456789",
        val phone: String = "27-1234-1234",
        val address: List<Address> = emptyList()

) {
    fun build() = ClientUser(
            idClient = this.idClient,
            email = this.email,
            name = this.name,
            surname = this.surname,
            document = this.document,
            phone = this.phone,
            addresses = address
    )
}
