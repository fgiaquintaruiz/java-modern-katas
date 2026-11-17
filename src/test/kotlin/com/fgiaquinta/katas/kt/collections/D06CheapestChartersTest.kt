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
import java.util.PriorityQueue

class D06CheapestChartersTest {

    private val ana = customer("C1", "Ana", "Spain", true)
    private val plane = aircraft("EC-001", REGIONAL, PISTON, 11, 1000, "30.00")
    private val madrid = airport("MAD", "Madrid", "Spain", plane)

    private fun priced(id: String, price: String): Charter =
        charter(id, ana, plane, madrid, madrid, "2024-01-01", "2024-01-02", price)

    // Descending prices: offering them in this order leaves the heap array as 10, 20, 40, 50, 30.
    private fun descending(): List<Charter> = listOf(
        priced("R50", "50.00"), priced("R40", "40.00"), priced("R30", "30.00"),
        priced("R20", "20.00"), priced("R10", "10.00"))

    private fun drain(queue: PriorityQueue<Charter>): List<String> =
        generateSequence { queue.poll() }.map { it.id() }.toList()

    @Test
    @DisplayName("cheapest_withKSmallerThanSize_pollsKCheapestInPriceOrder")
    fun cheapest_withKSmallerThanSize_pollsKCheapestInPriceOrder() {
        assertThat(drain(cheapest(descending(), 3))).containsExactly("R10", "R20", "R30")
    }

    @Test
    @DisplayName("cheapest_withKLargerThanSize_holdsAll")
    fun cheapest_withKLargerThanSize_holdsAll() {
        val queue = cheapest(descending(), 10)

        assertThat(queue).hasSize(5)
        assertThat(queue.poll().id()).isEqualTo("R10")
    }

    @Test
    @DisplayName("cheapest_withEmptyList_returnsEmptyQueue")
    fun cheapest_withEmptyList_returnsEmptyQueue() {
        assertThat(cheapest(emptyList(), 3)).isEmpty()
    }

    @Test
    @DisplayName("priorityQueue_iterationVersusPolling_onlyPollingIsSorted")
    fun priorityQueue_iterationVersusPolling_onlyPollingIsSorted() {
        val heap = PriorityQueue<Charter>(compareBy { it.totalPrice() })
        descending().forEach { heap.offer(it) }

        val iterated = heap.map { it.totalPrice() }
        assertThat(iterated).isNotEqualTo(iterated.sorted())

        val polled = generateSequence { heap.poll() }.map { it.totalPrice() }.toList()
        assertThat(polled).isEqualTo(iterated.sorted())
    }
}
