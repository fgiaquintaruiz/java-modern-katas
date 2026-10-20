package com.fgiaquinta.katas.streams.grouping;

import com.fgiaquinta.katas.domain.Branch;
import com.fgiaquinta.katas.domain.FuelType;
import com.fgiaquinta.katas.domain.VehicleCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.fgiaquinta.katas.domain.TestData.branch;
import static com.fgiaquinta.katas.domain.TestData.vehicle;
import static org.assertj.core.api.Assertions.assertThat;

class B02PlatesByCityTest {

    @Test
    @DisplayName("merges plates of same-city branches in branch order, then fleet order")
    void platesByCity_withTwoBranchesInSameCity_mergesPlatesInEncounterOrder() {
        Branch madrid1 = branch("MAD1", "Madrid", "Spain",
                vehicle("P1", VehicleCategory.ECONOMY, FuelType.PETROL, 4, 1000, "30.00"),
                vehicle("P2", VehicleCategory.COMPACT, FuelType.PETROL, 5, 1200, "35.00"));
        Branch barcelona = branch("BCN", "Barcelona", "Spain",
                vehicle("P3", VehicleCategory.SUV, FuelType.DIESEL, 5, 2000, "55.00"));
        Branch madrid2 = branch("MAD2", "Madrid", "Spain",
                vehicle("P4", VehicleCategory.VAN, FuelType.DIESEL, 7, 500, "70.00"));

        Map<String, List<String>> result = B02PlatesByCity.platesByCity(List.of(madrid1, barcelona, madrid2));

        // Madrid: P1, P2 (from madrid1), then P4 (from madrid2) -> encounter order preserved.
        assertThat(result.get("Madrid")).containsExactly("P1", "P2", "P4");
        // Barcelona: P3 only.
        assertThat(result.get("Barcelona")).containsExactly("P3");
        assertThat(result).hasSize(2);
    }

    @Test
    @DisplayName("omits a city whose only branch has an empty fleet")
    void platesByCity_withBranchWithoutVehicles_returnsNoEntryForThatCity() {
        Branch emptyBranch = branch("VAL", "Valencia", "Spain");
        Branch madrid = branch("MAD", "Madrid", "Spain",
                vehicle("P1", VehicleCategory.ECONOMY, FuelType.PETROL, 4, 1000, "30.00"));

        Map<String, List<String>> result = B02PlatesByCity.platesByCity(List.of(emptyBranch, madrid));

        assertThat(result).containsOnlyKeys("Madrid");
        assertThat(result.get("Madrid")).containsExactly("P1");
    }
}
