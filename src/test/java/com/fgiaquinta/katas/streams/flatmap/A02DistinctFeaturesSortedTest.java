package com.fgiaquinta.katas.streams.flatmap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fgiaquinta.katas.domain.EngineType.PISTON;
import static com.fgiaquinta.katas.domain.AircraftCategory.REGIONAL;
import static com.fgiaquinta.katas.domain.TestData.airport;
import static com.fgiaquinta.katas.domain.TestData.aircraft;
import static org.assertj.core.api.Assertions.assertThat;

class A02DistinctFeaturesSortedTest {

    @Test
    @DisplayName("distinctFeaturesSorted_withOverlappingFeatures_returnsDistinctNamesAlphabetically")
    void distinctFeaturesSorted_withOverlappingFeatures_returnsDistinctNamesAlphabetically() {
        var madrid = airport("MAD", "Madrid", "Spain",
                aircraft("EC-AAA", REGIONAL, PISTON, 11, 10_000, "40.00", "WIFI", "CATERING"),
                // "CATERING" repeated across aircraft, must appear only once in the result
                aircraft("EC-BBB", REGIONAL, PISTON, 11, 20_000, "42.00", "CATERING", "FLATBED"));
        var barcelona = airport("BCN", "Barcelona", "Spain",
                aircraft("EC-CCC", REGIONAL, PISTON, 11, 5_000, "38.00", "WIFI", "PET_CABIN"));

        var result = A02DistinctFeaturesSorted.distinctFeaturesSorted(List.of(madrid, barcelona));

        // Distinct features across all aircraft: WIFI, CATERING, FLATBED, PET_CABIN -> sorted alphabetically.
        assertThat(result).containsExactly("CATERING", "FLATBED", "PET_CABIN", "WIFI");
    }

    @Test
    @DisplayName("distinctFeaturesSorted_whenNoAircraftHasFeatures_returnsEmptyList")
    void distinctFeaturesSorted_whenNoAircraftHasFeatures_returnsEmptyList() {
        var madrid = airport("MAD", "Madrid", "Spain",
                aircraft("EC-AAA", REGIONAL, PISTON, 11, 10_000, "40.00"));

        var result = A02DistinctFeaturesSorted.distinctFeaturesSorted(List.of(madrid));

        assertThat(result).isEmpty();
    }
}
