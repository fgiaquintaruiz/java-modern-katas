package com.fgiaquinta.katas.streams.flatmap;

import com.fgiaquinta.katas.domain.Branch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fgiaquinta.katas.domain.FuelType.PETROL;
import static com.fgiaquinta.katas.domain.VehicleCategory.ECONOMY;
import static com.fgiaquinta.katas.domain.TestData.branch;
import static com.fgiaquinta.katas.domain.TestData.vehicle;
import static org.assertj.core.api.Assertions.assertThat;

class A01AllPlatesTest {

    @Test
    @DisplayName("allPlates_withMultipleBranches_returnsPlatesInBranchThenFleetOrder")
    void allPlates_withMultipleBranches_returnsPlatesInBranchThenFleetOrder() {
        var madrid = branch("MAD", "Madrid", "Spain",
                vehicle("1111AAA", ECONOMY, PETROL, 5, 10_000, "40.00"),
                vehicle("2222BBB", ECONOMY, PETROL, 5, 20_000, "42.00"));
        var barcelona = branch("BCN", "Barcelona", "Spain");
        var valencia = branch("VAL", "Valencia", "Spain",
                // same plate as Madrid's first vehicle: duplicates must be preserved
                vehicle("1111AAA", ECONOMY, PETROL, 5, 5_000, "38.00"),
                vehicle("3333CCC", ECONOMY, PETROL, 5, 1_000, "45.00"));

        var result = A01AllPlates.allPlates(List.of(madrid, barcelona, valencia));

        // Madrid's fleet plates, then Barcelona (empty, contributes nothing), then Valencia's fleet plates.
        assertThat(result).containsExactly("1111AAA", "2222BBB", "1111AAA", "3333CCC");
    }

    @Test
    @DisplayName("allPlates_withEmptyBranchList_returnsEmptyList")
    void allPlates_withEmptyBranchList_returnsEmptyList() {
        var result = A01AllPlates.allPlates(List.of());

        assertThat(result).isEmpty();
    }
}
