package com.fgiaquinta.katas.collections;

import com.fgiaquinta.katas.domain.Aircraft;
import com.fgiaquinta.katas.domain.AircraftCategory;
import com.fgiaquinta.katas.domain.EngineType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static com.fgiaquinta.katas.domain.AircraftCategory.BUSINESS_JET;
import static com.fgiaquinta.katas.domain.AircraftCategory.CARGO;
import static com.fgiaquinta.katas.domain.AircraftCategory.NARROW_BODY;
import static com.fgiaquinta.katas.domain.AircraftCategory.REGIONAL;
import static com.fgiaquinta.katas.domain.AircraftCategory.WIDE_BODY;
import static com.fgiaquinta.katas.domain.TestData.aircraft;
import static org.assertj.core.api.Assertions.assertThat;

class D07CategoryCountsOutsideRangeTest {

    private final List<Aircraft> fleet = List.of(
            aircraft("EC-001", REGIONAL, EngineType.PISTON, 11, 1000, "30.00"),
            aircraft("EC-002", REGIONAL, EngineType.TURBOPROP, 70, 1000, "40.00"),
            aircraft("EC-003", NARROW_BODY, EngineType.TURBOFAN, 180, 1200, "35.00"),
            aircraft("EC-004", BUSINESS_JET, EngineType.TURBOFAN, 12, 300, "120.00"));

    @Test
    @DisplayName("counts only the categories outside the range, in enum order")
    void countOutsideRange_withMiddleRange_returnsComplementInEnumOrder() {
        EnumMap<AircraftCategory, Long> result =
                D07CategoryCountsOutsideRange.countOutsideRange(fleet, NARROW_BODY, CARGO);

        assertThat(result).containsExactly(Map.entry(REGIONAL, 2L), Map.entry(BUSINESS_JET, 1L));
    }

    @Test
    @DisplayName("includes categories with no aircraft as zero")
    void countOutsideRange_withSingleCategoryRange_includesZeros() {
        EnumMap<AircraftCategory, Long> result =
                D07CategoryCountsOutsideRange.countOutsideRange(fleet, WIDE_BODY, WIDE_BODY);

        assertThat(result).containsExactly(
                Map.entry(REGIONAL, 2L), Map.entry(NARROW_BODY, 1L),
                Map.entry(CARGO, 0L), Map.entry(BUSINESS_JET, 1L));
    }

    @Test
    @DisplayName("returns an empty map when the range covers every category")
    void countOutsideRange_withFullRange_returnsEmpty() {
        assertThat(D07CategoryCountsOutsideRange.countOutsideRange(fleet, REGIONAL, BUSINESS_JET)).isEmpty();
    }

    @Test
    @DisplayName("returns zeros for every outside category when the fleet is empty")
    void countOutsideRange_withEmptyFleet_returnsAllZeros() {
        EnumMap<AircraftCategory, Long> result =
                D07CategoryCountsOutsideRange.countOutsideRange(List.of(), NARROW_BODY, CARGO);

        assertThat(result).containsExactly(Map.entry(REGIONAL, 0L), Map.entry(BUSINESS_JET, 0L));
    }
}
