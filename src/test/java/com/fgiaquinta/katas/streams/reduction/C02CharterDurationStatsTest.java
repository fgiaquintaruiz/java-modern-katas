package com.fgiaquinta.katas.streams.reduction;

import com.fgiaquinta.katas.domain.Airport;
import com.fgiaquinta.katas.domain.Customer;
import com.fgiaquinta.katas.domain.EngineType;
import com.fgiaquinta.katas.domain.Charter;
import com.fgiaquinta.katas.domain.Aircraft;
import com.fgiaquinta.katas.domain.AircraftCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.LongSummaryStatistics;

import static com.fgiaquinta.katas.domain.TestData.airport;
import static com.fgiaquinta.katas.domain.TestData.customer;
import static com.fgiaquinta.katas.domain.TestData.charter;
import static com.fgiaquinta.katas.domain.TestData.aircraft;
import static org.assertj.core.api.Assertions.assertThat;

class C02CharterDurationStatsTest {

    @Test
    @DisplayName("computes count, sum, min, average and max of charter days in one pass")
    void durationStats_withMultipleCharters_computesAllStatistics() {
        Customer customer = customer("C1", "Alice", "ES", true);
        Aircraft aircraft = aircraft("EC-AAA", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 10_000, "35.00");
        Airport airport = airport("MAD", "Madrid", "ES");

        List<Charter> charters = List.of(
                // 2026-01-01 to 2026-01-04, end exclusive -> 3 days
                charter("R1", customer, aircraft, airport, airport, "2026-01-01", "2026-01-04", "100.00"),
                // 2026-02-01 to 2026-02-06, end exclusive -> 5 days
                charter("R2", customer, aircraft, airport, airport, "2026-02-01", "2026-02-06", "150.00"),
                // 2026-03-01 to 2026-03-03, end exclusive -> 2 days
                charter("R3", customer, aircraft, airport, airport, "2026-03-01", "2026-03-03", "80.00"),
                // 2026-04-01 to 2026-04-06, end exclusive -> 5 days
                charter("R4", customer, aircraft, airport, airport, "2026-04-01", "2026-04-06", "150.00")
        );

        LongSummaryStatistics stats = C02CharterDurationStats.durationStats(charters);

        // days: 3, 5, 2, 5 -> count 4, sum 15, min 2, max 5, average 15/4 = 3.75
        assertThat(stats.getCount()).isEqualTo(4L);
        assertThat(stats.getSum()).isEqualTo(15L);
        assertThat(stats.getMin()).isEqualTo(2L);
        assertThat(stats.getMax()).isEqualTo(5L);
        assertThat(stats.getAverage()).isEqualTo(3.75);
    }

    @Test
    @DisplayName("returns the JDK sentinel values for an empty charter list")
    void durationStats_withEmptyList_returnsJdkSentinelValues() {
        LongSummaryStatistics stats = C02CharterDurationStats.durationStats(List.of());

        assertThat(stats.getCount()).isZero();
        assertThat(stats.getSum()).isZero();
        assertThat(stats.getMin()).isEqualTo(Long.MAX_VALUE);
        assertThat(stats.getMax()).isEqualTo(Long.MIN_VALUE);
        assertThat(stats.getAverage()).isEqualTo(0.0);
    }
}
