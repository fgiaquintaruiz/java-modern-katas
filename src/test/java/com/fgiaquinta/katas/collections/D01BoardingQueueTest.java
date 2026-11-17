package com.fgiaquinta.katas.collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class D01BoardingQueueTest {

    @Test
    @DisplayName("returns the last n boarded passengers, most recent first")
    void lastBoardedFirst_withFourPassengersAndTwo_returnsLastTwoReversed() {
        List<String> boarding = List.of("Ana", "Bea", "Carlos", "Dana");

        assertThat(D01BoardingQueue.lastBoardedFirst(boarding, 2)).containsExactly("Dana", "Carlos");
    }

    @Test
    @DisplayName("returns every passenger reversed when n exceeds the queue size")
    void lastBoardedFirst_withNLargerThanQueue_returnsAllReversed() {
        List<String> boarding = List.of("Ana", "Bea", "Carlos");

        assertThat(D01BoardingQueue.lastBoardedFirst(boarding, 10)).containsExactly("Carlos", "Bea", "Ana");
    }

    @Test
    @DisplayName("returns an empty list when n is zero")
    void lastBoardedFirst_withZero_returnsEmpty() {
        assertThat(D01BoardingQueue.lastBoardedFirst(List.of("Ana", "Bea"), 0)).isEmpty();
    }

    @Test
    @DisplayName("returns an empty list when nobody boards")
    void lastBoardedFirst_withEmptyList_returnsEmpty() {
        assertThat(D01BoardingQueue.lastBoardedFirst(List.of(), 3)).isEmpty();
    }

    @Test
    @DisplayName("keeps duplicated names as separate passengers and leaves an immutable input untouched")
    void lastBoardedFirst_withDuplicateNames_keepsBoth() {
        List<String> boarding = List.of("Ana", "Bea", "Ana");

        assertThat(D01BoardingQueue.lastBoardedFirst(boarding, 3)).containsExactly("Ana", "Bea", "Ana");
    }
}
