package com.luciano.cadastropessoa.cadastrarpessoa.controller.dto

import com.luciano.cadastropessoa.cadastrarpessoa.model.Address

class ResponseAddressDTO(
    val cep: String,
    val road: String,
    val city: String,
    val numberResidence: String,
    val complement: String,
    val state: String
) {
    fun toEntity() = Address(
        idAddress = 0,
        cep = this.cep,
        road = this.road,
        city = this.city,
        numberResidence = this.numberResidence,
        complement = this.complement,
        state = ResponseStateDTO(name = this.state).toEntity()
    )
    companion object {
        private val processedCountries: MutableMap<String, CountryDTO> = mutableMapOf()
        fun fromToEntity(address: Address): ResponseAddressDTO {
            //val countryName = address.state.country.name
            //val state = ResponseStateDTO(name = address.state.name)
//            val countryDTO = processedCountries.computeIfAbsent(countryName) {
//                CountryDTO(countryName)
//            }

            return ResponseAddressDTO(
                    cep = address.cep,
                    road = address.road,
                    city = address.city,
                    numberResidence = address.numberResidence,
                    complement = address.complement,
                    state = address.state.name
            )
        }
    }
}

