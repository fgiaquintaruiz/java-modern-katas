package com.fgiaquinta.katas.functional.methodref;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fgiaquinta.katas.domain.EngineType.TURBOFAN;
import static com.fgiaquinta.katas.domain.EngineType.PISTON;
import static com.fgiaquinta.katas.domain.TestData.airport;
import static com.fgiaquinta.katas.domain.TestData.aircraft;
import static com.fgiaquinta.katas.domain.AircraftCategory.REGIONAL;
import static com.fgiaquinta.katas.domain.AircraftCategory.WIDE_BODY;
import static org.assertj.core.api.Assertions.assertThat;

class M06AircraftInAirportFleetTest {

    @Test
    @DisplayName("inFleet_withMixedCandidates_keepsOnlyFleetAircraftInInputOrder")
    void inFleet_withMixedCandidates_keepsOnlyFleetAircraftInInputOrder() {
        // Only wideBody and regional are in the Madrid fleet; outsider is not.
        var wideBody = aircraft("EC-AAA", WIDE_BODY, TURBOFAN, 300, 10_000, "60.00");
        var regional = aircraft("EC-BBB", REGIONAL, PISTON, 11, 5_000, "30.00");
        var outsider = aircraft("EC-CCC", REGIONAL, PISTON, 11, 7_000, "45.50");
        var madrid = airport("MAD", "Madrid", "Spain", wideBody, regional);

        assertThat(M06AircraftInAirportFleet.inFleet(List.of(regional, outsider, wideBody), madrid))
                .containsExactly(regional, wideBody);
    }

    @Test
    @DisplayName("inFleet_withEmptyFleet_returnsEmptyList")
    void inFleet_withEmptyFleet_returnsEmptyList() {
        var wideBody = aircraft("EC-AAA", WIDE_BODY, TURBOFAN, 300, 10_000, "60.00");
        var empty = airport("MAD", "Madrid", "Spain");

        assertThat(M06AircraftInAirportFleet.inFleet(List.of(wideBody), empty)).isEmpty();
    }
}
