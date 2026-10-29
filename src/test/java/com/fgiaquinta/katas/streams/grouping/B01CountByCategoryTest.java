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

class B01CountByCategoryTest {

    @Test
    @DisplayName("counts aircraft grouped by category, omitting categories absent from the input")
    void countByCategory_withMixedCategories_countsEachPresentCategory() {
        List<Aircraft> aircraft = List.of(
                aircraft("EC-P01", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 1000, "30.00"),
                aircraft("EC-P02", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 2000, "32.00"),
                aircraft("EC-P03", AircraftCategory.NARROW_BODY, EngineType.TURBOFAN, 180, 1500, "40.00"),
                aircraft("EC-P04", AircraftCategory.WIDE_BODY, EngineType.TURBOFAN, 300, 3000, "60.00"),
                aircraft("EC-P05", AircraftCategory.WIDE_BODY, EngineType.TURBOFAN, 300, 3200, "62.00")
        );

        Map<AircraftCategory, Long> result = B01CountByCategory.countByCategory(aircraft);

        // REGIONAL: EC-P01, EC-P02 -> 2
        // NARROW_BODY: EC-P03 -> 1
        // WIDE_BODY: EC-P04, EC-P05 -> 2
        // CARGO and BUSINESS_JET have no aircraft, so they must be absent.
        assertThat(result).containsExactlyInAnyOrderEntriesOf(Map.of(
                AircraftCategory.REGIONAL, 2L,
                AircraftCategory.NARROW_BODY, 1L,
                AircraftCategory.WIDE_BODY, 2L
        ));
    }

    @Test
    @DisplayName("returns an empty map when the aircraft list is empty")
    void countByCategory_withEmptyList_returnsEmptyMap() {
        Map<AircraftCategory, Long> result = B01CountByCategory.countByCategory(List.of());

        assertThat(result).isEmpty();
    }
}
