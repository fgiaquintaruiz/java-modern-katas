package com.fgiaquinta.katas.kt.collections

import com.fgiaquinta.katas.domain.AircraftCategory.REGIONAL
import com.fgiaquinta.katas.domain.Charter
import com.fgiaquinta.katas.domain.EngineType.PISTON
import com.fgiaquinta.katas.domain.TestData.aircraft
import com.fgiaquinta.katas.domain.TestData.airport
import com.fgiaquinta.katas.domain.TestData.charter
import com.fgiaquinta.katas.domain.TestData.customer
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class D10RemoveWhileIteratingTest {

    private val ana = customer("C1", "Ana", "Spain", true)
    private val plane = aircraft("EC-001", REGIONAL, PISTON, 11, 1000, "30.00")
    private val madrid = airport("MAD", "Madrid", "Spain", plane)

    private val oneDay = charter("R1", ana, plane, madrid, madrid, "2024-01-01", "2024-01-02", "30.00")
    private val twoDays = charter("R2", ana, plane, madrid, madrid, "2024-01-01", "2024-01-03", "60.00")
    private val threeDays = charter("R3", ana, plane, madrid, madrid, "2024-01-01", "2024-01-04", "90.00")
    private val oneDayAgain = charter("R4", ana, plane, madrid, madrid, "2024-02-01", "2024-02-02", "30.00")

    private fun mutableList(): MutableList<Charter> = mutableListOf(oneDay, twoDays, threeDays, oneDayAgain)

    @Test
    @DisplayName("forEachRemoval_withMatchingFirstElement_throwsConcurrentModification")
    fun forEachRemoval_withMatchingFirstElement_throwsConcurrentModification() {
        val charters = mutableList()

        assertThatThrownBy {
            for (c in charters) {
                if (c.days() < 2) charters.remove(c)
            }
        }.isInstanceOf(ConcurrentModificationException::class.java)
    }

    @Test
    @DisplayName("removeShorterThan_withMixedDurations_removesOnlyShorterOnes")
    fun removeShorterThan_withMixedDurations_removesOnlyShorterOnes() {
        val charters = mutableList()

        val result = removeShorterThan(charters, 2)

        // filter { } would return a new list: the same instance proves the removal happened in place.
        assertThat(result).isSameAs(charters)
        assertThat(result).containsExactly(twoDays, threeDays)
    }

    @Test
    @DisplayName("removeShorterThan_withAdjacentMatches_removesAll")
    fun removeShorterThan_withAdjacentMatches_removesAll() {
        val charters = mutableListOf(oneDay, oneDayAgain, threeDays)

        assertThat(removeShorterThan(charters, 2)).containsExactly(threeDays)
    }

    @Test
    @DisplayName("removeShorterThan_withNoMatch_leavesListUnchanged")
    fun removeShorterThan_withNoMatch_leavesListUnchanged() {
        assertThat(removeShorterThan(mutableList(), 1)).hasSize(4)
    }

    @Test
    @DisplayName("removeShorterThan_withAllMatching_returnsEmptyList")
    fun removeShorterThan_withAllMatching_returnsEmptyList() {
        assertThat(removeShorterThan(mutableList(), 10)).isEmpty()
    }

    @Test
    @DisplayName("removeShorterThan_withEmptyList_returnsEmptyList")
    fun removeShorterThan_withEmptyList_returnsEmptyList() {
        assertThat(removeShorterThan(mutableListOf(), 2)).isEmpty()
    }
}
