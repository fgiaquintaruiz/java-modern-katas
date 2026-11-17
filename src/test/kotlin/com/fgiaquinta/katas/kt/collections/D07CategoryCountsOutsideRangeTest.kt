package com.fgiaquinta.katas.kt.collections

import com.fgiaquinta.katas.domain.AircraftCategory.BUSINESS_JET
import com.fgiaquinta.katas.domain.AircraftCategory.CARGO
import com.fgiaquinta.katas.domain.AircraftCategory.NARROW_BODY
import com.fgiaquinta.katas.domain.AircraftCategory.REGIONAL
import com.fgiaquinta.katas.domain.AircraftCategory.WIDE_BODY
import com.fgiaquinta.katas.domain.EngineType.PISTON
import com.fgiaquinta.katas.domain.EngineType.TURBOFAN
import com.fgiaquinta.katas.domain.EngineType.TURBOPROP
import com.fgiaquinta.katas.domain.TestData.aircraft
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class D07CategoryCountsOutsideRangeTest {

    private val fleet = listOf(
        aircraft("EC-001", REGIONAL, PISTON, 11, 1000, "30.00"),
        aircraft("EC-002", REGIONAL, TURBOPROP, 70, 1000, "40.00"),
        aircraft("EC-003", NARROW_BODY, TURBOFAN, 180, 1200, "35.00"),
        aircraft("EC-004", BUSINESS_JET, TURBOFAN, 12, 300, "120.00"))

    @Test
    @DisplayName("countOutsideRange_withMiddleRange_returnsComplementInEnumOrder")
    fun countOutsideRange_withMiddleRange_returnsComplementInEnumOrder() {
        val result = countOutsideRange(fleet, NARROW_BODY, CARGO)

        assertThat(result.toList()).containsExactly((REGIONAL to 2L), (BUSINESS_JET to 1L))
    }

    @Test
    @DisplayName("countOutsideRange_withSingleCategoryRange_includesZeros")
    fun countOutsideRange_withSingleCategoryRange_includesZeros() {
        val result = countOutsideRange(fleet, WIDE_BODY, WIDE_BODY)

        assertThat(result.toList()).containsExactly(
            (REGIONAL to 2L), (NARROW_BODY to 1L),
            (CARGO to 0L), (BUSINESS_JET to 1L))
    }

    @Test
    @DisplayName("countOutsideRange_withFullRange_returnsEmpty")
    fun countOutsideRange_withFullRange_returnsEmpty() {
        assertThat(countOutsideRange(fleet, REGIONAL, BUSINESS_JET)).isEmpty()
    }

    @Test
    @DisplayName("countOutsideRange_withEmptyFleet_returnsAllZeros")
    fun countOutsideRange_withEmptyFleet_returnsAllZeros() {
        val result = countOutsideRange(emptyList(), NARROW_BODY, CARGO)

        assertThat(result.toList()).containsExactly((REGIONAL to 0L), (BUSINESS_JET to 0L))
    }

    @Test
    @DisplayName("countOutsideRange_keysComeFromTheEnumConstants")
    fun countOutsideRange_keysComeFromTheEnumConstants() {
        val result = countOutsideRange(emptyList(), REGIONAL, REGIONAL)

        // Every constant of the enum except the range, obtained the Kotlin way.
        assertThat(result.keys).containsExactlyElementsOf(
            enumValues<com.fgiaquinta.katas.domain.AircraftCategory>().filter { it != REGIONAL })
    }
}
