package com.fgiaquinta.katas.streams.grouping;

import com.fgiaquinta.katas.domain.Airport;
import com.fgiaquinta.katas.domain.EngineType;
import com.fgiaquinta.katas.domain.AircraftCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.fgiaquinta.katas.domain.TestData.airport;
import static com.fgiaquinta.katas.domain.TestData.aircraft;
import static org.assertj.core.api.Assertions.assertThat;

class B02RegistrationsByCityTest {

    @Test
    @DisplayName("merges registrations of same-city airports in airport order, then fleet order")
    void registrationsByCity_withTwoAirportsInSameCity_mergesRegistrationsInEncounterOrder() {
        Airport madrid1 = airport("MAD1", "Madrid", "Spain",
                aircraft("EC-P01", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 1000, "30.00"),
                aircraft("EC-P02", AircraftCategory.NARROW_BODY, EngineType.TURBOFAN, 180, 1200, "35.00"));
        Airport barcelona = airport("BCN", "Barcelona", "Spain",
                aircraft("EC-P03", AircraftCategory.WIDE_BODY, EngineType.TURBOFAN, 300, 2000, "55.00"));
        Airport madrid2 = airport("MAD2", "Madrid", "Spain",
                aircraft("EC-P04", AircraftCategory.CARGO, EngineType.TURBOPROP, 0, 500, "70.00"));

        Map<String, List<String>> result = B02RegistrationsByCity.registrationsByCity(List.of(madrid1, barcelona, madrid2));

        // Madrid: EC-P01, EC-P02 (from madrid1), then EC-P04 (from madrid2) -> encounter order preserved.
        assertThat(result.get("Madrid")).containsExactly("EC-P01", "EC-P02", "EC-P04");
        // Barcelona: EC-P03 only.
        assertThat(result.get("Barcelona")).containsExactly("EC-P03");
        assertThat(result).hasSize(2);
    }

    @Test
    @DisplayName("omits a city whose only airport has an empty fleet")
    void registrationsByCity_withAirportWithoutAircraft_returnsNoEntryForThatCity() {
        Airport emptyAirport = airport("VAL", "Valencia", "Spain");
        Airport madrid = airport("MAD", "Madrid", "Spain",
                aircraft("EC-P01", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 1000, "30.00"));

        Map<String, List<String>> result = B02RegistrationsByCity.registrationsByCity(List.of(emptyAirport, madrid));

        assertThat(result).containsOnlyKeys("Madrid");
        assertThat(result.get("Madrid")).containsExactly("EC-P01");
    }
}
