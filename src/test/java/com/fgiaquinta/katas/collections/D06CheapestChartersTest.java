package com.fgiaquinta.katas.collections;

import com.fgiaquinta.katas.domain.Aircraft;
import com.fgiaquinta.katas.domain.AircraftCategory;
import com.fgiaquinta.katas.domain.Airport;
import com.fgiaquinta.katas.domain.Charter;
import com.fgiaquinta.katas.domain.Customer;
import com.fgiaquinta.katas.domain.EngineType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import static com.fgiaquinta.katas.domain.TestData.aircraft;
import static com.fgiaquinta.katas.domain.TestData.airport;
import static com.fgiaquinta.katas.domain.TestData.charter;
import static com.fgiaquinta.katas.domain.TestData.customer;
import static org.assertj.core.api.Assertions.assertThat;

class D06CheapestChartersTest {

    private final Customer ana = customer("C1", "Ana", "Spain", true);
    private final Aircraft plane = aircraft("EC-001", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 1000, "30.00");
    private final Airport madrid = airport("MAD", "Madrid", "Spain", plane);

    private Charter priced(String id, String price) {
        return charter(id, ana, plane, madrid, madrid, "2024-01-01", "2024-01-02", price);
    }

    // Descending prices: offering them in this order leaves the heap array as 10, 20, 40, 50, 30.
    private List<Charter> descending() {
        return List.of(priced("R50", "50.00"), priced("R40", "40.00"), priced("R30", "30.00"),
                priced("R20", "20.00"), priced("R10", "10.00"));
    }

    @Test
    @DisplayName("polls the k cheapest charters from cheapest to most expensive")
    void cheapest_withKSmallerThanSize_pollsKCheapestInPriceOrder() {
        PriorityQueue<Charter> queue = D06CheapestCharters.cheapest(descending(), 3);

        List<String> polled = new ArrayList<>();
        while (!queue.isEmpty()) {
            polled.add(queue.poll().id());
        }
        assertThat(polled).containsExactly("R10", "R20", "R30");
    }

    @Test
    @DisplayName("holds every charter when k exceeds the list size")
    void cheapest_withKLargerThanSize_holdsAll() {
        PriorityQueue<Charter> queue = D06CheapestCharters.cheapest(descending(), 10);

        assertThat(queue).hasSize(5);
        assertThat(queue.poll().id()).isEqualTo("R10");
    }

    @Test
    @DisplayName("returns an empty queue for an empty list")
    void cheapest_withEmptyList_returnsEmptyQueue() {
        assertThat(D06CheapestCharters.cheapest(List.of(), 3)).isEmpty();
    }

    @Test
    @DisplayName("demonstration: iterating a heap is not sorted, polling it is")
    void priorityQueue_iterationVersusPolling_onlyPollingIsSorted() {
        PriorityQueue<Charter> heap = new PriorityQueue<>(Comparator.comparing(Charter::totalPrice));
        descending().forEach(heap::offer);

        List<BigDecimal> iterated = heap.stream().map(Charter::totalPrice).toList();
        List<BigDecimal> sorted = iterated.stream().sorted().toList();
        assertThat(iterated).isNotEqualTo(sorted);

        List<BigDecimal> polled = new ArrayList<>();
        while (!heap.isEmpty()) {
            polled.add(heap.poll().totalPrice());
        }
        assertThat(polled).isEqualTo(sorted);
    }
}
