package com.luciano.cadastropessoa.cadastrarpessoa.service

import com.luciano.cadastropessoa.cadastrarpessoa.model.Country
import com.luciano.cadastropessoa.cadastrarpessoa.model.StateUF
import com.luciano.cadastropessoa.cadastrarpessoa.repository.CountryRepository
import com.luciano.cadastropessoa.cadastrarpessoa.service.impl.CountryServiceImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class CountryServiceImplTest {

    @InjectMocks
    private lateinit var countryServiceImpl: CountryServiceImpl

    @Mock
    private lateinit var countryRepository: CountryRepository

    private fun countryObject() = Country(idCountry = 1, name = "Brasil")

    private fun listState(): List<StateUF> = listOf(
            StateUF(idState = 1, name = "SP", countryObject())
    )

    @Test
    fun `When executing the createCountrie method, you must save a country`() {
        val country = countryObject()
        `when`(countryRepository.save(country)).thenReturn(country)

        val saveCountry = countryServiceImpl.createCountrie(country)

        assertThat(saveCountry).isNotNull
        assertThat(saveCountry).isEqualTo(country)
        assertThat(saveCountry.idCountry).isEqualTo(country.idCountry)
        assertThat(saveCountry.name).isEqualTo(country.name)

        verify(countryRepository, times(1)).save(country)
    }

    @Test
    fun `when executing the getAllCountries method, it must return a list of countries`() {
        val listCountries: List<Country> = listOf(
                Country(idCountry = 1, name = "Brasil"),
                Country(idCountry = 2, name = "Portugal"),
                Country(idCountry = 3, name = "Inglaterra"),
                Country(idCountry = 4, name = "Inglaterra")
        )

        `when`(countryRepository.findAll()).thenReturn(listCountries)

        val newListCountries = countryServiceImpl.getAllCountries()
        assertThat(newListCountries).isNotNull
        assertThat(newListCountries).isEqualTo(listCountries)
        assertThat(newListCountries[0].name).isEqualTo("Brasil")
        verify(countryRepository, times(1)).findAll()
    }

    @Test
    fun `when executing the getWithIdCountry method, it must return a country`() {
        val country = countryObject()

        `when`(countryRepository.findById(country.idCountry!!)).thenReturn(Optional.of(country))
        val countryId = countryServiceImpl.getWithIdCountry(country.idCountry!!)

        assertThat(countryId).isNotNull
        assertThat(countryId).isEqualTo(country)
        assertThat(countryId.idCountry).isEqualTo(country.idCountry)
        assertThat(countryId.name).isEqualTo("Brasil")

        verify(countryRepository, times(1)).findById(country.idCountry!!)
    }

    @Test
    fun `when executing the updateWithIdCountry method, it must return an updated country`() {
        val existingCountry = countryObject()

        val updateCountry = Country(
                idCountry = 1,
                name = "Alemanhã"
        )
        `when`(countryRepository.findById(existingCountry.idCountry!!)).thenReturn(Optional.of(existingCountry))
        `when`(countryRepository.save(updateCountry)).thenReturn(updateCountry)
        val newCountry = countryServiceImpl.updateWithIdCountry(existingCountry.idCountry!!, updateCountry)

        assertThat(newCountry).isNotNull
        assertThat(newCountry.idCountry).isEqualTo(1)
        assertThat(newCountry.name).isEqualTo("Alemanhã")

        verify(countryRepository, times(1)).save(updateCountry)
        verify(countryRepository, times(1)).findById(existingCountry.idCountry!!)
    }

    @Test
    fun `when executing the deleteWithIdContry method, it should return nothing`() {
        val country = countryObject()
        countryRepository.save(country)

        `when`(countryRepository.deleteById(country.idCountry!!)).then { }

        countryServiceImpl.deleteWithIdContry(country.idCountry!!)

        val deleteCountry = countryRepository.findById(country.idCountry!!).orElse(null)
        assertNull(deleteCountry, "O country não deve existir")

        verify(countryRepository, times(1)).save(country)
        verify(countryRepository, times(1)).deleteById(country.idCountry!!)
        verify(countryRepository, times(1)).findById(country.idCountry!!)
    }
}
