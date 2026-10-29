package com.fgiaquinta.katas.functional.methodref;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fgiaquinta.katas.domain.EngineType.TURBOFAN;
import static com.fgiaquinta.katas.domain.EngineType.PISTON;
import static com.fgiaquinta.katas.domain.TestData.aircraft;
import static com.fgiaquinta.katas.domain.AircraftCategory.REGIONAL;
import static com.fgiaquinta.katas.domain.AircraftCategory.WIDE_BODY;
import static org.assertj.core.api.Assertions.assertThat;

class M04AircraftByDailyRateTest {

    @Test
    @DisplayName("sortedByDailyRate_withUnsortedAircraft_returnsAscendingByRate")
    void sortedByDailyRate_withUnsortedAircraft_returnsAscendingByRate() {
        // 30.00 < 45.50 < 60.00.
        var wideBody = aircraft("EC-AAA", WIDE_BODY, TURBOFAN, 300, 10_000, "60.00");
        var regional = aircraft("EC-BBB", REGIONAL, PISTON, 11, 5_000, "30.00");
        var mid = aircraft("EC-CCC", REGIONAL, PISTON, 11, 7_000, "45.50");

        assertThat(M04AircraftByDailyRate.sortedByDailyRate(List.of(wideBody, regional, mid)))
                .containsExactly(regional, mid, wideBody);
    }

    @Test
    @DisplayName("sortedByDailyRate_doesNotModifyInput")
    void sortedByDailyRate_doesNotModifyInput() {
        var wideBody = aircraft("EC-AAA", WIDE_BODY, TURBOFAN, 300, 10_000, "60.00");
        var regional = aircraft("EC-BBB", REGIONAL, PISTON, 11, 5_000, "30.00");
        var input = List.of(wideBody, regional);

        M04AircraftByDailyRate.sortedByDailyRate(input);

        assertThat(input).containsExactly(wideBody, regional);
    }

    @Test
    @DisplayName("sortedByDailyRate_withEmptyList_returnsEmptyList")
    void sortedByDailyRate_withEmptyList_returnsEmptyList() {
        assertThat(M04AircraftByDailyRate.sortedByDailyRate(List.of())).isEmpty();
    }
}
