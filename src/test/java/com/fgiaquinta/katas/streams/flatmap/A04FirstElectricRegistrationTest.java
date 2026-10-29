package com.fgiaquinta.katas.streams.flatmap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.fgiaquinta.katas.domain.EngineType.TURBOPROP;
import static com.fgiaquinta.katas.domain.EngineType.ELECTRIC;
import static com.fgiaquinta.katas.domain.EngineType.PISTON;
import static com.fgiaquinta.katas.domain.AircraftCategory.REGIONAL;
import static com.fgiaquinta.katas.domain.TestData.airport;
import static com.fgiaquinta.katas.domain.TestData.aircraft;
import static org.assertj.core.api.Assertions.assertThat;

class A04FirstElectricRegistrationTest {

    @Test
    @DisplayName("firstElectricRegistration_withElectricAircraftInSecondAirport_returnsFirstMatchInEncounterOrder")
    void firstElectricRegistration_withElectricAircraftInSecondAirport_returnsFirstMatchInEncounterOrder() {
        var madrid = airport("MAD", "Madrid", "Spain",
                aircraft("EC-AAA", REGIONAL, PISTON, 11, 10_000, "40.00"),
                aircraft("EC-BBB", REGIONAL, TURBOPROP, 70, 20_000, "42.00"));
        var barcelona = airport("BCN", "Barcelona", "Spain",
                // first electric aircraft encountered when scanning airports then fleet in order
                aircraft("EC-E01", REGIONAL, ELECTRIC, 9, 1_000, "50.00"),
                aircraft("EC-E02", REGIONAL, ELECTRIC, 9, 500, "52.00"));

        var result = A04FirstElectricRegistration.firstElectricRegistration(List.of(madrid, barcelona));

        assertThat(result).isEqualTo(Optional.of("EC-E01"));
    }

    @Test
    @DisplayName("firstElectricRegistration_withNoElectricAircraft_returnsEmptyOptional")
    void firstElectricRegistration_withNoElectricAircraft_returnsEmptyOptional() {
        var madrid = airport("MAD", "Madrid", "Spain",
                aircraft("EC-AAA", REGIONAL, PISTON, 11, 10_000, "40.00"),
                aircraft("EC-BBB", REGIONAL, TURBOPROP, 70, 20_000, "42.00"));

        var result = A04FirstElectricRegistration.firstElectricRegistration(List.of(madrid));

        assertThat(result).isEmpty();
    }
}
