package com.fgiaquinta.katas.streams.grouping;

import com.fgiaquinta.katas.domain.FuelType;
import com.fgiaquinta.katas.domain.Vehicle;
import com.fgiaquinta.katas.domain.VehicleCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.fgiaquinta.katas.domain.TestData.vehicle;
import static org.assertj.core.api.Assertions.assertThat;

class B05HighestRateVehiclePerCategoryTest {

    @Test
    @DisplayName("picks the vehicle with the highest dailyRate per category, keeping the first one on ties")
    void highestRatePerCategory_withTiesAndDistinctRates_picksHighestKeepingFirstOnTie() {
        Vehicle econ1 = vehicle("E1", VehicleCategory.ECONOMY, FuelType.PETROL, 4, 1000, "30.00");
        Vehicle econ2 = vehicle("E2", VehicleCategory.ECONOMY, FuelType.PETROL, 4, 1200, "45.00");
        Vehicle suv1 = vehicle("S1", VehicleCategory.SUV, FuelType.DIESEL, 5, 2000, "90.00");
        Vehicle suv2 = vehicle("S2", VehicleCategory.SUV, FuelType.DIESEL, 5, 2100, "90.00"); // tie with suv1

        List<Vehicle> vehicles = List.of(econ1, econ2, suv1, suv2);

        Map<VehicleCategory, Vehicle> result = B05HighestRateVehiclePerCategory.highestRatePerCategory(vehicles);

        // ECONOMY: 45.00 (econ2) > 30.00 (econ1)
        assertThat(result.get(VehicleCategory.ECONOMY)).isEqualTo(econ2);
        // SUV: tie at 90.00, suv1 appears first in encounter order -> suv1 wins
        assertThat(result.get(VehicleCategory.SUV)).isEqualTo(suv1);
        assertThat(result).hasSize(2);
    }

    @Test
    @DisplayName("returns an empty map when the vehicle list is empty")
    void highestRatePerCategory_withEmptyList_returnsEmptyMap() {
        Map<VehicleCategory, Vehicle> result = B05HighestRateVehiclePerCategory.highestRatePerCategory(List.of());

        assertThat(result).isEmpty();
    }
}
