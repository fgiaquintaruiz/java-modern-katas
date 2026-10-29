package com.fgiaquinta.katas.streams.flatmap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fgiaquinta.katas.domain.EngineType.TURBOFAN;
import static com.fgiaquinta.katas.domain.EngineType.PISTON;
import static com.fgiaquinta.katas.domain.AircraftCategory.REGIONAL;
import static com.fgiaquinta.katas.domain.AircraftCategory.WIDE_BODY;
import static com.fgiaquinta.katas.domain.TestData.airport;
import static com.fgiaquinta.katas.domain.TestData.aircraft;
import static org.assertj.core.api.Assertions.assertThat;

class A03CitiesOfferingCategoryTest {

    @Test
    @DisplayName("citiesOffering_withTwoAirportsInSameCityQualifying_returnsCityOnlyOnce")
    void citiesOffering_withTwoAirportsInSameCityQualifying_returnsCityOnlyOnce() {
        var madrid1 = airport("MAD1", "Madrid", "Spain",
                aircraft("EC-AAA", WIDE_BODY, TURBOFAN, 300, 10_000, "60.00"));
        var madrid2 = airport("MAD2", "Madrid", "Spain",
                aircraft("EC-BBB", WIDE_BODY, TURBOFAN, 300, 8_000, "58.00"));
        var barcelona = airport("BCN", "Barcelona", "Spain",
                aircraft("EC-CCC", REGIONAL, PISTON, 11, 5_000, "30.00"));
        var valencia = airport("VAL", "Valencia", "Spain",
                aircraft("EC-DDD", WIDE_BODY, TURBOFAN, 300, 2_000, "55.00"));

        var result = A03CitiesOfferingCategory.citiesOffering(
                List.of(madrid1, madrid2, barcelona, valencia), WIDE_BODY);

        // Madrid qualifies via two different airports but must appear once; Barcelona has no WIDE_BODY.
        assertThat(result).containsExactlyInAnyOrder("Madrid", "Valencia");
    }

    @Test
    @DisplayName("citiesOffering_whenNoAirportHasCategory_returnsEmptySet")
    void citiesOffering_whenNoAirportHasCategory_returnsEmptySet() {
        var barcelona = airport("BCN", "Barcelona", "Spain",
                aircraft("EC-CCC", REGIONAL, PISTON, 11, 5_000, "30.00"));

        var result = A03CitiesOfferingCategory.citiesOffering(List.of(barcelona), WIDE_BODY);

        assertThat(result).isEmpty();
    }
}
