package com.fgiaquinta.katas.functional.methodref;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fgiaquinta.katas.domain.FuelType.DIESEL;
import static com.fgiaquinta.katas.domain.FuelType.PETROL;
import static com.fgiaquinta.katas.domain.TestData.branch;
import static com.fgiaquinta.katas.domain.TestData.vehicle;
import static com.fgiaquinta.katas.domain.VehicleCategory.ECONOMY;
import static com.fgiaquinta.katas.domain.VehicleCategory.SUV;
import static org.assertj.core.api.Assertions.assertThat;

class M06VehiclesInBranchFleetTest {

    @Test
    @DisplayName("inFleet_withMixedCandidates_keepsOnlyFleetVehiclesInInputOrder")
    void inFleet_withMixedCandidates_keepsOnlyFleetVehiclesInInputOrder() {
        // Only suv and eco are in the Madrid fleet; outsider is not.
        var suv = vehicle("1111AAA", SUV, DIESEL, 5, 10_000, "60.00");
        var eco = vehicle("2222BBB", ECONOMY, PETROL, 5, 5_000, "30.00");
        var outsider = vehicle("3333CCC", ECONOMY, PETROL, 5, 7_000, "45.50");
        var madrid = branch("MAD", "Madrid", "Spain", suv, eco);

        assertThat(M06VehiclesInBranchFleet.inFleet(List.of(eco, outsider, suv), madrid))
                .containsExactly(eco, suv);
    }

    @Test
    @DisplayName("inFleet_withEmptyFleet_returnsEmptyList")
    void inFleet_withEmptyFleet_returnsEmptyList() {
        var suv = vehicle("1111AAA", SUV, DIESEL, 5, 10_000, "60.00");
        var empty = branch("MAD", "Madrid", "Spain");

        assertThat(M06VehiclesInBranchFleet.inFleet(List.of(suv), empty)).isEmpty();
    }
}
