package com.fgiaquinta.katas.streams.flatmap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fgiaquinta.katas.domain.FuelType.PETROL;
import static com.fgiaquinta.katas.domain.VehicleCategory.ECONOMY;
import static com.fgiaquinta.katas.domain.TestData.branch;
import static com.fgiaquinta.katas.domain.TestData.vehicle;
import static org.assertj.core.api.Assertions.assertThat;

class A02DistinctFeaturesSortedTest {

    @Test
    @DisplayName("distinctFeaturesSorted_withOverlappingFeatures_returnsDistinctNamesAlphabetically")
    void distinctFeaturesSorted_withOverlappingFeatures_returnsDistinctNamesAlphabetically() {
        var madrid = branch("MAD", "Madrid", "Spain",
                vehicle("1111AAA", ECONOMY, PETROL, 5, 10_000, "40.00", "GPS", "AC"),
                // "AC" repeated across vehicles, must appear only once in the result
                vehicle("2222BBB", ECONOMY, PETROL, 5, 20_000, "42.00", "AC", "BLUETOOTH"));
        var barcelona = branch("BCN", "Barcelona", "Spain",
                vehicle("3333CCC", ECONOMY, PETROL, 5, 5_000, "38.00", "GPS", "USB"));

        var result = A02DistinctFeaturesSorted.distinctFeaturesSorted(List.of(madrid, barcelona));

        // Distinct features across all vehicles: GPS, AC, BLUETOOTH, USB -> sorted alphabetically.
        assertThat(result).containsExactly("AC", "BLUETOOTH", "GPS", "USB");
    }

    @Test
    @DisplayName("distinctFeaturesSorted_whenNoVehicleHasFeatures_returnsEmptyList")
    void distinctFeaturesSorted_whenNoVehicleHasFeatures_returnsEmptyList() {
        var madrid = branch("MAD", "Madrid", "Spain",
                vehicle("1111AAA", ECONOMY, PETROL, 5, 10_000, "40.00"));

        var result = A02DistinctFeaturesSorted.distinctFeaturesSorted(List.of(madrid));

        assertThat(result).isEmpty();
    }
}
