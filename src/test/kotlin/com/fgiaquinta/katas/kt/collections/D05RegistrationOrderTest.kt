package com.fgiaquinta.katas.kt.collections

import com.fgiaquinta.katas.domain.Aircraft
import com.fgiaquinta.katas.domain.AircraftCategory.REGIONAL
import com.fgiaquinta.katas.domain.EngineType.PISTON
import com.fgiaquinta.katas.domain.TestData.aircraft
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class D05RegistrationOrderTest {

    private fun withRegistration(registration: String): Aircraft =
        aircraft(registration, REGIONAL, PISTON, 11, 1000, "30.00")

    private val fleet = listOf("EC-A350", "EC-P12", "EC-ATF", "EC-AL", "EC-ALI").map { withRegistration(it) }

    @Test
    @DisplayName("orderedRegistrations_withMixedLengths_ordersByLengthThenAlphabetically")
    fun orderedRegistrations_withMixedLengths_ordersByLengthThenAlphabetically() {
        assertThat(orderedRegistrations(fleet)).containsExactly("EC-AL", "EC-ALI", "EC-ATF", "EC-P12", "EC-A350")
    }

    @Test
    @DisplayName("orderedRegistrations_navigation_followsComparatorOrder")
    fun orderedRegistrations_navigation_followsComparatorOrder() {
        val result = orderedRegistrations(fleet)

        assertThat(result.first()).isEqualTo("EC-AL")
        assertThat(result.last()).isEqualTo("EC-A350")
        assertThat(result.higher("EC-ATF")).isEqualTo("EC-P12")
        assertThat(result.lower("EC-ATF")).isEqualTo("EC-ALI")
        assertThat(result.higher("EC-A350")).isNull()
        assertThat(result.lower("EC-AL")).isNull()
    }

    @Test
    @DisplayName("orderedRegistrations_withCaseVariant_collapsesToFirstInserted")
    fun orderedRegistrations_withCaseVariant_collapsesToFirstInserted() {
        val withVariant = listOf("EC-ATF", "ec-atf", "EC-AL").map { withRegistration(it) }

        val result = orderedRegistrations(withVariant)

        assertThat(result).containsExactly("EC-AL", "EC-ATF")
        assertThat(result.contains("ec-atf")).isTrue()
    }

    @Test
    @DisplayName("orderedRegistrations_withEmptyFleet_returnsEmptySet")
    fun orderedRegistrations_withEmptyFleet_returnsEmptySet() {
        assertThat(orderedRegistrations(emptyList())).isEmpty()
    }

    @Test
    @DisplayName("orderedRegistrations_returnsJavaTreeSet")
    fun orderedRegistrations_returnsJavaTreeSet() {
        // sortedSetOf / toSortedSet produce a java.util.TreeSet behind the SortedSet type.
        assertThat(orderedRegistrations(fleet)).isInstanceOf(java.util.TreeSet::class.java)
    }
}
