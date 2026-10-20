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

class B07RateRangePerCategoryTest {

    @Test
    @DisplayName("computes the min and max dailyRate per category in a single pass")
    void rateRangePerCategory_withMultipleVehiclesPerCategory_computesMinAndMax() {
        List<Vehicle> vehicles = List.of(
                vehicle("E1", VehicleCategory.ECONOMY, FuelType.PETROL, 4, 1000, "30.00"),
                vehicle("E2", VehicleCategory.ECONOMY, FuelType.PETROL, 4, 1100, "45.00"),
                vehicle("E3", VehicleCategory.ECONOMY, FuelType.PETROL, 4, 1200, "20.00"),
                vehicle("S1", VehicleCategory.SUV, FuelType.DIESEL, 5, 2000, "90.00")
        );

        Map<VehicleCategory, B07RateRangePerCategory.RateRange> result =
                B07RateRangePerCategory.rateRangePerCategory(vehicles);

        // ECONOMY rates: 30.00, 45.00, 20.00 -> min 20.00, max 45.00
        assertThat(result.get(VehicleCategory.ECONOMY).min()).isEqualByComparingTo("20.00");
        assertThat(result.get(VehicleCategory.ECONOMY).max()).isEqualByComparingTo("45.00");
        // SUV: single rate 90.00 -> min == max == 90.00
        assertThat(result.get(VehicleCategory.SUV).min()).isEqualByComparingTo("90.00");
        assertThat(result.get(VehicleCategory.SUV).max()).isEqualByComparingTo("90.00");
        assertThat(result).hasSize(2);
    }

    @Test
    @DisplayName("returns an empty map when the vehicle list is empty")
    void rateRangePerCategory_withEmptyList_returnsEmptyMap() {
        Map<VehicleCategory, B07RateRangePerCategory.RateRange> result =
                B07RateRangePerCategory.rateRangePerCategory(List.of());

        assertThat(result).isEmpty();
    }
}
