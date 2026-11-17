package com.fgiaquinta.katas.kt.language

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class E05RecordInternalsTest {

    @Test
    @DisplayName("quote_withNegativePrice_throwsIllegalArgument")
    fun quote_withNegativePrice_throwsIllegalArgument() {
        assertThatThrownBy { quote("MAD-BCN", BigDecimal("-0.01")) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    @DisplayName("quote_withZeroPrice_isAccepted")
    fun quote_withZeroPrice_isAccepted() {
        assertThat(quote("MAD-BCN", BigDecimal.ZERO).price).isEqualByComparingTo("0")
    }

    @Test
    @DisplayName("quote_withPaddedRoute_storesStrippedRoute")
    fun quote_withPaddedRoute_storesStrippedRoute() {
        assertThat(quote("  MAD-BCN ", BigDecimal("100.00")).route).isEqualTo("MAD-BCN")
    }

    @Test
    @DisplayName("quote_withEqualComponents_areEqualWithSameHashCode")
    fun quote_withEqualComponents_areEqualWithSameHashCode() {
        val first = quote("MAD-BCN", BigDecimal("100.00"))
        val second = quote("MAD-BCN", BigDecimal("100.00"))

        assertThat(first).isEqualTo(second)
        assertThat(first.hashCode()).isEqualTo(second.hashCode())
    }

    @Test
    @DisplayName("quote_withDifferentNotes_stillEqualBecauseBodyPropertiesAreExcluded")
    fun quote_withDifferentNotes_stillEqualBecauseBodyPropertiesAreExcluded() {
        val first = quote("MAD-BCN", BigDecimal("100.00"), "window seat")
        val second = quote("MAD-BCN", BigDecimal("100.00"), "aisle seat")

        assertThat(first.note).isEqualTo("window seat")
        assertThat(first).isEqualTo(second)
        assertThat(first.hashCode()).isEqualTo(second.hashCode())
        assertThat(first.toString()).isEqualTo("Quote(route=MAD-BCN, price=100.00)")
    }
}
