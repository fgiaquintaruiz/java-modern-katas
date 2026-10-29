package com.fgiaquinta.katas.functional.methodref;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class M07SortedUniqueRegistrationsTest {

    @Test
    @DisplayName("sortedUniqueRegistrations_withDuplicatesAndDisorder_returnsDistinctInNaturalOrder")
    void sortedUniqueRegistrations_withDuplicatesAndDisorder_returnsDistinctInNaturalOrder() {
        // Natural order: "EC-AAA" < "EC-BBB" < "EC-CCC"; the repeated "EC-BBB" collapses.
        var result = M07SortedUniqueRegistrations.sortedUniqueRegistrations(
                List.of("EC-CCC", "EC-BBB", "EC-AAA", "EC-BBB"));

        assertThat(result).containsExactly("EC-AAA", "EC-BBB", "EC-CCC");
    }

    @Test
    @DisplayName("sortedUniqueRegistrations_withEmptyList_returnsEmptyTreeSet")
    void sortedUniqueRegistrations_withEmptyList_returnsEmptyTreeSet() {
        assertThat(M07SortedUniqueRegistrations.sortedUniqueRegistrations(List.of())).isEmpty();
    }
}
