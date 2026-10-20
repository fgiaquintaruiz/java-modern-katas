package com.fgiaquinta.katas.streams.flatmap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.fgiaquinta.katas.domain.FuelType.DIESEL;
import static com.fgiaquinta.katas.domain.FuelType.ELECTRIC;
import static com.fgiaquinta.katas.domain.FuelType.PETROL;
import static com.fgiaquinta.katas.domain.VehicleCategory.ECONOMY;
import static com.fgiaquinta.katas.domain.TestData.branch;
import static com.fgiaquinta.katas.domain.TestData.vehicle;
import static org.assertj.core.api.Assertions.assertThat;

class A04FirstElectricPlateTest {

    @Test
    @DisplayName("firstElectricPlate_withElectricVehicleInSecondBranch_returnsFirstMatchInEncounterOrder")
    void firstElectricPlate_withElectricVehicleInSecondBranch_returnsFirstMatchInEncounterOrder() {
        var madrid = branch("MAD", "Madrid", "Spain",
                vehicle("1111AAA", ECONOMY, PETROL, 5, 10_000, "40.00"),
                vehicle("2222BBB", ECONOMY, DIESEL, 5, 20_000, "42.00"));
        var barcelona = branch("BCN", "Barcelona", "Spain",
                // first electric vehicle encountered when scanning branches then fleet in order
                vehicle("E001", ECONOMY, ELECTRIC, 5, 1_000, "50.00"),
                vehicle("E002", ECONOMY, ELECTRIC, 5, 500, "52.00"));

        var result = A04FirstElectricPlate.firstElectricPlate(List.of(madrid, barcelona));

        assertThat(result).isEqualTo(Optional.of("E001"));
    }

    @Test
    @DisplayName("firstElectricPlate_withNoElectricVehicle_returnsEmptyOptional")
    void firstElectricPlate_withNoElectricVehicle_returnsEmptyOptional() {
        var madrid = branch("MAD", "Madrid", "Spain",
                vehicle("1111AAA", ECONOMY, PETROL, 5, 10_000, "40.00"),
                vehicle("2222BBB", ECONOMY, DIESEL, 5, 20_000, "42.00"));

        var result = A04FirstElectricPlate.firstElectricPlate(List.of(madrid));

        assertThat(result).isEmpty();
    }
}
