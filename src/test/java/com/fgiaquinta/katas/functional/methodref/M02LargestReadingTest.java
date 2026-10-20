package com.fgiaquinta.katas.functional.methodref;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class M02LargestReadingTest {

    @Test
    @DisplayName("largestReading_withSeveralReadings_returnsMaximum")
    void largestReading_withSeveralReadings_returnsMaximum() {
        // max(12_000, 45_000, 800) = 45_000.
        assertThat(M02LargestReading.largestReading(List.of(12_000, 45_000, 800))).contains(45_000);
    }

    @Test
    @DisplayName("largestReading_withEmptyList_returnsEmptyOptional")
    void largestReading_withEmptyList_returnsEmptyOptional() {
        assertThat(M02LargestReading.largestReading(List.of())).isEmpty();
    }
}
