package com.fgiaquinta.katas.kt.flatmap

import com.fgiaquinta.katas.domain.EngineType.TURBOFAN
import com.fgiaquinta.katas.domain.EngineType.PISTON
import com.fgiaquinta.katas.domain.TestData.airport
import com.fgiaquinta.katas.domain.TestData.aircraft
import com.fgiaquinta.katas.domain.AircraftCategory.REGIONAL
import com.fgiaquinta.katas.domain.AircraftCategory.WIDE_BODY
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class A03CitiesOfferingCategoryTest {

    @Test
    @DisplayName("citiesOffering_withTwoAirportsInSameCityQualifying_returnsCityOnlyOnce")
    fun citiesOffering_withTwoAirportsInSameCityQualifying_returnsCityOnlyOnce() {
        val madrid1 = airport("MAD1", "Madrid", "Spain",
            aircraft("EC-AAA", WIDE_BODY, TURBOFAN, 300, 10_000, "60.00"))
        val madrid2 = airport("MAD2", "Madrid", "Spain",
            aircraft("EC-BBB", WIDE_BODY, TURBOFAN, 300, 8_000, "58.00"))
        val barcelona = airport("BCN", "Barcelona", "Spain",
            aircraft("EC-CCC", REGIONAL, PISTON, 11, 5_000, "30.00"))
        val valencia = airport("VAL", "Valencia", "Spain",
            aircraft("EC-DDD", WIDE_BODY, TURBOFAN, 300, 2_000, "55.00"))

        val result = citiesOffering(listOf(madrid1, madrid2, barcelona, valencia), WIDE_BODY)

        // Madrid qualifies via two different airports but must appear once; Barcelona has no WIDE_BODY.
        assertThat(result).containsExactlyInAnyOrder("Madrid", "Valencia")
    }

    @Test
    @DisplayName("citiesOffering_whenNoAirportHasCategory_returnsEmptySet")
    fun citiesOffering_whenNoAirportHasCategory_returnsEmptySet() {
        val barcelona = airport("BCN", "Barcelona", "Spain",
            aircraft("EC-CCC", REGIONAL, PISTON, 11, 5_000, "30.00"))

        val result = citiesOffering(listOf(barcelona), WIDE_BODY)

        assertThat(result).isEmpty()
    }
}
