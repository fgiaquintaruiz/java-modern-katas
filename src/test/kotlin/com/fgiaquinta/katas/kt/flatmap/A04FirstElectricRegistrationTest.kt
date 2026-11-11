package com.fgiaquinta.katas.kt.flatmap

import com.fgiaquinta.katas.domain.EngineType.TURBOPROP
import com.fgiaquinta.katas.domain.EngineType.ELECTRIC
import com.fgiaquinta.katas.domain.EngineType.PISTON
import com.fgiaquinta.katas.domain.AircraftCategory.REGIONAL
import com.fgiaquinta.katas.domain.TestData.airport
import com.fgiaquinta.katas.domain.TestData.aircraft
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class A04FirstElectricRegistrationTest {

    @Test
    @DisplayName("firstElectricRegistration_withElectricAircraftInSecondAirport_returnsFirstMatchInEncounterOrder")
    fun firstElectricRegistration_withElectricAircraftInSecondAirport_returnsFirstMatchInEncounterOrder() {
        val madrid = airport("MAD", "Madrid", "Spain",
            aircraft("EC-AAA", REGIONAL, PISTON, 11, 10_000, "40.00"),
            aircraft("EC-BBB", REGIONAL, TURBOPROP, 70, 20_000, "42.00"))
        val barcelona = airport("BCN", "Barcelona", "Spain",
            // first electric aircraft encountered when scanning airports then fleet in order
            aircraft("EC-E01", REGIONAL, ELECTRIC, 9, 1_000, "50.00"),
            aircraft("EC-E02", REGIONAL, ELECTRIC, 9, 500, "52.00"))

        val result = firstElectricRegistration(listOf(madrid, barcelona))

        assertThat(result).isEqualTo("EC-E01")
    }

    @Test
    @DisplayName("firstElectricRegistration_withNoElectricAircraft_returnsEmptyOptional")
    fun firstElectricRegistration_withNoElectricAircraft_returnsEmptyOptional() {
        val madrid = airport("MAD", "Madrid", "Spain",
            aircraft("EC-AAA", REGIONAL, PISTON, 11, 10_000, "40.00"),
            aircraft("EC-BBB", REGIONAL, TURBOPROP, 70, 20_000, "42.00"))

        val result = firstElectricRegistration(listOf(madrid))

        // Kotlin twin models "no value" as null instead of Optional.empty().
        assertThat(result).isNull()
    }
}
