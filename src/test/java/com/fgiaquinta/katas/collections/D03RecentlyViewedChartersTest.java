package com.fgiaquinta.katas.collections;

import com.fgiaquinta.katas.domain.Aircraft;
import com.fgiaquinta.katas.domain.AircraftCategory;
import com.fgiaquinta.katas.domain.Airport;
import com.fgiaquinta.katas.domain.Charter;
import com.fgiaquinta.katas.domain.Customer;
import com.fgiaquinta.katas.domain.EngineType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.fgiaquinta.katas.domain.TestData.aircraft;
import static com.fgiaquinta.katas.domain.TestData.airport;
import static com.fgiaquinta.katas.domain.TestData.charter;
import static com.fgiaquinta.katas.domain.TestData.customer;
import static org.assertj.core.api.Assertions.assertThat;

class D03RecentlyViewedChartersTest {

    private final Customer ana = customer("C1", "Ana", "Spain", true);
    private final Aircraft plane = aircraft("EC-001", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 1000, "30.00");
    private final Airport madrid = airport("MAD", "Madrid", "Spain", plane);

    private Charter charterWithId(String id) {
        return charter(id, ana, plane, madrid, madrid, "2024-01-01", "2024-01-02", "30.00");
    }

    @Test
    @DisplayName("lists charter ids from least to most recently viewed when nothing is evicted")
    void viewOrder_withinCapacity_returnsViewOrder() {
        List<Charter> views = List.of(charterWithId("R1"), charterWithId("R2"), charterWithId("R3"));

        assertThat(D03RecentlyViewedCharters.viewOrder(3, views)).containsExactly("R1", "R2", "R3");
    }

    @Test
    @DisplayName("moves a charter viewed again to the most recent position")
    void viewOrder_withRepeatedView_movesItToTheEnd() {
        List<Charter> views = List.of(charterWithId("R1"), charterWithId("R2"), charterWithId("R3"), charterWithId("R1"));

        assertThat(D03RecentlyViewedCharters.viewOrder(3, views)).containsExactly("R2", "R3", "R1");
    }

    @Test
    @DisplayName("evicts the least recently viewed charter, not the first inserted one")
    void viewOrder_overCapacityAfterRepeatedView_evictsLeastRecentlyViewed() {
        List<Charter> views = List.of(charterWithId("R1"), charterWithId("R2"), charterWithId("R3"),
                charterWithId("R1"), charterWithId("R4"));

        assertThat(D03RecentlyViewedCharters.viewOrder(3, views)).containsExactly("R3", "R1", "R4");
    }

    @Test
    @DisplayName("keeps only the last view when the capacity is one")
    void viewOrder_withCapacityOne_keepsLastView() {
        List<Charter> views = List.of(charterWithId("R1"), charterWithId("R2"), charterWithId("R2"));

        assertThat(D03RecentlyViewedCharters.viewOrder(1, views)).containsExactly("R2");
    }

    @Test
    @DisplayName("returns an empty list when there are no views")
    void viewOrder_withNoViews_returnsEmpty() {
        assertThat(D03RecentlyViewedCharters.viewOrder(3, List.of())).isEmpty();
    }
}
