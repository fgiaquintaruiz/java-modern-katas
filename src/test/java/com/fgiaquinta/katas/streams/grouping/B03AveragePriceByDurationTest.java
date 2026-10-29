package com.fgiaquinta.katas.streams.grouping;

import com.fgiaquinta.katas.domain.Airport;
import com.fgiaquinta.katas.domain.Customer;
import com.fgiaquinta.katas.domain.EngineType;
import com.fgiaquinta.katas.domain.Charter;
import com.fgiaquinta.katas.domain.Aircraft;
import com.fgiaquinta.katas.domain.AircraftCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.fgiaquinta.katas.domain.TestData.airport;
import static com.fgiaquinta.katas.domain.TestData.customer;
import static com.fgiaquinta.katas.domain.TestData.charter;
import static com.fgiaquinta.katas.domain.TestData.aircraft;
import static org.assertj.core.api.Assertions.assertThat;

class B03AveragePriceByDurationTest {

    private static final Customer CUSTOMER = customer("C1", "Ana", "Spain", true);
    private static final Aircraft AIRCRAFT = aircraft("EC-P01", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 1000, "30.00");
    private static final Airport AIRPORT = airport("MAD", "Madrid", "Spain", AIRCRAFT);

    @Test
    @DisplayName("partitions charters by minDays and averages totalPrice within each partition")
    void averagePriceByLongCharter_withMixedDurations_averagesEachPartitionSeparately() {
        List<Charter> charters = List.of(
                // days() = end - start, end exclusive
                charter("R1", CUSTOMER, AIRCRAFT, AIRPORT, AIRPORT, "2024-01-01", "2024-01-04", "120.00"), // 3 days
                charter("R2", CUSTOMER, AIRCRAFT, AIRPORT, AIRPORT, "2024-01-01", "2024-01-02", "50.00"),  // 1 day
                charter("R3", CUSTOMER, AIRCRAFT, AIRPORT, AIRPORT, "2024-01-01", "2024-01-06", "200.00"), // 5 days
                charter("R4", CUSTOMER, AIRCRAFT, AIRPORT, AIRPORT, "2024-01-01", "2024-01-03", "80.00")   // 2 days
        );

        Map<Boolean, Double> result = B03AveragePriceByDuration.averagePriceByLongCharter(charters, 3);

        // long (days >= 3): R1 (120.00) + R3 (200.00) = 320.00 / 2 = 160.0
        assertThat(result.get(true)).isEqualTo(160.0);
        // short (days < 3): R2 (50.00) + R4 (80.00) = 130.00 / 2 = 65.0
        assertThat(result.get(false)).isEqualTo(65.0);
    }

    @Test
    @DisplayName("returns both partitions with average 0.0 when the charter list is empty")
    void averagePriceByLongCharter_withEmptyList_returnsZeroAverageForBothPartitions() {
        Map<Boolean, Double> result = B03AveragePriceByDuration.averagePriceByLongCharter(List.of(), 3);

        assertThat(result).containsExactlyInAnyOrderEntriesOf(Map.of(true, 0.0, false, 0.0));
    }
}
