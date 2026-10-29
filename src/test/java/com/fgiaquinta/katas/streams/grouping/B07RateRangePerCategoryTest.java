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

class B07RateRangePerCategoryTest {

    @Test
    @DisplayName("computes the min and max dailyRate per category in a single pass")
    void rateRangePerCategory_withMultipleAircraftPerCategory_computesMinAndMax() {
        List<Aircraft> aircraft = List.of(
                aircraft("EC-R01", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 1000, "30.00"),
                aircraft("EC-R02", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 1100, "45.00"),
                aircraft("EC-R03", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 1200, "20.00"),
                aircraft("EC-W01", AircraftCategory.WIDE_BODY, EngineType.TURBOFAN, 300, 2000, "90.00")
        );

        Map<AircraftCategory, B07RateRangePerCategory.RateRange> result =
                B07RateRangePerCategory.rateRangePerCategory(aircraft);

        // REGIONAL rates: 30.00, 45.00, 20.00 -> min 20.00, max 45.00
        assertThat(result.get(AircraftCategory.REGIONAL).min()).isEqualByComparingTo("20.00");
        assertThat(result.get(AircraftCategory.REGIONAL).max()).isEqualByComparingTo("45.00");
        // WIDE_BODY: single rate 90.00 -> min == max == 90.00
        assertThat(result.get(AircraftCategory.WIDE_BODY).min()).isEqualByComparingTo("90.00");
        assertThat(result.get(AircraftCategory.WIDE_BODY).max()).isEqualByComparingTo("90.00");
        assertThat(result).hasSize(2);
    }

    @Test
    @DisplayName("returns an empty map when the aircraft list is empty")
    void rateRangePerCategory_withEmptyList_returnsEmptyMap() {
        Map<AircraftCategory, B07RateRangePerCategory.RateRange> result =
                B07RateRangePerCategory.rateRangePerCategory(List.of());

        assertThat(result).isEmpty();
    }
}
