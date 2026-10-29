package com.fgiaquinta.katas.streams.grouping;

import com.fgiaquinta.katas.domain.EngineType;
import com.fgiaquinta.katas.domain.Aircraft;
import com.fgiaquinta.katas.domain.AircraftCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.fgiaquinta.katas.domain.TestData.aircraft;
import static org.assertj.core.api.Assertions.assertThat;

class B05HighestRateAircraftPerCategoryTest {

    @Test
    @DisplayName("picks the aircraft with the highest dailyRate per category, keeping the first one on ties")
    void highestRatePerCategory_withTiesAndDistinctRates_picksHighestKeepingFirstOnTie() {
        Aircraft regional1 = aircraft("EC-R01", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 1000, "30.00");
        Aircraft regional2 = aircraft("EC-R02", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 1200, "45.00");
        Aircraft wideBody1 = aircraft("EC-W01", AircraftCategory.WIDE_BODY, EngineType.TURBOFAN, 300, 2000, "90.00");
        Aircraft wideBody2 = aircraft("EC-W02", AircraftCategory.WIDE_BODY, EngineType.TURBOFAN, 300, 2100, "90.00"); // tie with wideBody1

        List<Aircraft> aircraft = List.of(regional1, regional2, wideBody1, wideBody2);

        Map<AircraftCategory, Aircraft> result = B05HighestRateAircraftPerCategory.highestRatePerCategory(aircraft);

        // REGIONAL: 45.00 (regional2) > 30.00 (regional1)
        assertThat(result.get(AircraftCategory.REGIONAL)).isEqualTo(regional2);
        // WIDE_BODY: tie at 90.00, wideBody1 appears first in encounter order -> wideBody1 wins
        assertThat(result.get(AircraftCategory.WIDE_BODY)).isEqualTo(wideBody1);
        assertThat(result).hasSize(2);
    }

    @Test
    @DisplayName("returns an empty map when the aircraft list is empty")
    void highestRatePerCategory_withEmptyList_returnsEmptyMap() {
        Map<AircraftCategory, Aircraft> result = B05HighestRateAircraftPerCategory.highestRatePerCategory(List.of());

        assertThat(result).isEmpty();
    }
}
