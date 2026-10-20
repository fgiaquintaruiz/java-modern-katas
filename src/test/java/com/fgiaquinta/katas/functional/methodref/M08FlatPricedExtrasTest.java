package com.fgiaquinta.katas.functional.methodref;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fgiaquinta.katas.domain.TestData.extra;
import static com.fgiaquinta.katas.domain.TestData.money;
import static org.assertj.core.api.Assertions.assertThat;

class M08FlatPricedExtrasTest {

    @Test
    @DisplayName("flatPricedExtras_withTwoNames_returnsExtrasWithSamePriceInInputOrder")
    void flatPricedExtras_withTwoNames_returnsExtrasWithSamePriceInInputOrder() {
        // Two names sharing one price of 5.00 -> two Extra, input order kept.
        var result = M08FlatPricedExtras.flatPricedExtras(List.of("GPS", "Child seat"), money("5.00"));

        assertThat(result).containsExactly(extra("GPS", "5.00"), extra("Child seat", "5.00"));
    }

    @Test
    @DisplayName("flatPricedExtras_withNoNames_returnsEmptyArray")
    void flatPricedExtras_withNoNames_returnsEmptyArray() {
        assertThat(M08FlatPricedExtras.flatPricedExtras(List.of(), money("5.00"))).isEmpty();
    }
}
