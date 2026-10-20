package com.fgiaquinta.katas.functional.methodref;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fgiaquinta.katas.domain.FuelType.DIESEL;
import static com.fgiaquinta.katas.domain.FuelType.PETROL;
import static com.fgiaquinta.katas.domain.TestData.vehicle;
import static com.fgiaquinta.katas.domain.VehicleCategory.ECONOMY;
import static com.fgiaquinta.katas.domain.VehicleCategory.SUV;
import static org.assertj.core.api.Assertions.assertThat;

class M04VehiclesByDailyRateTest {

    @Test
    @DisplayName("sortedByDailyRate_withUnsortedVehicles_returnsAscendingByRate")
    void sortedByDailyRate_withUnsortedVehicles_returnsAscendingByRate() {
        // 30.00 < 45.50 < 60.00.
        var suv = vehicle("1111AAA", SUV, DIESEL, 5, 10_000, "60.00");
        var eco = vehicle("2222BBB", ECONOMY, PETROL, 5, 5_000, "30.00");
        var mid = vehicle("3333CCC", ECONOMY, PETROL, 5, 7_000, "45.50");

        assertThat(M04VehiclesByDailyRate.sortedByDailyRate(List.of(suv, eco, mid)))
                .containsExactly(eco, mid, suv);
    }

    @Test
    @DisplayName("sortedByDailyRate_doesNotModifyInput")
    void sortedByDailyRate_doesNotModifyInput() {
        var suv = vehicle("1111AAA", SUV, DIESEL, 5, 10_000, "60.00");
        var eco = vehicle("2222BBB", ECONOMY, PETROL, 5, 5_000, "30.00");
        var input = List.of(suv, eco);

        M04VehiclesByDailyRate.sortedByDailyRate(input);

        assertThat(input).containsExactly(suv, eco);
    }

    @Test
    @DisplayName("sortedByDailyRate_withEmptyList_returnsEmptyList")
    void sortedByDailyRate_withEmptyList_returnsEmptyList() {
        assertThat(M04VehiclesByDailyRate.sortedByDailyRate(List.of())).isEmpty();
    }
}
