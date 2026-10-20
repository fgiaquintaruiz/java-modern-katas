package com.fgiaquinta.katas.functional.methodref;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class M07SortedUniquePlatesTest {

    @Test
    @DisplayName("sortedUniquePlates_withDuplicatesAndDisorder_returnsDistinctInNaturalOrder")
    void sortedUniquePlates_withDuplicatesAndDisorder_returnsDistinctInNaturalOrder() {
        // Natural order: "1111AAA" < "2222BBB" < "3333CCC"; the repeated "2222BBB" collapses.
        var result = M07SortedUniquePlates.sortedUniquePlates(
                List.of("3333CCC", "2222BBB", "1111AAA", "2222BBB"));

        assertThat(result).containsExactly("1111AAA", "2222BBB", "3333CCC");
    }

    @Test
    @DisplayName("sortedUniquePlates_withEmptyList_returnsEmptyTreeSet")
    void sortedUniquePlates_withEmptyList_returnsEmptyTreeSet() {
        assertThat(M07SortedUniquePlates.sortedUniquePlates(List.of())).isEmpty();
    }
}
