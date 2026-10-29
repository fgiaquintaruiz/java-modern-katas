package com.fgiaquinta.katas.kt.flatmap

import com.fgiaquinta.katas.domain.EngineType.PISTON
import com.fgiaquinta.katas.domain.TestData.airport
import com.fgiaquinta.katas.domain.TestData.aircraft
import com.fgiaquinta.katas.domain.AircraftCategory.REGIONAL
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class A02DistinctFeaturesSortedTest {

    @Test
    @DisplayName("distinctFeaturesSorted_withOverlappingFeatures_returnsDistinctNamesAlphabetically")
    fun distinctFeaturesSorted_withOverlappingFeatures_returnsDistinctNamesAlphabetically() {
        val madrid = airport("MAD", "Madrid", "Spain",
            aircraft("EC-AAA", REGIONAL, PISTON, 11, 10_000, "40.00", "WIFI", "CATERING"),
            // "CATERING" repeated across aircraft, must appear only once in the result
            aircraft("EC-BBB", REGIONAL, PISTON, 11, 20_000, "42.00", "CATERING", "FLATBED"))
        val barcelona = airport("BCN", "Barcelona", "Spain",
            aircraft("EC-CCC", REGIONAL, PISTON, 11, 5_000, "38.00", "WIFI", "PET_CABIN"))

        val result = distinctFeaturesSorted(listOf(madrid, barcelona))

        // Distinct features across all aircraft: WIFI, CATERING, FLATBED, PET_CABIN -> sorted alphabetically.
        assertThat(result).containsExactly("CATERING", "FLATBED", "PET_CABIN", "WIFI")
    }

    @Test
    @DisplayName("distinctFeaturesSorted_whenNoAircraftHasFeatures_returnsEmptyList")
    fun distinctFeaturesSorted_whenNoAircraftHasFeatures_returnsEmptyList() {
        val madrid = airport("MAD", "Madrid", "Spain",
            aircraft("EC-AAA", REGIONAL, PISTON, 11, 10_000, "40.00"))

        val result = distinctFeaturesSorted(listOf(madrid))

        assertThat(result).isEmpty()
    }
}
