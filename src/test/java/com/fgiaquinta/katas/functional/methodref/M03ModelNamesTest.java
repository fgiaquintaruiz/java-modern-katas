package com.fgiaquinta.katas.functional.methodref;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fgiaquinta.katas.domain.FuelType.DIESEL;
import static com.fgiaquinta.katas.domain.FuelType.PETROL;
import static com.fgiaquinta.katas.domain.TestData.vehicle;
import static com.fgiaquinta.katas.domain.VehicleCategory.ECONOMY;
import static com.fgiaquinta.katas.domain.VehicleCategory.SUV;
import static org.assertj.core.api.Assertions.assertThat;

class M03ModelNamesTest {

    @Test
    @DisplayName("modelNames_withTwoVehicles_returnsModelsInInputOrder")
    void modelNames_withTwoVehicles_returnsModelsInInputOrder() {
        // TestData uses the plate as the model: input order b, a gives "2222BBB", "1111AAA".
        var a = vehicle("1111AAA", SUV, DIESEL, 5, 10_000, "60.00");
        var b = vehicle("2222BBB", ECONOMY, PETROL, 5, 5_000, "30.00");

        assertThat(M03ModelNames.modelNames(List.of(b, a))).containsExactly("2222BBB", "1111AAA");
    }

    @Test
    @DisplayName("modelNames_withEmptyList_returnsEmptyList")
    void modelNames_withEmptyList_returnsEmptyList() {
        assertThat(M03ModelNames.modelNames(List.of())).isEmpty();
    }
}
