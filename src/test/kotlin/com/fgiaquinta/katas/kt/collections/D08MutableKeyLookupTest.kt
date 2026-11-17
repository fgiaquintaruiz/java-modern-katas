package com.fgiaquinta.katas.kt.collections

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class D08MutableKeyLookupTest {

    @Test
    @DisplayName("hashSet_withMutatedDataClassKey_failsToFindItButStillIterates")
    fun hashSet_withMutatedDataClassKey_failsToFindItButStillIterates() {
        val route = MutableRoute("MAD-BCN")
        val broken = hashSetOf(route)

        route.code = "MAD-SEV"

        assertThat(broken.contains(route)).isFalse()
        assertThat(broken).hasSize(1)
        assertThat(broken.first()).isSameAs(route)
    }

    @Test
    @DisplayName("containsAfterRename_withRename_returnsTrue")
    fun containsAfterRename_withRename_returnsTrue() {
        assertThat(containsAfterRename(MutableRoute("MAD-BCN"), "MAD-SEV")).isTrue()
    }

    @Test
    @DisplayName("containsAfterRename_withRename_changesTheRoute")
    fun containsAfterRename_withRename_changesTheRoute() {
        val route = MutableRoute("MAD-BCN")

        containsAfterRename(route, "MAD-SEV")

        assertThat(route.code).isEqualTo("MAD-SEV")
    }

    @Test
    @DisplayName("containsAfterRename_withSameCode_returnsTrue")
    fun containsAfterRename_withSameCode_returnsTrue() {
        assertThat(containsAfterRename(MutableRoute("MAD-BCN"), "MAD-BCN")).isTrue()
    }

    @Test
    @DisplayName("dataClass_copyIsEqualToOriginalUntilOneOfThemMutates")
    fun dataClass_copyIsEqualToOriginalUntilOneOfThemMutates() {
        val route = MutableRoute("MAD-BCN")
        val snapshot = route.copy()

        assertThat(snapshot).isEqualTo(route)
        assertThat(snapshot.hashCode()).isEqualTo(route.hashCode())

        route.code = "MAD-SEV"

        assertThat(snapshot).isNotEqualTo(route)
    }
}
