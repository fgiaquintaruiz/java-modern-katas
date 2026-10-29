package com.fgiaquinta.katas.kt.flatmap

import com.fgiaquinta.katas.domain.EngineType.PISTON
import com.fgiaquinta.katas.domain.TestData.airport
import com.fgiaquinta.katas.domain.TestData.aircraft
import com.fgiaquinta.katas.domain.AircraftCategory.REGIONAL
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class A01AllRegistrationsTest {

    @Test
    @DisplayName("allRegistrations_withMultipleAirports_returnsRegistrationsInAirportThenFleetOrder")
    fun allRegistrations_withMultipleAirports_returnsRegistrationsInAirportThenFleetOrder() {
        val madrid = airport("MAD", "Madrid", "Spain",
            aircraft("EC-AAA", REGIONAL, PISTON, 11, 10_000, "40.00"),
            aircraft("EC-BBB", REGIONAL, PISTON, 11, 20_000, "42.00"))
        val barcelona = airport("BCN", "Barcelona", "Spain")
        val valencia = airport("VAL", "Valencia", "Spain",
            // same registration as Madrid's first aircraft: duplicates must be preserved
            aircraft("EC-AAA", REGIONAL, PISTON, 11, 5_000, "38.00"),
            aircraft("EC-CCC", REGIONAL, PISTON, 11, 1_000, "45.00"))

        val result = allRegistrations(listOf(madrid, barcelona, valencia))

        // Madrid's fleet registrations, then Barcelona (empty, contributes nothing), then Valencia's fleet registrations.
        assertThat(result).containsExactly("EC-AAA", "EC-BBB", "EC-AAA", "EC-CCC")
    }

    @Test
    @DisplayName("allRegistrations_withEmptyAirportList_returnsEmptyList")
    fun allRegistrations_withEmptyAirportList_returnsEmptyList() {
        val result = allRegistrations(listOf())

        assertThat(result).isEmpty()
    }
}
