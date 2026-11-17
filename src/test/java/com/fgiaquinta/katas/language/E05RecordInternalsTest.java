package com.fgiaquinta.katas.language;

import com.fgiaquinta.katas.language.E05RecordInternals.Quote;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class E05RecordInternalsTest {

    @Test
    @DisplayName("the compact constructor rejects a negative price")
    void quote_withNegativePrice_throwsIllegalArgument() {
        assertThatThrownBy(() -> E05RecordInternals.quote("MAD-BCN", new BigDecimal("-0.01")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("a zero price is accepted")
    void quote_withZeroPrice_isAccepted() {
        assertThat(E05RecordInternals.quote("MAD-BCN", BigDecimal.ZERO).price()).isEqualByComparingTo("0");
    }

    @Test
    @DisplayName("the compact constructor strips the route")
    void quote_withPaddedRoute_storesStrippedRoute() {
        assertThat(E05RecordInternals.quote("  MAD-BCN ", new BigDecimal("100.00")).route()).isEqualTo("MAD-BCN");
    }

    @Test
    @DisplayName("two records with equal components are equal and share a hash code")
    void quote_withEqualComponents_areEqualWithSameHashCode() {
        Quote first = E05RecordInternals.quote("MAD-BCN", new BigDecimal("100.00"));
        Quote second = E05RecordInternals.quote("MAD-BCN", new BigDecimal("100.00"));

        assertThat(first).isEqualTo(second);
        assertThat(first.hashCode()).isEqualTo(second.hashCode());
        assertThat(first).isNotEqualTo(E05RecordInternals.quote("MAD-SEV", new BigDecimal("100.00")));
    }

    @Test
    @DisplayName("toString is generated from the components")
    void quote_toString_listsComponentsByName() {
        assertThat(E05RecordInternals.quote("MAD-BCN", new BigDecimal("100.00")).toString())
                .isEqualTo("Quote[route=MAD-BCN, price=100.00]");
    }

    @Test
    @DisplayName("a record is a final class that already extends Record")
    void quote_type_isFinalRecord() {
        Quote quote = E05RecordInternals.quote("MAD-BCN", BigDecimal.TEN);

        assertThat(quote.getClass().isRecord()).isTrue();
        assertThat(Modifier.isFinal(quote.getClass().getModifiers())).isTrue();
        assertThat(quote.getClass().getSuperclass()).isEqualTo(Record.class);
    }
}
