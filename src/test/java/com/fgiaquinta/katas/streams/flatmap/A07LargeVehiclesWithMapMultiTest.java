package com.fgiaquinta.katas.streams.flatmap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fgiaquinta.katas.domain.FuelType.DIESEL;
import static com.fgiaquinta.katas.domain.FuelType.PETROL;
import static com.fgiaquinta.katas.domain.VehicleCategory.ECONOMY;
import static com.fgiaquinta.katas.domain.VehicleCategory.SUV;
import static com.fgiaquinta.katas.domain.VehicleCategory.VAN;
import static com.fgiaquinta.katas.domain.TestData.branch;
import static com.fgiaquinta.katas.domain.TestData.vehicle;
import static org.assertj.core.api.Assertions.assertThat;

class A07LargeVehiclesWithMapMultiTest {

    @Test
    @DisplayName("largeVehicles_withMixedSeatCounts_returnsOnlyVehiclesMeetingMinSeatsInEncounterOrder")
    void largeVehicles_withMixedSeatCounts_returnsOnlyVehiclesMeetingMinSeatsInEncounterOrder() {
        var small = vehicle("1111AAA", ECONOMY, PETROL, 4, 10_000, "30.00");
        var sevenSeater = vehicle("2222BBB", SUV, DIESEL, 7, 20_000, "55.00");
        var fiveSeater = vehicle("3333CCC", ECONOMY, PETROL, 5, 5_000, "35.00");
        var nineSeater = vehicle("4444DDD", VAN, DIESEL, 9, 1_000, "70.00");
        var madrid = branch("MAD", "Madrid", "Spain", small, sevenSeater);
        var barcelona = branch("BCN", "Barcelona", "Spain", fiveSeater, nineSeater);

        var result = A07LargeVehiclesWithMapMulti.largeVehicles(List.of(madrid, barcelona), 5);

        // seats >= 5: 1111AAA (4) excluded; 2222BBB (7), 3333CCC (5), 4444DDD (9) included, in encounter order.
        assertThat(result).containsExactly(sevenSeater, fiveSeater, nineSeater);
    }

    @Test
    @DisplayName("largeVehicles_whenMinSeatsExceedsEveryVehicle_returnsEmptyList")
    void largeVehicles_whenMinSeatsExceedsEveryVehicle_returnsEmptyList() {
        var small = vehicle("1111AAA", ECONOMY, PETROL, 4, 10_000, "30.00");
        var madrid = branch("MAD", "Madrid", "Spain", small);

        var result = A07LargeVehiclesWithMapMulti.largeVehicles(List.of(madrid), 100);

        assertThat(result).isEmpty();
    }
}
