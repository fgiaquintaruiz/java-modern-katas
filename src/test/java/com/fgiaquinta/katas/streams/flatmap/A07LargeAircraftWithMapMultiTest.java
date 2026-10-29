package com.fgiaquinta.katas.streams.flatmap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fgiaquinta.katas.domain.EngineType.TURBOFAN;
import static com.fgiaquinta.katas.domain.EngineType.TURBOPROP;
import static com.fgiaquinta.katas.domain.EngineType.PISTON;
import static com.fgiaquinta.katas.domain.AircraftCategory.REGIONAL;
import static com.fgiaquinta.katas.domain.AircraftCategory.WIDE_BODY;
import static com.fgiaquinta.katas.domain.AircraftCategory.CARGO;
import static com.fgiaquinta.katas.domain.TestData.airport;
import static com.fgiaquinta.katas.domain.TestData.aircraft;
import static org.assertj.core.api.Assertions.assertThat;

class A07LargeAircraftWithMapMultiTest {

    @Test
    @DisplayName("largeAircraft_withMixedSeatCounts_returnsOnlyAircraftMeetingMinSeatsInEncounterOrder")
    void largeAircraft_withMixedSeatCounts_returnsOnlyAircraftMeetingMinSeatsInEncounterOrder() {
        var regional = aircraft("EC-AAA", REGIONAL, PISTON, 4, 10_000, "30.00");
        var sevenSeats = aircraft("EC-BBB", WIDE_BODY, TURBOFAN, 7, 20_000, "55.00");
        var fiveSeats = aircraft("EC-CCC", REGIONAL, PISTON, 5, 5_000, "35.00");
        var nineSeats = aircraft("EC-DDD", CARGO, TURBOPROP, 9, 1_000, "70.00");
        var madrid = airport("MAD", "Madrid", "Spain", regional, sevenSeats);
        var barcelona = airport("BCN", "Barcelona", "Spain", fiveSeats, nineSeats);

        var result = A07LargeAircraftWithMapMulti.largeAircraft(List.of(madrid, barcelona), 5);

        // seats >= 5: EC-AAA (4) excluded; EC-BBB (7), EC-CCC (5), EC-DDD (9) included, in encounter order.
        assertThat(result).containsExactly(sevenSeats, fiveSeats, nineSeats);
    }

    @Test
    @DisplayName("largeAircraft_whenMinSeatsExceedsEveryAircraft_returnsEmptyList")
    void largeAircraft_whenMinSeatsExceedsEveryAircraft_returnsEmptyList() {
        var regional = aircraft("EC-AAA", REGIONAL, PISTON, 4, 10_000, "30.00");
        var madrid = airport("MAD", "Madrid", "Spain", regional);

        var result = A07LargeAircraftWithMapMulti.largeAircraft(List.of(madrid), 100);

        assertThat(result).isEmpty();
    }
}
