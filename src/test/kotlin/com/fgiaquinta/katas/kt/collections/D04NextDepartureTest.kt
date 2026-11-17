package com.fgiaquinta.katas.kt.collections

import com.fgiaquinta.katas.domain.AircraftCategory.REGIONAL
import com.fgiaquinta.katas.domain.EngineType.PISTON
import com.fgiaquinta.katas.domain.TestData.aircraft
import com.fgiaquinta.katas.domain.TestData.airport
import com.fgiaquinta.katas.domain.TestData.charter
import com.fgiaquinta.katas.domain.TestData.customer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalTime

class D04NextDepartureTest {

    private val ana = customer("C1", "Ana", "Spain", true)
    private val plane = aircraft("EC-001", REGIONAL, PISTON, 11, 1000, "30.00")
    private val madrid = airport("MAD", "Madrid", "Spain", plane)

    private val r1 = charter("R1", ana, plane, madrid, madrid, "2024-01-01", "2024-01-02", "30.00")
    private val r2 = charter("R2", ana, plane, madrid, madrid, "2024-01-01", "2024-01-02", "30.00")
    private val r3 = charter("R3", ana, plane, madrid, madrid, "2024-01-01", "2024-01-02", "30.00")

    private val schedule: Map<LocalTime, com.fgiaquinta.katas.domain.Charter> = hashMapOf(
        LocalTime.of(18, 45) to r3,
        LocalTime.of(8, 0) to r1,
        LocalTime.of(12, 30) to r2)

    @Test
    @DisplayName("neighbours_withTimeBetweenDepartures_returnsPreviousAndNext")
    fun neighbours_withTimeBetweenDepartures_returnsPreviousAndNext() {
        val (previous, next) = neighbours(schedule, LocalTime.of(10, 0))

        assertThat(previous).isEqualTo(r1)
        assertThat(next).isEqualTo(r2)
    }

    @Test
    @DisplayName("neighbours_withTimeEqualToDeparture_returnsThatDepartureAsNext")
    fun neighbours_withTimeEqualToDeparture_returnsThatDepartureAsNext() {
        val (previous, next) = neighbours(schedule, LocalTime.of(12, 30))

        assertThat(next).isEqualTo(r2)
        assertThat(previous).isEqualTo(r1)
    }

    @Test
    @DisplayName("neighbours_withTimeBeforeFirstDeparture_returnsNullPrevious")
    fun neighbours_withTimeBeforeFirstDeparture_returnsNullPrevious() {
        val (previous, next) = neighbours(schedule, LocalTime.of(7, 0))

        assertThat(previous).isNull()
        assertThat(next).isEqualTo(r1)
    }

    @Test
    @DisplayName("neighbours_withTimeAfterLastDeparture_returnsNullNext")
    fun neighbours_withTimeAfterLastDeparture_returnsNullNext() {
        val (previous, next) = neighbours(schedule, LocalTime.of(19, 0))

        assertThat(next).isNull()
        assertThat(previous).isEqualTo(r3)
    }

    @Test
    @DisplayName("neighbours_withEmptySchedule_returnsBothNull")
    fun neighbours_withEmptySchedule_returnsBothNull() {
        assertThat(neighbours(emptyMap(), LocalTime.NOON)).isEqualTo(Pair(null, null))
    }
}
