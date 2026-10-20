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
import java.util.LongSummaryStatistics;

import static com.fgiaquinta.katas.domain.TestData.branch;
import static com.fgiaquinta.katas.domain.TestData.customer;
import static com.fgiaquinta.katas.domain.TestData.rental;
import static com.fgiaquinta.katas.domain.TestData.vehicle;
import static org.assertj.core.api.Assertions.assertThat;

class C02RentalDurationStatsTest {

    @Test
    @DisplayName("computes count, sum, min, average and max of rental days in one pass")
    void durationStats_withMultipleRentals_computesAllStatistics() {
        Customer customer = customer("C1", "Alice", "ES", true);
        Vehicle vehicle = vehicle("AA111", VehicleCategory.ECONOMY, FuelType.PETROL, 4, 10_000, "35.00");
        Branch branch = branch("MAD", "Madrid", "ES");

        List<Rental> rentals = List.of(
                // 2026-01-01 to 2026-01-04, end exclusive -> 3 days
                rental("R1", customer, vehicle, branch, branch, "2026-01-01", "2026-01-04", "100.00"),
                // 2026-02-01 to 2026-02-06, end exclusive -> 5 days
                rental("R2", customer, vehicle, branch, branch, "2026-02-01", "2026-02-06", "150.00"),
                // 2026-03-01 to 2026-03-03, end exclusive -> 2 days
                rental("R3", customer, vehicle, branch, branch, "2026-03-01", "2026-03-03", "80.00"),
                // 2026-04-01 to 2026-04-06, end exclusive -> 5 days
                rental("R4", customer, vehicle, branch, branch, "2026-04-01", "2026-04-06", "150.00")
        );

        LongSummaryStatistics stats = C02RentalDurationStats.durationStats(rentals);

        // days: 3, 5, 2, 5 -> count 4, sum 15, min 2, max 5, average 15/4 = 3.75
        assertThat(stats.getCount()).isEqualTo(4L);
        assertThat(stats.getSum()).isEqualTo(15L);
        assertThat(stats.getMin()).isEqualTo(2L);
        assertThat(stats.getMax()).isEqualTo(5L);
        assertThat(stats.getAverage()).isEqualTo(3.75);
    }

    @Test
    @DisplayName("returns the JDK sentinel values for an empty rental list")
    void durationStats_withEmptyList_returnsJdkSentinelValues() {
        LongSummaryStatistics stats = C02RentalDurationStats.durationStats(List.of());

        assertThat(stats.getCount()).isZero();
        assertThat(stats.getSum()).isZero();
        assertThat(stats.getMin()).isEqualTo(Long.MAX_VALUE);
        assertThat(stats.getMax()).isEqualTo(Long.MIN_VALUE);
        assertThat(stats.getAverage()).isEqualTo(0.0);
    }
}
