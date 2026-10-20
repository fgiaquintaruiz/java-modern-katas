package com.fgiaquinta.katas.streams.flatmap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fgiaquinta.katas.domain.FuelType.DIESEL;
import static com.fgiaquinta.katas.domain.FuelType.PETROL;
import static com.fgiaquinta.katas.domain.VehicleCategory.ECONOMY;
import static com.fgiaquinta.katas.domain.VehicleCategory.SUV;
import static com.fgiaquinta.katas.domain.TestData.branch;
import static com.fgiaquinta.katas.domain.TestData.vehicle;
import static org.assertj.core.api.Assertions.assertThat;

class A03CitiesOfferingCategoryTest {

    @Test
    @DisplayName("citiesOffering_withTwoBranchesInSameCityQualifying_returnsCityOnlyOnce")
    void citiesOffering_withTwoBranchesInSameCityQualifying_returnsCityOnlyOnce() {
        var madrid1 = branch("MAD1", "Madrid", "Spain",
                vehicle("1111AAA", SUV, DIESEL, 5, 10_000, "60.00"));
        var madrid2 = branch("MAD2", "Madrid", "Spain",
                vehicle("2222BBB", SUV, DIESEL, 5, 8_000, "58.00"));
        var barcelona = branch("BCN", "Barcelona", "Spain",
                vehicle("3333CCC", ECONOMY, PETROL, 5, 5_000, "30.00"));
        var valencia = branch("VAL", "Valencia", "Spain",
                vehicle("4444DDD", SUV, DIESEL, 5, 2_000, "55.00"));

        var result = A03CitiesOfferingCategory.citiesOffering(
                List.of(madrid1, madrid2, barcelona, valencia), SUV);

        // Madrid qualifies via two different branches but must appear once; Barcelona has no SUV.
        assertThat(result).containsExactlyInAnyOrder("Madrid", "Valencia");
    }

    @Test
    @DisplayName("citiesOffering_whenNoBranchHasCategory_returnsEmptySet")
    void citiesOffering_whenNoBranchHasCategory_returnsEmptySet() {
        var barcelona = branch("BCN", "Barcelona", "Spain",
                vehicle("3333CCC", ECONOMY, PETROL, 5, 5_000, "30.00"));

        var result = A03CitiesOfferingCategory.citiesOffering(List.of(barcelona), SUV);

        assertThat(result).isEmpty();
    }
}
