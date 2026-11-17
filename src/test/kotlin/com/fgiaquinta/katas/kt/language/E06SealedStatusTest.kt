package com.fgiaquinta.katas.kt.language

import com.fgiaquinta.katas.domain.AircraftCategory.REGIONAL
import com.fgiaquinta.katas.domain.EngineType.PISTON
import com.fgiaquinta.katas.domain.TestData.aircraft
import com.fgiaquinta.katas.domain.TestData.airport
import com.fgiaquinta.katas.domain.TestData.charter
import com.fgiaquinta.katas.domain.TestData.customer
import com.fgiaquinta.katas.kt.language.CharterStatus.Boarding
import com.fgiaquinta.katas.kt.language.CharterStatus.Cancelled
import com.fgiaquinta.katas.kt.language.CharterStatus.Delayed
import com.fgiaquinta.katas.kt.language.CharterStatus.Scheduled
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class E06SealedStatusTest {

    private val ana = customer("C1", "Ana", "Spain", true)
    private val plane = aircraft("EC-001", REGIONAL, PISTON, 11, 1000, "30.00")
    private val madrid = airport("MAD", "Madrid", "Spain", plane)
    private val r1 = charter("R1", ana, plane, madrid, madrid, "2024-01-01", "2024-01-02", "30.00")

    @Test
    @DisplayName("describe_withScheduled_returnsScheduledAndCharterId")
    fun describe_withScheduled_returnsScheduledAndCharterId() {
        assertThat(describe(Scheduled(r1))).isEqualTo("Scheduled R1")
    }

    @Test
    @DisplayName("describe_withShortDelay_returnsDelayedMinutes")
    fun describe_withShortDelay_returnsDelayedMinutes() {
        assertThat(describe(Delayed(15))).isEqualTo("Delayed 15 min")
        assertThat(describe(Delayed(59))).isEqualTo("Delayed 59 min")
    }

    @Test
    @DisplayName("describe_withDelayFromSixtyMinutes_returnsHeavilyDelayed")
    fun describe_withDelayFromSixtyMinutes_returnsHeavilyDelayed() {
        assertThat(describe(Delayed(60))).isEqualTo("Heavily delayed 60 min")
        assertThat(describe(Delayed(90))).isEqualTo("Heavily delayed 90 min")
    }

    @Test
    @DisplayName("describe_withCancelled_returnsCancelledAndReason")
    fun describe_withCancelled_returnsCancelledAndReason() {
        assertThat(describe(Cancelled("weather"))).isEqualTo("Cancelled: weather")
    }

    @Test
    @DisplayName("describe_withBoardingObject_returnsBoarding")
    fun describe_withBoardingObject_returnsBoarding() {
        assertThat(describe(Boarding)).isEqualTo("Boarding")
    }
}
