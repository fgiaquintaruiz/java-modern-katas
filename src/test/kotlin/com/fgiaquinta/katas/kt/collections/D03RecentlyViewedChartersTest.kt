package com.fgiaquinta.katas.kt.collections

import com.fgiaquinta.katas.domain.AircraftCategory.REGIONAL
import com.fgiaquinta.katas.domain.Charter
import com.fgiaquinta.katas.domain.EngineType.PISTON
import com.fgiaquinta.katas.domain.TestData.aircraft
import com.fgiaquinta.katas.domain.TestData.airport
import com.fgiaquinta.katas.domain.TestData.charter
import com.fgiaquinta.katas.domain.TestData.customer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class D03RecentlyViewedChartersTest {

    private val ana = customer("C1", "Ana", "Spain", true)
    private val plane = aircraft("EC-001", REGIONAL, PISTON, 11, 1000, "30.00")
    private val madrid = airport("MAD", "Madrid", "Spain", plane)

    private fun charterWithId(id: String): Charter =
        charter(id, ana, plane, madrid, madrid, "2024-01-01", "2024-01-02", "30.00")

    private fun ids(capacity: Int, vararg viewed: String): List<String> =
        viewOrder(capacity, viewed.map { charterWithId(it) }).keys.toList()

    @Test
    @DisplayName("viewOrder_withinCapacity_returnsViewOrder")
    fun viewOrder_withinCapacity_returnsViewOrder() {
        assertThat(ids(3, "R1", "R2", "R3")).containsExactly("R1", "R2", "R3")
    }

    @Test
    @DisplayName("viewOrder_withRepeatedView_movesItToTheEnd")
    fun viewOrder_withRepeatedView_movesItToTheEnd() {
        assertThat(ids(3, "R1", "R2", "R3", "R1")).containsExactly("R2", "R3", "R1")
    }

    @Test
    @DisplayName("viewOrder_overCapacityAfterRepeatedView_evictsLeastRecentlyViewed")
    fun viewOrder_overCapacityAfterRepeatedView_evictsLeastRecentlyViewed() {
        assertThat(ids(3, "R1", "R2", "R3", "R1", "R4")).containsExactly("R3", "R1", "R4")
    }

    @Test
    @DisplayName("viewOrder_withCapacityOne_keepsLastView")
    fun viewOrder_withCapacityOne_keepsLastView() {
        assertThat(ids(1, "R1", "R2", "R2")).containsExactly("R2")
    }

    @Test
    @DisplayName("viewOrder_withNoViews_returnsEmpty")
    fun viewOrder_withNoViews_returnsEmpty() {
        assertThat(ids(3)).isEmpty()
    }

    @Test
    @DisplayName("viewOrder_returnsAnonymousSubclassOfJavaLinkedHashMap") // pragma: allowlist secret
    fun viewOrder_returnsAnonymousSubclassOfJavaLinkedHashMap() {
        val result = viewOrder(2, listOf(charterWithId("R1")))

        // removeEldestEntry can only be overridden through a subclass, so the map is an `object :` expression.
        assertThat(result.javaClass).isNotEqualTo(java.util.LinkedHashMap::class.java)
        assertThat(result.javaClass.superclass).isEqualTo(java.util.LinkedHashMap::class.java)
    }
}
