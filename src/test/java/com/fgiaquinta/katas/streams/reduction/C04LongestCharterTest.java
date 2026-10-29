package com.fgiaquinta.katas.streams.reduction;

import com.fgiaquinta.katas.domain.Airport;
import com.fgiaquinta.katas.domain.Customer;
import com.fgiaquinta.katas.domain.EngineType;
import com.fgiaquinta.katas.domain.Charter;
import com.fgiaquinta.katas.domain.Aircraft;
import com.fgiaquinta.katas.domain.AircraftCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.fgiaquinta.katas.domain.TestData.airport;
import static com.fgiaquinta.katas.domain.TestData.customer;
import static com.fgiaquinta.katas.domain.TestData.charter;
import static com.fgiaquinta.katas.domain.TestData.aircraft;
import static org.assertj.core.api.Assertions.assertThat;

class C04LongestCharterTest {

    private static final Customer CUSTOMER = customer("C1", "Alice", "ES", true);
    private static final Aircraft AIRCRAFT =
            aircraft("EC-AAA", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 10_000, "35.00");
    private static final Airport AIRPORT = airport("MAD", "Madrid", "ES");

    @Test
    @DisplayName("returns the charter with the greatest number of days")
    void longestCharter_withDistinctDurations_returnsTheLongestOne() {
        // 2026-01-01 to 2026-01-03 -> 2 days
        Charter shortest = charter("R1", CUSTOMER, AIRCRAFT, AIRPORT, AIRPORT, "2026-01-01", "2026-01-03", "60.00");
        // 2026-02-01 to 2026-02-05 -> 4 days (the longest)
        Charter longest = charter("R2", CUSTOMER, AIRCRAFT, AIRPORT, AIRPORT, "2026-02-01", "2026-02-05", "140.00");
        // 2026-03-01 to 2026-03-04 -> 3 days
        Charter middle = charter("R3", CUSTOMER, AIRCRAFT, AIRPORT, AIRPORT, "2026-03-01", "2026-03-04", "90.00");

        Optional<Charter> result = C04LongestCharter.longestCharter(List.of(shortest, longest, middle));

        assertThat(result).contains(longest);
    }

    @Test
    @DisplayName("keeps the first charter in encounter order when durations tie")
    void longestCharter_withTiedDurations_returnsTheFirstEncountered() {
        // 2026-01-01 to 2026-01-06 -> 5 days
        Charter first = charter("R1", CUSTOMER, AIRCRAFT, AIRPORT, AIRPORT, "2026-01-01", "2026-01-06", "150.00");
        // 2026-02-01 to 2026-02-06 -> 5 days, same duration as "first"
        Charter second = charter("R2", CUSTOMER, AIRCRAFT, AIRPORT, AIRPORT, "2026-02-01", "2026-02-06", "150.00");

        Optional<Charter> result = C04LongestCharter.longestCharter(List.of(first, second));

        assertThat(result).contains(first);
    }

    @Test
    @DisplayName("returns an empty Optional for an empty charter list")
    void longestCharter_withEmptyList_returnsEmptyOptional() {
        Optional<Charter> result = C04LongestCharter.longestCharter(List.of());

        assertThat(result).isEmpty();
    }
}
