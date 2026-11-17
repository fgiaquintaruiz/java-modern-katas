package com.fgiaquinta.katas.kt.collections

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class D01BoardingQueueTest {

    @Test
    @DisplayName("lastBoardedFirst_withFourPassengersAndTwo_returnsLastTwoReversed")
    fun lastBoardedFirst_withFourPassengersAndTwo_returnsLastTwoReversed() {
        val boarding = listOf("Ana", "Bea", "Carlos", "Dana")

        assertThat(lastBoardedFirst(boarding, 2)).containsExactly("Dana", "Carlos")
    }

    @Test
    @DisplayName("lastBoardedFirst_withNLargerThanQueue_returnsAllReversed")
    fun lastBoardedFirst_withNLargerThanQueue_returnsAllReversed() {
        assertThat(lastBoardedFirst(listOf("Ana", "Bea", "Carlos"), 10)).containsExactly("Carlos", "Bea", "Ana")
    }

    @Test
    @DisplayName("lastBoardedFirst_withZero_returnsEmpty")
    fun lastBoardedFirst_withZero_returnsEmpty() {
        assertThat(lastBoardedFirst(listOf("Ana", "Bea"), 0)).isEmpty()
    }

    @Test
    @DisplayName("lastBoardedFirst_withEmptyList_returnsEmpty")
    fun lastBoardedFirst_withEmptyList_returnsEmpty() {
        assertThat(lastBoardedFirst(emptyList(), 3)).isEmpty()
    }

    @Test
    @DisplayName("lastBoardedFirst_withDuplicateNames_keepsBoth")
    fun lastBoardedFirst_withDuplicateNames_keepsBoth() {
        assertThat(lastBoardedFirst(listOf("Ana", "Bea", "Ana"), 3)).containsExactly("Ana", "Bea", "Ana")
    }

    @Test
    @DisplayName("lastBoardedFirst_returnsKotlinDequeWithIndexAccess")
    fun lastBoardedFirst_returnsKotlinDequeWithIndexAccess() {
        val result = lastBoardedFirst(listOf("Ana", "Bea", "Carlos"), 2)

        // kotlin.collections.ArrayDeque is a MutableList: index access works, java.util.ArrayDeque has none.
        assertThat(result).isInstanceOf(kotlin.collections.ArrayDeque::class.java)
        assertThat(result[0]).isEqualTo("Carlos")
        assertThat(result[1]).isEqualTo("Bea")
    }
}
