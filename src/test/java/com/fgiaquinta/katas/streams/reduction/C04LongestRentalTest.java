package com.fgiaquinta.katas.streams.reduction;

import com.fgiaquinta.katas.domain.Branch;
import com.fgiaquinta.katas.domain.Customer;
import com.fgiaquinta.katas.domain.FuelType;
import com.fgiaquinta.katas.domain.Rental;
import com.fgiaquinta.katas.domain.Vehicle;
import com.fgiaquinta.katas.domain.VehicleCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.fgiaquinta.katas.domain.TestData.branch;
import static com.fgiaquinta.katas.domain.TestData.customer;
import static com.fgiaquinta.katas.domain.TestData.rental;
import static com.fgiaquinta.katas.domain.TestData.vehicle;
import static org.assertj.core.api.Assertions.assertThat;

class C04LongestRentalTest {

    private static final Customer CUSTOMER = customer("C1", "Alice", "ES", true);
    private static final Vehicle VEHICLE =
            vehicle("AA111", VehicleCategory.ECONOMY, FuelType.PETROL, 4, 10_000, "35.00");
    private static final Branch BRANCH = branch("MAD", "Madrid", "ES");

    @Test
    @DisplayName("returns the rental with the greatest number of days")
    void longestRental_withDistinctDurations_returnsTheLongestOne() {
        // 2026-01-01 to 2026-01-03 -> 2 days
        Rental shortest = rental("R1", CUSTOMER, VEHICLE, BRANCH, BRANCH, "2026-01-01", "2026-01-03", "60.00");
        // 2026-02-01 to 2026-02-05 -> 4 days (the longest)
        Rental longest = rental("R2", CUSTOMER, VEHICLE, BRANCH, BRANCH, "2026-02-01", "2026-02-05", "140.00");
        // 2026-03-01 to 2026-03-04 -> 3 days
        Rental middle = rental("R3", CUSTOMER, VEHICLE, BRANCH, BRANCH, "2026-03-01", "2026-03-04", "90.00");

        Optional<Rental> result = C04LongestRental.longestRental(List.of(shortest, longest, middle));

        assertThat(result).contains(longest);
    }

    @Test
    @DisplayName("keeps the first rental in encounter order when durations tie")
    void longestRental_withTiedDurations_returnsTheFirstEncountered() {
        // 2026-01-01 to 2026-01-06 -> 5 days
        Rental first = rental("R1", CUSTOMER, VEHICLE, BRANCH, BRANCH, "2026-01-01", "2026-01-06", "150.00");
        // 2026-02-01 to 2026-02-06 -> 5 days, same duration as "first"
        Rental second = rental("R2", CUSTOMER, VEHICLE, BRANCH, BRANCH, "2026-02-01", "2026-02-06", "150.00");

        Optional<Rental> result = C04LongestRental.longestRental(List.of(first, second));

        assertThat(result).contains(first);
    }

    @Test
    @DisplayName("returns an empty Optional for an empty rental list")
    void longestRental_withEmptyList_returnsEmptyOptional() {
        Optional<Rental> result = C04LongestRental.longestRental(List.of());

        assertThat(result).isEmpty();
    }
}
