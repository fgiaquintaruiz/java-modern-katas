package com.fgiaquinta.katas.functional.methodref;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class M05KnownPlatesTest {

    @Test
    @DisplayName("knownPlates_withMixedPlates_keepsOnlyKnownInInputOrder")
    void knownPlates_withMixedPlates_keepsOnlyKnownInInputOrder() {
        // "3333CCC" is not in the set; the others keep their input order.
        var result = M05KnownPlates.knownPlates(
                List.of("2222BBB", "3333CCC", "1111AAA"), Set.of("1111AAA", "2222BBB"));

        assertThat(result).containsExactly("2222BBB", "1111AAA");
    }

    @Test
    @DisplayName("knownPlates_withEmptyKnownSet_returnsEmptyList")
    void knownPlates_withEmptyKnownSet_returnsEmptyList() {
        assertThat(M05KnownPlates.knownPlates(List.of("1111AAA"), Set.of())).isEmpty();
    }
}
