package com.fgiaquinta.katas.streams.flatmap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fgiaquinta.katas.domain.FuelType.DIESEL;
import static com.fgiaquinta.katas.domain.FuelType.PETROL;
import static com.fgiaquinta.katas.domain.VehicleCategory.COMPACT;
import static com.fgiaquinta.katas.domain.VehicleCategory.ECONOMY;
import static com.fgiaquinta.katas.domain.VehicleCategory.LUXURY;
import static com.fgiaquinta.katas.domain.VehicleCategory.SUV;
import static com.fgiaquinta.katas.domain.VehicleCategory.VAN;
import static com.fgiaquinta.katas.domain.TestData.branch;
import static com.fgiaquinta.katas.domain.TestData.customer;
import static com.fgiaquinta.katas.domain.TestData.rental;
import static com.fgiaquinta.katas.domain.TestData.vehicle;
import static org.assertj.core.api.Assertions.assertThat;

class A06UpgradeCandidatesTest {

    @Test
    @DisplayName("upgradeCandidates_withHigherTierVehiclesInPickupFleet_returnsThemInFleetOrderPerRental")
    void upgradeCandidates_withHigherTierVehiclesInPickupFleet_returnsThemInFleetOrderPerRental() {
        var eco = vehicle("ECO1", ECONOMY, PETROL, 5, 1_000, "30.00");
        var compact = vehicle("COM1", COMPACT, PETROL, 5, 1_000, "35.00");
        var suv = vehicle("SUV1", SUV, DIESEL, 5, 1_000, "50.00");
        var van = vehicle("VAN1", VAN, DIESEL, 7, 1_000, "60.00");
        var luxury = vehicle("LUX1", LUXURY, PETROL, 4, 1_000, "90.00");
        var pickup = branch("MAD", "Madrid", "Spain", eco, compact, suv, van, luxury);
        var alice = customer("C1", "Alice", "ES", true);

        // rented ECONOMY: every higher tier in the fleet (COMPACT, SUV, VAN, LUXURY) qualifies.
        var r1 = rental("R1", alice, eco, pickup, pickup, "2026-01-01", "2026-01-03", "60.00");
        // rented SUV: only strictly higher tiers in the fleet (VAN, LUXURY) qualify.
        var r2 = rental("R2", alice, suv, pickup, pickup, "2026-02-01", "2026-02-02", "50.00");

        var result = A06UpgradeCandidates.upgradeCandidates(List.of(r1, r2));

        assertThat(result).containsExactly(
                new A06UpgradeCandidates.Upgrade("R1", "COM1"),
                new A06UpgradeCandidates.Upgrade("R1", "SUV1"),
                new A06UpgradeCandidates.Upgrade("R1", "VAN1"),
                new A06UpgradeCandidates.Upgrade("R1", "LUX1"),
                new A06UpgradeCandidates.Upgrade("R2", "VAN1"),
                new A06UpgradeCandidates.Upgrade("R2", "LUX1"));
    }

    @Test
    @DisplayName("upgradeCandidates_whenRentedVehicleIsAlreadyTopTier_returnsNoCandidates")
    void upgradeCandidates_whenRentedVehicleIsAlreadyTopTier_returnsNoCandidates() {
        var eco = vehicle("ECO1", ECONOMY, PETROL, 5, 1_000, "30.00");
        var luxury = vehicle("LUX1", LUXURY, PETROL, 4, 1_000, "90.00");
        var pickup = branch("MAD", "Madrid", "Spain", eco, luxury);
        var alice = customer("C1", "Alice", "ES", true);
        // rented LUXURY: no vehicle in the fleet outranks it, so no upgrade candidate exists.
        var r1 = rental("R1", alice, luxury, pickup, pickup, "2026-01-01", "2026-01-03", "270.00");

        var result = A06UpgradeCandidates.upgradeCandidates(List.of(r1));

        assertThat(result).isEmpty();
    }
}
