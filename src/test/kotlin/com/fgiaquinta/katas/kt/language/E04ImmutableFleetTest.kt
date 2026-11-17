package com.fgiaquinta.katas.kt.language

import com.fgiaquinta.katas.domain.Aircraft
import com.fgiaquinta.katas.domain.AircraftCategory.NARROW_BODY
import com.fgiaquinta.katas.domain.AircraftCategory.REGIONAL
import com.fgiaquinta.katas.domain.EngineType.PISTON
import com.fgiaquinta.katas.domain.EngineType.TURBOFAN
import com.fgiaquinta.katas.domain.TestData.aircraft
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class E04ImmutableFleetTest {

    private val a1: Aircraft = aircraft("EC-001", REGIONAL, PISTON, 11, 1000, "30.00")
    private val a2: Aircraft = aircraft("EC-002", NARROW_BODY, TURBOFAN, 180, 1200, "35.00")

    @Test
    @DisplayName("create_thenMutatingSourceList_doesNotAffectFleet")
    fun create_thenMutatingSourceList_doesNotAffectFleet() {
        val source = mutableListOf(a1)
        val fleet = create("Charter Air", source)

        source.add(a2)
        source[0] = a2

        assertThat(fleet.aircraft).containsExactly(a1)
    }

    @Test
    @DisplayName("create_thenMutatingReturnedListThroughCast_doesNotAffectFleet")
    fun create_thenMutatingReturnedListThroughCast_doesNotAffectFleet() {
        val fleet = create("Charter Air", listOf(a1))

        try {
            @Suppress("UNCHECKED_CAST")
            (fleet.aircraft as MutableList<Aircraft>).add(a2)
        } catch (acceptedWayOfRefusing: UnsupportedOperationException) {
            // An unmodifiable result is as good as a copy.
        } catch (acceptedWayOfRefusing: ClassCastException) {
            // So is a type that cannot be cast to a mutable list.
        }

        assertThat(fleet.aircraft).containsExactly(a1)
        assertThat(fleet.owner).isEqualTo("Charter Air")
    }

    @Test
    @DisplayName("val_withMutableListProperty_freezesReferenceNotContents")
    fun val_withMutableListProperty_freezesReferenceNotContents() {
        data class Naive(val list: MutableList<String>)
        val naive = Naive(mutableListOf("EC-A"))

        naive.list.add("EC-B")

        assertThat(naive.list).containsExactly("EC-A", "EC-B")
    }
}
