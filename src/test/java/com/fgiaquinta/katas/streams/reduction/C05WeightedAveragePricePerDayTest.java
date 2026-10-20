package com.fgiaquinta.katas.streams.reduction;

import com.fgiaquinta.katas.domain.Branch;
import com.fgiaquinta.katas.domain.Customer;
import com.fgiaquinta.katas.domain.FuelType;
import com.fgiaquinta.katas.domain.Rental;
import com.fgiaquinta.katas.domain.Vehicle;
import com.fgiaquinta.katas.domain.VehicleCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.fgiaquinta.katas.domain.TestData.branch;
import static com.fgiaquinta.katas.domain.TestData.customer;
import static com.fgiaquinta.katas.domain.TestData.money;
import static com.fgiaquinta.katas.domain.TestData.rental;
import static com.fgiaquinta.katas.domain.TestData.vehicle;
import static org.assertj.core.api.Assertions.assertThat;

class C05WeightedAveragePricePerDayTest {

    @Test
    @DisplayName("computes the weighted average price per day with HALF_UP rounding to scale 2")
    void weightedAveragePricePerDay_withNonExactDivision_roundsHalfUpToScale2() {
        Customer customer = customer("C1", "Alice", "ES", true);
        Vehicle vehicle = vehicle("AA111", VehicleCategory.ECONOMY, FuelType.PETROL, 4, 10_000, "35.00");
        Branch branch = branch("MAD", "Madrid", "ES");

        List<Rental> rentals = List.of(
                // 2026-01-01 to 2026-01-04 -> 3 days, price 60.00
                rental("R1", customer, vehicle, branch, branch, "2026-01-01", "2026-01-04", "60.00"),
                // 2026-02-01 to 2026-02-05 -> 4 days, price 40.00
                rental("R2", customer, vehicle, branch, branch, "2026-02-01", "2026-02-05", "40.00")
        );

        BigDecimal result = C05WeightedAveragePricePerDay.weightedAveragePricePerDay(rentals);

        // total price = 60.00 + 40.00 = 100.00, total days = 3 + 4 = 7
        // 100.00 / 7 = 14.2857142857... -> HALF_UP to scale 2 = 14.29
        assertThat(result).isEqualByComparingTo(money("14.29"));
        assertThat(result.scale()).isEqualTo(2);
    }

    @Test
    @DisplayName("returns zero at scale 2 for an empty rental list")
    void weightedAveragePricePerDay_withEmptyList_returnsZeroAtScale2() {
        BigDecimal result = C05WeightedAveragePricePerDay.weightedAveragePricePerDay(List.of());

        assertThat(result).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(result.scale()).isEqualTo(2);
    }
}
