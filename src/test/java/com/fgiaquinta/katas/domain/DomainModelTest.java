package com.fgiaquinta.katas.domain;

import org.junit.jupiter.api.Test;

import static com.fgiaquinta.katas.domain.TestData.branch;
import static com.fgiaquinta.katas.domain.TestData.customer;
import static com.fgiaquinta.katas.domain.TestData.rental;
import static com.fgiaquinta.katas.domain.TestData.vehicle;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

/**
 * Sanity test for the shared domain model. Verifies the invariants that
 * every kata relies on: {@link Rental#days()} computes an end-exclusive
 * duration, and a {@link Rental} cannot be built with an end date that is
 * not strictly after its start date.
 */
class DomainModelTest {

    @Test
    void days_forThreeDayRental_returnsThree() {
        var pickupBranch = branch("MAD", "Madrid", "ES",
                vehicle("1234ABC", VehicleCategory.ECONOMY, FuelType.PETROL, 4, 15000, "35.00", "AC"));
        var customer = customer("C1", "Ada Lovelace", "ES", true);
        var rentedVehicle = pickupBranch.fleet().get(0);

        var rental = rental("R1", customer, rentedVehicle, pickupBranch, pickupBranch,
                "2026-10-01", "2026-10-04", "105.00");

        assertThat(rental.days()).isEqualTo(3L);
    }

    @Test
    void constructor_whenEndIsBeforeStart_throwsIllegalArgumentException() {
        var pickupBranch = branch("MAD", "Madrid", "ES",
                vehicle("1234ABC", VehicleCategory.ECONOMY, FuelType.PETROL, 4, 15000, "35.00", "AC"));
        var customer = customer("C1", "Ada Lovelace", "ES", true);
        var rentedVehicle = pickupBranch.fleet().get(0);

        assertThatIllegalArgumentException().isThrownBy(() ->
                rental("R1", customer, rentedVehicle, pickupBranch, pickupBranch,
                        "2026-10-04", "2026-10-01", "105.00"));
    }
}
