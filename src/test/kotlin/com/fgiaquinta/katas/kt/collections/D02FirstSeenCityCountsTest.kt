package com.fgiaquinta.katas.kt.collections

import com.fgiaquinta.katas.domain.AircraftCategory.NARROW_BODY
import com.fgiaquinta.katas.domain.AircraftCategory.REGIONAL
import com.fgiaquinta.katas.domain.AircraftCategory.WIDE_BODY
import com.fgiaquinta.katas.domain.EngineType.PISTON
import com.fgiaquinta.katas.domain.EngineType.TURBOFAN
import com.fgiaquinta.katas.domain.TestData.aircraft
import com.fgiaquinta.katas.domain.TestData.airport
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class D02FirstSeenCityCountsTest {

    private val a1 = aircraft("EC-001", REGIONAL, PISTON, 11, 1000, "30.00")
    private val a2 = aircraft("EC-002", NARROW_BODY, TURBOFAN, 180, 1200, "35.00")
    private val a3 = aircraft("EC-003", WIDE_BODY, TURBOFAN, 300, 2000, "60.00")

    @Test
    @DisplayName("aircraftCountByCity_withUnsortedCities_keepsFirstSeenOrder")
    fun aircraftCountByCity_withUnsortedCities_keepsFirstSeenOrder() {
        val airports = listOf(
            airport("SEV", "Sevilla", "Spain", a1),
            airport("MAD", "Madrid", "Spain", a2, a3),
            airport("BCN", "Barcelona", "Spain", a1))

        val result = aircraftCountByCity(airports)

        assertThat(result.toList()).containsExactly(
            ("Sevilla" to 1), ("Madrid" to 2), ("Barcelona" to 1))
    }

    @Test
    @DisplayName("aircraftCountByCity_withRepeatedCity_mergesCountsAtFirstPosition")
    fun aircraftCountByCity_withRepeatedCity_mergesCountsAtFirstPosition() {
        val airports = listOf(
            airport("MAD", "Madrid", "Spain", a1),
            airport("SEV", "Sevilla", "Spain", a2),
            airport("TOJ", "Madrid", "Spain", a2, a3))

        assertThat(aircraftCountByCity(airports).toList()).containsExactly(("Madrid" to 3), ("Sevilla" to 1))
    }

    @Test
    @DisplayName("aircraftCountByCity_withEmptyFleet_keepsCityWithZero")
    fun aircraftCountByCity_withEmptyFleet_keepsCityWithZero() {
        val airports = listOf(airport("VLC", "Valencia", "Spain"))

        assertThat(aircraftCountByCity(airports).toList()).containsExactly(("Valencia" to 0))
    }

    @Test
    @DisplayName("aircraftCountByCity_withEmptyList_returnsEmptyMap")
    fun aircraftCountByCity_withEmptyList_returnsEmptyMap() {
        assertThat(aircraftCountByCity(emptyList())).isEmpty()
    }

    @Test
    @DisplayName("aircraftCountByCity_returnsLinkedHashMapWithoutExplicitFactory")
    fun aircraftCountByCity_returnsLinkedHashMapWithoutExplicitFactory() {
        val result = aircraftCountByCity(listOf(airport("MAD", "Madrid", "Spain", a1)))

        // Kotlin's default map builders already produce java.util.LinkedHashMap.
        assertThat(result).isInstanceOf(java.util.LinkedHashMap::class.java)
    }
}
