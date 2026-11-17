package com.fgiaquinta.katas.kt.language

import com.fgiaquinta.katas.domain.AircraftCategory.REGIONAL
import com.fgiaquinta.katas.domain.EngineType.PISTON
import com.fgiaquinta.katas.domain.TestData.aircraft
import com.fgiaquinta.katas.domain.TestData.airport
import com.fgiaquinta.katas.domain.TestData.charter
import com.fgiaquinta.katas.domain.TestData.customer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class E07EffectivelyFinalCounterTest {

    private val ana = customer("C1", "Ana", "Spain", true)
    private val plane = aircraft("EC-001", REGIONAL, PISTON, 11, 1000, "30.00")
    private val madrid = airport("MAD", "Madrid", "Spain", plane)

    private val oneDay = charter("R1", ana, plane, madrid, madrid, "2024-01-01", "2024-01-02", "30.00")
    private val twoDays = charter("R2", ana, plane, madrid, madrid, "2024-01-01", "2024-01-03", "60.00")
    private val threeDays = charter("R3", ana, plane, madrid, madrid, "2024-01-01", "2024-01-04", "90.00")

    @Test
    @DisplayName("countLongCharters_withMixedDurations_countsThoseAtOrAboveThreshold")
    fun countLongCharters_withMixedDurations_countsThoseAtOrAboveThreshold() {
        assertThat(countLongCharters(listOf(oneDay, twoDays, threeDays), 2)).isEqualTo(2)
    }

    @Test
    @DisplayName("countLongCharters_withNoMatch_returnsZero")
    fun countLongCharters_withNoMatch_returnsZero() {
        assertThat(countLongCharters(listOf(oneDay, twoDays), 5)).isZero()
    }

    @Test
    @DisplayName("countLongCharters_withEmptyList_returnsZero")
    fun countLongCharters_withEmptyList_returnsZero() {
        assertThat(countLongCharters(emptyList(), 1)).isZero()
    }

    @Test
    @DisplayName("countLongCharters_withThresholdOne_countsAll")
    fun countLongCharters_withThresholdOne_countsAll() {
        assertThat(countLongCharters(listOf(oneDay, twoDays, threeDays), 1)).isEqualTo(3)
    }

    @Test
    @DisplayName("lambda_mutatingCapturedVar_compilesAndSeesTheChange")
    fun lambda_mutatingCapturedVar_compilesAndSeesTheChange() {
        var total = 0
        listOf(1, 2, 3).forEach { total += it }

        assertThat(total).isEqualTo(6)
    }
}
