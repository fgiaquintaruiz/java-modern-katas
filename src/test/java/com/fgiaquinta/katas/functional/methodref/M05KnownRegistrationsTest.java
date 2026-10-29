package com.fgiaquinta.katas.functional.methodref;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class M05KnownRegistrationsTest {

    @Test
    @DisplayName("knownRegistrations_withMixedRegistrations_keepsOnlyKnownInInputOrder")
    void knownRegistrations_withMixedRegistrations_keepsOnlyKnownInInputOrder() {
        // "EC-CCC" is not in the set; the others keep their input order.
        var result = M05KnownRegistrations.knownRegistrations(
                List.of("EC-BBB", "EC-CCC", "EC-AAA"), Set.of("EC-AAA", "EC-BBB"));

        assertThat(result).containsExactly("EC-BBB", "EC-AAA");
    }

    @Test
    @DisplayName("knownRegistrations_withEmptyKnownSet_returnsEmptyList")
    void knownRegistrations_withEmptyKnownSet_returnsEmptyList() {
        assertThat(M05KnownRegistrations.knownRegistrations(List.of("EC-AAA"), Set.of())).isEmpty();
    }
}
