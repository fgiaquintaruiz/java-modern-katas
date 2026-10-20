package com.fgiaquinta.katas.streams.grouping;

import com.fgiaquinta.katas.domain.Branch;
import com.fgiaquinta.katas.domain.Customer;
import com.fgiaquinta.katas.domain.FuelType;
import com.fgiaquinta.katas.domain.Rental;
import com.fgiaquinta.katas.domain.Vehicle;
import com.fgiaquinta.katas.domain.VehicleCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.fgiaquinta.katas.domain.TestData.branch;
import static com.fgiaquinta.katas.domain.TestData.customer;
import static com.fgiaquinta.katas.domain.TestData.rental;
import static com.fgiaquinta.katas.domain.TestData.vehicle;
import static org.assertj.core.api.Assertions.assertThat;

class B03AveragePriceByDurationTest {

    private static final Customer CUSTOMER = customer("C1", "Ana", "Spain", true);
    private static final Vehicle VEHICLE = vehicle("P1", VehicleCategory.ECONOMY, FuelType.PETROL, 4, 1000, "30.00");
    private static final Branch BRANCH = branch("MAD", "Madrid", "Spain", VEHICLE);

    @Test
    @DisplayName("partitions rentals by minDays and averages totalPrice within each partition")
    void averagePriceByLongRental_withMixedDurations_averagesEachPartitionSeparately() {
        List<Rental> rentals = List.of(
                // days() = end - start, end exclusive
                rental("R1", CUSTOMER, VEHICLE, BRANCH, BRANCH, "2024-01-01", "2024-01-04", "120.00"), // 3 days
                rental("R2", CUSTOMER, VEHICLE, BRANCH, BRANCH, "2024-01-01", "2024-01-02", "50.00"),  // 1 day
                rental("R3", CUSTOMER, VEHICLE, BRANCH, BRANCH, "2024-01-01", "2024-01-06", "200.00"), // 5 days
                rental("R4", CUSTOMER, VEHICLE, BRANCH, BRANCH, "2024-01-01", "2024-01-03", "80.00")   // 2 days
        );

        Map<Boolean, Double> result = B03AveragePriceByDuration.averagePriceByLongRental(rentals, 3);

        // long (days >= 3): R1 (120.00) + R3 (200.00) = 320.00 / 2 = 160.0
        assertThat(result.get(true)).isEqualTo(160.0);
        // short (days < 3): R2 (50.00) + R4 (80.00) = 130.00 / 2 = 65.0
        assertThat(result.get(false)).isEqualTo(65.0);
    }

    @Test
    @DisplayName("returns both partitions with average 0.0 when the rental list is empty")
    void averagePriceByLongRental_withEmptyList_returnsZeroAverageForBothPartitions() {
        Map<Boolean, Double> result = B03AveragePriceByDuration.averagePriceByLongRental(List.of(), 3);

        assertThat(result).containsExactlyInAnyOrderEntriesOf(Map.of(true, 0.0, false, 0.0));
    }
}
