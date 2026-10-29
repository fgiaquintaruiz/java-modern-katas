package com.fgiaquinta.katas.functional.methodref;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class M01ParseAllNumbersTest {

    @Test
    @DisplayName("parseAll_withNumericStrings_returnsIntegersInOrder")
    void parseAll_withNumericStrings_returnsIntegersInOrder() {
        // "12" -> 12, "-3" -> -3, "007" -> 7, input order preserved.
        assertThat(M01ParseAllNumbers.parseAll(List.of("12", "-3", "007"))).containsExactly(12, -3, 7);
    }

    @Test
    @DisplayName("parseAll_withEmptyList_returnsEmptyList")
    void parseAll_withEmptyList_returnsEmptyList() {
        assertThat(M01ParseAllNumbers.parseAll(List.of())).isEmpty();
    }
}
