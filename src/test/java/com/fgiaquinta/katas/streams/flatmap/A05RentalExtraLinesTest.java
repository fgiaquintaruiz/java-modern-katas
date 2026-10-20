package com.fgiaquinta.katas.streams.flatmap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fgiaquinta.katas.domain.FuelType.PETROL;
import static com.fgiaquinta.katas.domain.VehicleCategory.ECONOMY;
import static com.fgiaquinta.katas.domain.TestData.branch;
import static com.fgiaquinta.katas.domain.TestData.customer;
import static com.fgiaquinta.katas.domain.TestData.extra;
import static com.fgiaquinta.katas.domain.TestData.rental;
import static com.fgiaquinta.katas.domain.TestData.vehicle;
import static org.assertj.core.api.Assertions.assertThat;

class A05RentalExtraLinesTest {

    @Test
    @DisplayName("extraLines_withSomeRentalsLackingExtras_returnsOnlyLinesForRentalsWithExtrasInOrder")
    void extraLines_withSomeRentalsLackingExtras_returnsOnlyLinesForRentalsWithExtrasInOrder() {
        var alice = customer("C1", "Alice", "ES", true);
        var vehicle = vehicle("1111AAA", ECONOMY, PETROL, 5, 10_000, "40.00");
        var madrid = branch("MAD", "Madrid", "Spain", vehicle);

        var r1 = rental("R1", alice, vehicle, madrid, madrid, "2026-01-01", "2026-01-04", "150.00",
                extra("GPS", "10.00"), extra("CHILD_SEAT", "5.00"));
        // no extras: must not contribute any line
        var r2 = rental("R2", alice, vehicle, madrid, madrid, "2026-02-01", "2026-02-03", "80.00");
        var r3 = rental("R3", alice, vehicle, madrid, madrid, "2026-03-01", "2026-03-02", "40.00",
                extra("INSURANCE", "20.00"));

        var result = A05RentalExtraLines.extraLines(List.of(r1, r2, r3));

        // Rental order first, then extras order within each rental; R2 contributes nothing.
        assertThat(result).containsExactly("R1:GPS", "R1:CHILD_SEAT", "R3:INSURANCE");
    }

    @Test
    @DisplayName("extraLines_whenNoRentalHasExtras_returnsEmptyList")
    void extraLines_whenNoRentalHasExtras_returnsEmptyList() {
        var alice = customer("C1", "Alice", "ES", true);
        var vehicle = vehicle("1111AAA", ECONOMY, PETROL, 5, 10_000, "40.00");
        var madrid = branch("MAD", "Madrid", "Spain", vehicle);
        var r1 = rental("R1", alice, vehicle, madrid, madrid, "2026-01-01", "2026-01-04", "150.00");

        var result = A05RentalExtraLines.extraLines(List.of(r1));

        assertThat(result).isEmpty();
    }
}
