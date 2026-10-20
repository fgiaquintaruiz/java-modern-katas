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

import static com.fgiaquinta.katas.domain.TestData.branch;
import static com.fgiaquinta.katas.domain.TestData.customer;
import static com.fgiaquinta.katas.domain.TestData.rental;
import static com.fgiaquinta.katas.domain.TestData.vehicle;
import static org.assertj.core.api.Assertions.assertThat;

class C06Top3VehiclesByRevenueTest {

    private static final Customer CUSTOMER = customer("C1", "Alice", "ES", true);
    private static final Branch BRANCH = branch("MAD", "Madrid", "ES");

    @Test
    @DisplayName("returns the plates of the three vehicles with the highest summed revenue, highest first")
    void top3PlatesByRevenue_withMoreThanThreeVehicles_returnsTopThreeDescending() {
        Vehicle v1 = vehicle("AA111", VehicleCategory.ECONOMY, FuelType.PETROL, 4, 10_000, "35.00");
        Vehicle v2 = vehicle("BB222", VehicleCategory.COMPACT, FuelType.DIESEL, 5, 20_000, "45.00");
        Vehicle v3 = vehicle("CC333", VehicleCategory.SUV, FuelType.HYBRID, 5, 15_000, "60.00");
        Vehicle v4 = vehicle("DD444", VehicleCategory.VAN, FuelType.PETROL, 7, 30_000, "55.00");

        List<Rental> rentals = List.of(
                // AA111 revenue: 100.00 + 50.00 = 150.00
                rental("R1", CUSTOMER, v1, BRANCH, BRANCH, "2026-01-01", "2026-01-02", "100.00"),
                rental("R2", CUSTOMER, v1, BRANCH, BRANCH, "2026-01-05", "2026-01-06", "50.00"),
                // BB222 revenue: 200.00
                rental("R3", CUSTOMER, v2, BRANCH, BRANCH, "2026-02-01", "2026-02-02", "200.00"),
                // CC333 revenue: 120.00
                rental("R4", CUSTOMER, v3, BRANCH, BRANCH, "2026-03-01", "2026-03-02", "120.00"),
                // DD444 revenue: 90.00
                rental("R5", CUSTOMER, v4, BRANCH, BRANCH, "2026-04-01", "2026-04-02", "90.00")
        );

        List<String> result = C06Top3VehiclesByRevenue.top3PlatesByRevenue(rentals);

        // ranking: BB222 (200.00) > AA111 (150.00) > CC333 (120.00) > DD444 (90.00) -> DD444 excluded
        assertThat(result).containsExactly("BB222", "AA111", "CC333");
    }

    @Test
    @DisplayName("breaks a revenue tie by plate ascending order")
    void top3PlatesByRevenue_withTiedRevenue_breaksTieByPlateAscending() {
        Vehicle lowest = vehicle("ZZ999", VehicleCategory.ECONOMY, FuelType.PETROL, 4, 10_000, "35.00");
        Vehicle tiedA = vehicle("AA111", VehicleCategory.COMPACT, FuelType.DIESEL, 5, 20_000, "45.00");
        Vehicle tiedB = vehicle("BB222", VehicleCategory.SUV, FuelType.HYBRID, 5, 15_000, "60.00");

        List<Rental> rentals = List.of(
                // ZZ999 revenue: 100.00
                rental("R1", CUSTOMER, lowest, BRANCH, BRANCH, "2026-01-01", "2026-01-02", "100.00"),
                // AA111 revenue: 150.00, tied with BB222
                rental("R2", CUSTOMER, tiedA, BRANCH, BRANCH, "2026-02-01", "2026-02-02", "150.00"),
                // BB222 revenue: 150.00, tied with AA111
                rental("R3", CUSTOMER, tiedB, BRANCH, BRANCH, "2026-03-01", "2026-03-02", "150.00")
        );

        List<String> result = C06Top3VehiclesByRevenue.top3PlatesByRevenue(rentals);

        // AA111 and BB222 tie at 150.00 -> ascending plate order puts AA111 before BB222
        assertThat(result).containsExactly("AA111", "BB222", "ZZ999");
    }

    @Test
    @DisplayName("returns all plates when fewer than three distinct vehicles are present")
    void top3PlatesByRevenue_withFewerThanThreeVehicles_returnsAllOfThem() {
        Vehicle v1 = vehicle("AA111", VehicleCategory.ECONOMY, FuelType.PETROL, 4, 10_000, "35.00");
        Vehicle v2 = vehicle("BB222", VehicleCategory.COMPACT, FuelType.DIESEL, 5, 20_000, "45.00");

        List<Rental> rentals = List.of(
                // AA111 revenue: 120.00
                rental("R1", CUSTOMER, v1, BRANCH, BRANCH, "2026-01-01", "2026-01-02", "120.00"),
                // BB222 revenue: 80.00
                rental("R2", CUSTOMER, v2, BRANCH, BRANCH, "2026-02-01", "2026-02-02", "80.00")
        );

        List<String> result = C06Top3VehiclesByRevenue.top3PlatesByRevenue(rentals);

        assertThat(result).containsExactly("AA111", "BB222");
    }
}
