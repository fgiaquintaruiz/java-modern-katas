package com.fgiaquinta.katas.streams.flatmap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fgiaquinta.katas.domain.EngineType.PISTON;
import static com.fgiaquinta.katas.domain.AircraftCategory.REGIONAL;
import static com.fgiaquinta.katas.domain.TestData.airport;
import static com.fgiaquinta.katas.domain.TestData.customer;
import static com.fgiaquinta.katas.domain.TestData.extra;
import static com.fgiaquinta.katas.domain.TestData.charter;
import static com.fgiaquinta.katas.domain.TestData.aircraft;
import static org.assertj.core.api.Assertions.assertThat;

class A05CharterExtraLinesTest {

    @Test
    @DisplayName("extraLines_withSomeChartersLackingExtras_returnsOnlyLinesForChartersWithExtrasInOrder")
    void extraLines_withSomeChartersLackingExtras_returnsOnlyLinesForChartersWithExtrasInOrder() {
        var alice = customer("C1", "Alice", "ES", true);
        var aircraft = aircraft("EC-AAA", REGIONAL, PISTON, 11, 10_000, "40.00");
        var madrid = airport("MAD", "Madrid", "Spain", aircraft);

        var r1 = charter("R1", alice, aircraft, madrid, madrid, "2026-01-01", "2026-01-04", "150.00",
                extra("CATERING", "10.00"), extra("GROUND_TRANSFER", "5.00"));
        // no extras: must not contribute any line
        var r2 = charter("R2", alice, aircraft, madrid, madrid, "2026-02-01", "2026-02-03", "80.00");
        var r3 = charter("R3", alice, aircraft, madrid, madrid, "2026-03-01", "2026-03-02", "40.00",
                extra("INSURANCE", "20.00"));

        var result = A05CharterExtraLines.extraLines(List.of(r1, r2, r3));

        // Charter order first, then extras order within each charter; R2 contributes nothing.
        assertThat(result).containsExactly("R1:CATERING", "R1:GROUND_TRANSFER", "R3:INSURANCE");
    }

    @Test
    @DisplayName("extraLines_whenNoCharterHasExtras_returnsEmptyList")
    void extraLines_whenNoCharterHasExtras_returnsEmptyList() {
        var alice = customer("C1", "Alice", "ES", true);
        var aircraft = aircraft("EC-AAA", REGIONAL, PISTON, 11, 10_000, "40.00");
        var madrid = airport("MAD", "Madrid", "Spain", aircraft);
        var r1 = charter("R1", alice, aircraft, madrid, madrid, "2026-01-01", "2026-01-04", "150.00");

        var result = A05CharterExtraLines.extraLines(List.of(r1));

        assertThat(result).isEmpty();
    }
}
