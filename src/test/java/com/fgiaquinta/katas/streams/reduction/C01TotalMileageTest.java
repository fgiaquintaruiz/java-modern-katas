package com.fgiaquinta.katas.streams.reduction;

import com.fgiaquinta.katas.domain.FuelType;
import com.fgiaquinta.katas.domain.Vehicle;
import com.fgiaquinta.katas.domain.VehicleCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fgiaquinta.katas.domain.TestData.vehicle;
import static org.assertj.core.api.Assertions.assertThat;

class C01TotalMileageTest {

    @Test
    @DisplayName("sums mileageKm across all vehicles in the list")
    void totalMileage_withMultipleVehicles_sumsAllMileage() {
        List<Vehicle> vehicles = List.of(
                vehicle("AA111", VehicleCategory.ECONOMY, FuelType.PETROL, 4, 10_000, "35.00"),
                vehicle("BB222", VehicleCategory.COMPACT, FuelType.DIESEL, 5, 25_000, "40.00"),
                vehicle("CC333", VehicleCategory.SUV, FuelType.HYBRID, 5, 5_000, "60.00"),
                vehicle("DD444", VehicleCategory.VAN, FuelType.PETROL, 7, 60_000, "55.00")
        );

        long result = C01TotalMileage.totalMileage(vehicles);

        // 10_000 + 25_000 + 5_000 + 60_000 = 100_000
        assertThat(result).isEqualTo(100_000L);
    }

    @Test
    @DisplayName("returns zero for an empty vehicle list")
    void totalMileage_withEmptyList_returnsZero() {
        long result = C01TotalMileage.totalMileage(List.of());

        assertThat(result).isZero();
    }
}
