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

class B01CountByCategoryTest {

    @Test
    @DisplayName("counts vehicles grouped by category, omitting categories absent from the input")
    void countByCategory_withMixedCategories_countsEachPresentCategory() {
        List<Vehicle> vehicles = List.of(
                vehicle("P1", VehicleCategory.ECONOMY, FuelType.PETROL, 4, 1000, "30.00"),
                vehicle("P2", VehicleCategory.ECONOMY, FuelType.PETROL, 4, 2000, "32.00"),
                vehicle("P3", VehicleCategory.COMPACT, FuelType.PETROL, 5, 1500, "40.00"),
                vehicle("P4", VehicleCategory.SUV, FuelType.DIESEL, 5, 3000, "60.00"),
                vehicle("P5", VehicleCategory.SUV, FuelType.DIESEL, 5, 3200, "62.00")
        );

        Map<VehicleCategory, Long> result = B01CountByCategory.countByCategory(vehicles);

        // ECONOMY: P1, P2 -> 2
        // COMPACT: P3 -> 1
        // SUV: P4, P5 -> 2
        // VAN and LUXURY have no vehicles, so they must be absent.
        assertThat(result).containsExactlyInAnyOrderEntriesOf(Map.of(
                VehicleCategory.ECONOMY, 2L,
                VehicleCategory.COMPACT, 1L,
                VehicleCategory.SUV, 2L
        ));
    }

    @Test
    @DisplayName("returns an empty map when the vehicle list is empty")
    void countByCategory_withEmptyList_returnsEmptyMap() {
        Map<VehicleCategory, Long> result = B01CountByCategory.countByCategory(List.of());

        assertThat(result).isEmpty();
    }
}
