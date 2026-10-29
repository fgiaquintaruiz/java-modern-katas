package com.fgiaquinta.katas.streams.flatmap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fgiaquinta.katas.domain.EngineType.TURBOFAN;
import static com.fgiaquinta.katas.domain.EngineType.TURBOPROP;
import static com.fgiaquinta.katas.domain.EngineType.PISTON;
import static com.fgiaquinta.katas.domain.AircraftCategory.NARROW_BODY;
import static com.fgiaquinta.katas.domain.AircraftCategory.REGIONAL;
import static com.fgiaquinta.katas.domain.AircraftCategory.BUSINESS_JET;
import static com.fgiaquinta.katas.domain.AircraftCategory.WIDE_BODY;
import static com.fgiaquinta.katas.domain.AircraftCategory.CARGO;
import static com.fgiaquinta.katas.domain.TestData.airport;
import static com.fgiaquinta.katas.domain.TestData.customer;
import static com.fgiaquinta.katas.domain.TestData.charter;
import static com.fgiaquinta.katas.domain.TestData.aircraft;
import static org.assertj.core.api.Assertions.assertThat;

class A06UpgradeCandidatesTest {

    @Test
    @DisplayName("upgradeCandidates_withHigherTierAircraftInOriginFleet_returnsThemInFleetOrderPerCharter")
    void upgradeCandidates_withHigherTierAircraftInOriginFleet_returnsThemInFleetOrderPerCharter() {
        var regional = aircraft("EC-REG", REGIONAL, PISTON, 11, 1_000, "30.00");
        var narrowBody = aircraft("EC-NAR", NARROW_BODY, TURBOFAN, 180, 1_000, "35.00");
        var wideBody = aircraft("EC-WID", WIDE_BODY, TURBOFAN, 300, 1_000, "50.00");
        var cargo = aircraft("EC-CAR", CARGO, TURBOPROP, 0, 1_000, "60.00");
        var businessJet = aircraft("EC-BUS", BUSINESS_JET, PISTON, 6, 1_000, "90.00");
        var origin = airport("MAD", "Madrid", "Spain", regional, narrowBody, wideBody, cargo, businessJet);
        var alice = customer("C1", "Alice", "ES", true);

        // chartered REGIONAL: every higher tier in the fleet (NARROW_BODY, WIDE_BODY, CARGO, BUSINESS_JET) qualifies.
        var r1 = charter("R1", alice, regional, origin, origin, "2026-01-01", "2026-01-03", "60.00");
        // chartered WIDE_BODY: only strictly higher tiers in the fleet (CARGO, BUSINESS_JET) qualify.
        var r2 = charter("R2", alice, wideBody, origin, origin, "2026-02-01", "2026-02-02", "50.00");

        var result = A06UpgradeCandidates.upgradeCandidates(List.of(r1, r2));

        assertThat(result).containsExactly(
                new A06UpgradeCandidates.Upgrade("R1", "EC-NAR"),
                new A06UpgradeCandidates.Upgrade("R1", "EC-WID"),
                new A06UpgradeCandidates.Upgrade("R1", "EC-CAR"),
                new A06UpgradeCandidates.Upgrade("R1", "EC-BUS"),
                new A06UpgradeCandidates.Upgrade("R2", "EC-CAR"),
                new A06UpgradeCandidates.Upgrade("R2", "EC-BUS"));
    }

    @Test
    @DisplayName("upgradeCandidates_whenCharteredAircraftIsAlreadyTopTier_returnsNoCandidates")
    void upgradeCandidates_whenCharteredAircraftIsAlreadyTopTier_returnsNoCandidates() {
        var regional = aircraft("EC-REG", REGIONAL, PISTON, 11, 1_000, "30.00");
        var businessJet = aircraft("EC-BUS", BUSINESS_JET, PISTON, 6, 1_000, "90.00");
        var origin = airport("MAD", "Madrid", "Spain", regional, businessJet);
        var alice = customer("C1", "Alice", "ES", true);
        // chartered BUSINESS_JET: no aircraft in the fleet outranks it, so no upgrade candidate exists.
        var r1 = charter("R1", alice, businessJet, origin, origin, "2026-01-01", "2026-01-03", "270.00");

        var result = A06UpgradeCandidates.upgradeCandidates(List.of(r1));

        assertThat(result).isEmpty();
    }
}
