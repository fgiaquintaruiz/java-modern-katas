package com.fgiaquinta.katas.streams.reduction;

import com.fgiaquinta.katas.domain.EngineType;
import com.fgiaquinta.katas.domain.Aircraft;
import com.fgiaquinta.katas.domain.AircraftCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fgiaquinta.katas.domain.TestData.aircraft;
import static org.assertj.core.api.Assertions.assertThat;

class C01TotalFlightHoursTest {

    @Test
    @DisplayName("sums flightHours across all aircraft in the list")
    void totalFlightHours_withMultipleAircraft_sumsAllFlightHours() {
        List<Aircraft> aircraft = List.of(
                aircraft("EC-AAA", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 10_000, "35.00"),
                aircraft("EC-BBB", AircraftCategory.NARROW_BODY, EngineType.TURBOFAN, 180, 25_000, "40.00"),
                aircraft("EC-CCC", AircraftCategory.WIDE_BODY, EngineType.TURBOFAN, 300, 5_000, "60.00"),
                aircraft("EC-DDD", AircraftCategory.CARGO, EngineType.TURBOFAN, 0, 60_000, "55.00")
        );

        long result = C01TotalFlightHours.totalFlightHours(aircraft);

        // 10_000 + 25_000 + 5_000 + 60_000 = 100_000
        assertThat(result).isEqualTo(100_000L);
    }

    @Test
    @DisplayName("returns zero for an empty aircraft list")
    void totalFlightHours_withEmptyList_returnsZero() {
        long result = C01TotalFlightHours.totalFlightHours(List.of());

        assertThat(result).isZero();
    }
}
