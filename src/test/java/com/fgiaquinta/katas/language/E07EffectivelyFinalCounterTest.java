package com.fgiaquinta.katas.language;

import com.fgiaquinta.katas.domain.Aircraft;
import com.fgiaquinta.katas.domain.AircraftCategory;
import com.fgiaquinta.katas.domain.Airport;
import com.fgiaquinta.katas.domain.Charter;
import com.fgiaquinta.katas.domain.Customer;
import com.fgiaquinta.katas.domain.EngineType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fgiaquinta.katas.domain.TestData.aircraft;
import static com.fgiaquinta.katas.domain.TestData.airport;
import static com.fgiaquinta.katas.domain.TestData.charter;
import static com.fgiaquinta.katas.domain.TestData.customer;
import static org.assertj.core.api.Assertions.assertThat;

class E07EffectivelyFinalCounterTest {

    private final Customer ana = customer("C1", "Ana", "Spain", true);
    private final Aircraft plane = aircraft("EC-001", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 1000, "30.00");
    private final Airport madrid = airport("MAD", "Madrid", "Spain", plane);

    private final Charter oneDay = charter("R1", ana, plane, madrid, madrid, "2024-01-01", "2024-01-02", "30.00");
    private final Charter twoDays = charter("R2", ana, plane, madrid, madrid, "2024-01-01", "2024-01-03", "60.00");
    private final Charter threeDays = charter("R3", ana, plane, madrid, madrid, "2024-01-01", "2024-01-04", "90.00");

    @Test
    @DisplayName("counts charters lasting at least minDays, including exactly minDays")
    void countLongCharters_withMixedDurations_countsThoseAtOrAboveThreshold() {
        assertThat(E07EffectivelyFinalCounter.countLongCharters(List.of(oneDay, twoDays, threeDays), 2)).isEqualTo(2);
    }

    @Test
    @DisplayName("returns zero when no charter is long enough")
    void countLongCharters_withNoMatch_returnsZero() {
        assertThat(E07EffectivelyFinalCounter.countLongCharters(List.of(oneDay, twoDays), 5)).isZero();
    }

    @Test
    @DisplayName("returns zero for an empty list")
    void countLongCharters_withEmptyList_returnsZero() {
        assertThat(E07EffectivelyFinalCounter.countLongCharters(List.of(), 1)).isZero();
    }

    @Test
    @DisplayName("counts every charter when the threshold is one day")
    void countLongCharters_withThresholdOne_countsAll() {
        assertThat(E07EffectivelyFinalCounter.countLongCharters(List.of(oneDay, twoDays, threeDays), 1)).isEqualTo(3);
    }
}
