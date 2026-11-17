package com.fgiaquinta.katas.collections;

import com.fgiaquinta.katas.collections.D08MutableKeyLookup.MutableRoute;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class D08MutableKeyLookupTest {

    @Test
    @DisplayName("demonstration: a mutable key renamed after insertion is lost, though still iterated")
    void hashSet_withMutatedKey_failsToFindItButStillIterates() {
        Set<MutableRoute> broken = new HashSet<>();
        MutableRoute route = new MutableRoute("MAD-BCN");
        broken.add(route);

        route.setCode("MAD-SEV");

        assertThat(broken.contains(route)).isFalse();
        assertThat(broken).hasSize(1);
        assertThat(broken.iterator().next()).isSameAs(route);
    }

    @Test
    @DisplayName("still finds the route after the rename")
    void containsAfterRename_withRename_returnsTrue() {
        MutableRoute route = new MutableRoute("MAD-BCN");

        assertThat(D08MutableKeyLookup.containsAfterRename(route, "MAD-SEV")).isTrue();
    }

    @Test
    @DisplayName("applies the rename to the route it was given")
    void containsAfterRename_withRename_changesTheRoute() {
        MutableRoute route = new MutableRoute("MAD-BCN");

        D08MutableKeyLookup.containsAfterRename(route, "MAD-SEV");

        assertThat(route.getCode()).isEqualTo("MAD-SEV");
    }

    @Test
    @DisplayName("finds the route when the new code equals the old one")
    void containsAfterRename_withSameCode_returnsTrue() {
        assertThat(D08MutableKeyLookup.containsAfterRename(new MutableRoute("MAD-BCN"), "MAD-BCN")).isTrue();
    }
}
