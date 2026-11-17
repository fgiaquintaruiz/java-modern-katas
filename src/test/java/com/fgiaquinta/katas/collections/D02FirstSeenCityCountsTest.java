package com.fgiaquinta.katas.collections;

import com.fgiaquinta.katas.domain.Aircraft;
import com.fgiaquinta.katas.domain.AircraftCategory;
import com.fgiaquinta.katas.domain.Airport;
import com.fgiaquinta.katas.domain.EngineType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.fgiaquinta.katas.domain.TestData.aircraft;
import static com.fgiaquinta.katas.domain.TestData.airport;
import static org.assertj.core.api.Assertions.assertThat;

class D02FirstSeenCityCountsTest {

    private final Aircraft a1 = aircraft("EC-001", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 1000, "30.00");
    private final Aircraft a2 = aircraft("EC-002", AircraftCategory.NARROW_BODY, EngineType.TURBOFAN, 180, 1200, "35.00");
    private final Aircraft a3 = aircraft("EC-003", AircraftCategory.WIDE_BODY, EngineType.TURBOFAN, 300, 2000, "60.00");

    @Test
    @DisplayName("keeps the cities in first-seen order, not alphabetical and not hash order")
    void aircraftCountByCity_withUnsortedCities_keepsFirstSeenOrder() {
        List<Airport> airports = List.of(
                airport("SEV", "Sevilla", "Spain", a1),
                airport("MAD", "Madrid", "Spain", a2, a3),
                airport("BCN", "Barcelona", "Spain", a1));

        Map<String, Integer> result = D02FirstSeenCityCounts.aircraftCountByCity(airports);

        assertThat(result).containsExactly(
                Map.entry("Sevilla", 1), Map.entry("Madrid", 2), Map.entry("Barcelona", 1));
    }

    @Test
    @DisplayName("sums the fleets of airports sharing a city and keeps the position of the first one")
    void aircraftCountByCity_withRepeatedCity_mergesCountsAtFirstPosition() {
        List<Airport> airports = List.of(
                airport("MAD", "Madrid", "Spain", a1),
                airport("SEV", "Sevilla", "Spain", a2),
                airport("TOJ", "Madrid", "Spain", a2, a3));

        Map<String, Integer> result = D02FirstSeenCityCounts.aircraftCountByCity(airports);

        assertThat(result).containsExactly(Map.entry("Madrid", 3), Map.entry("Sevilla", 1));
    }

    @Test
    @DisplayName("keeps a city with an empty fleet with a zero count")
    void aircraftCountByCity_withEmptyFleet_keepsCityWithZero() {
        List<Airport> airports = List.of(airport("VLC", "Valencia", "Spain"));

        assertThat(D02FirstSeenCityCounts.aircraftCountByCity(airports)).containsExactly(Map.entry("Valencia", 0));
    }

    @Test
    @DisplayName("returns an empty map for an empty list")
    void aircraftCountByCity_withEmptyList_returnsEmptyMap() {
        assertThat(D02FirstSeenCityCounts.aircraftCountByCity(List.of())).isEmpty();
    }
}
