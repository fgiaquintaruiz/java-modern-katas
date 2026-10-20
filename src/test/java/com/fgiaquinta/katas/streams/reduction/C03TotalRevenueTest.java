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

class C03TotalRevenueTest {

    @Test
    @DisplayName("sums totalPrice across all rentals")
    void totalRevenue_withMultipleRentals_sumsAllTotalPrices() {
        Customer customer = customer("C1", "Alice", "ES", true);
        Vehicle vehicle = vehicle("AA111", VehicleCategory.ECONOMY, FuelType.PETROL, 4, 10_000, "35.00");
        Branch branch = branch("MAD", "Madrid", "ES");

        List<Rental> rentals = List.of(
                rental("R1", customer, vehicle, branch, branch, "2026-01-01", "2026-01-02", "120.00"),
                rental("R2", customer, vehicle, branch, branch, "2026-02-01", "2026-02-02", "80.50"),
                rental("R3", customer, vehicle, branch, branch, "2026-03-01", "2026-03-02", "45.25")
        );

        BigDecimal result = C03TotalRevenue.totalRevenue(rentals);

        // 120.00 + 80.50 + 45.25 = 245.75
        assertThat(result).isEqualByComparingTo(money("245.75"));
    }

    @Test
    @DisplayName("returns BigDecimal.ZERO for an empty rental list")
    void totalRevenue_withEmptyList_returnsZero() {
        BigDecimal result = C03TotalRevenue.totalRevenue(List.of());

        assertThat(result).isEqualByComparingTo(BigDecimal.ZERO);
    }
}
