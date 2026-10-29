package com.fgiaquinta.katas.streams.flatmap;

import com.fgiaquinta.katas.domain.Airport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fgiaquinta.katas.domain.EngineType.PISTON;
import static com.fgiaquinta.katas.domain.AircraftCategory.REGIONAL;
import static com.fgiaquinta.katas.domain.TestData.airport;
import static com.fgiaquinta.katas.domain.TestData.aircraft;
import static org.assertj.core.api.Assertions.assertThat;

class A01AllRegistrationsTest {

    @Test
    @DisplayName("allRegistrations_withMultipleAirports_returnsRegistrationsInAirportThenFleetOrder")
    void allRegistrations_withMultipleAirports_returnsRegistrationsInAirportThenFleetOrder() {
        var madrid = airport("MAD", "Madrid", "Spain",
                aircraft("EC-AAA", REGIONAL, PISTON, 11, 10_000, "40.00"),
                aircraft("EC-BBB", REGIONAL, PISTON, 11, 20_000, "42.00"));
        var barcelona = airport("BCN", "Barcelona", "Spain");
        var valencia = airport("VAL", "Valencia", "Spain",
                // same registration as Madrid's first aircraft: duplicates must be preserved
                aircraft("EC-AAA", REGIONAL, PISTON, 11, 5_000, "38.00"),
                aircraft("EC-CCC", REGIONAL, PISTON, 11, 1_000, "45.00"));

        var result = A01AllRegistrations.allRegistrations(List.of(madrid, barcelona, valencia));

        // Madrid's fleet registrations, then Barcelona (empty, contributes nothing), then Valencia's fleet registrations.
        assertThat(result).containsExactly("EC-AAA", "EC-BBB", "EC-AAA", "EC-CCC");
    }

    @Test
    @DisplayName("allRegistrations_withEmptyAirportList_returnsEmptyList")
    void allRegistrations_withEmptyAirportList_returnsEmptyList() {
        var result = A01AllRegistrations.allRegistrations(List.of());

        assertThat(result).isEmpty();
    }
}
