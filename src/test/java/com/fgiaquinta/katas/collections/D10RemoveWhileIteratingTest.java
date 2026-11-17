package com.fgiaquinta.katas.collections;

import com.fgiaquinta.katas.domain.Aircraft;
import com.fgiaquinta.katas.domain.AircraftCategory;
import com.fgiaquinta.katas.domain.Airport;
import com.fgiaquinta.katas.domain.Charter;
import com.fgiaquinta.katas.domain.Customer;
import com.fgiaquinta.katas.domain.EngineType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import static com.fgiaquinta.katas.domain.TestData.aircraft;
import static com.fgiaquinta.katas.domain.TestData.airport;
import static com.fgiaquinta.katas.domain.TestData.charter;
import static com.fgiaquinta.katas.domain.TestData.customer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class D10RemoveWhileIteratingTest {

    private final Customer ana = customer("C1", "Ana", "Spain", true);
    private final Aircraft plane = aircraft("EC-001", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 1000, "30.00");
    private final Airport madrid = airport("MAD", "Madrid", "Spain", plane);

    private final Charter oneDay = charter("R1", ana, plane, madrid, madrid, "2024-01-01", "2024-01-02", "30.00");
    private final Charter twoDays = charter("R2", ana, plane, madrid, madrid, "2024-01-01", "2024-01-03", "60.00");
    private final Charter threeDays = charter("R3", ana, plane, madrid, madrid, "2024-01-01", "2024-01-04", "90.00");
    private final Charter oneDayAgain = charter("R4", ana, plane, madrid, madrid, "2024-02-01", "2024-02-02", "30.00");

    private List<Charter> mutableList() {
        return new ArrayList<>(List.of(oneDay, twoDays, threeDays, oneDayAgain));
    }

    @Test
    @DisplayName("demonstration: removing inside a for-each throws ConcurrentModificationException")
    void forEachRemoval_withMatchingFirstElement_throwsConcurrentModification() {
        List<Charter> charters = mutableList();

        assertThatThrownBy(() -> {
            for (Charter c : charters) {
                if (c.days() < 2) {
                    charters.remove(c);
                }
            }
        }).isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    @DisplayName("removes the short charters in place, keeps the exact-threshold ones and the order")
    void removeShorterThan_withMixedDurations_removesOnlyShorterOnes() {
        List<Charter> charters = mutableList();

        List<Charter> result = D10RemoveWhileIterating.removeShorterThan(charters, 2);

        assertThat(result).isSameAs(charters);
        assertThat(result).containsExactly(twoDays, threeDays);
    }

    @Test
    @DisplayName("removes adjacent matching charters")
    void removeShorterThan_withAdjacentMatches_removesAll() {
        List<Charter> charters = new ArrayList<>(List.of(oneDay, oneDayAgain, threeDays));

        assertThat(D10RemoveWhileIterating.removeShorterThan(charters, 2)).containsExactly(threeDays);
    }

    @Test
    @DisplayName("leaves the list untouched when nothing matches")
    void removeShorterThan_withNoMatch_leavesListUnchanged() {
        List<Charter> charters = mutableList();

        assertThat(D10RemoveWhileIterating.removeShorterThan(charters, 1)).hasSize(4);
    }

    @Test
    @DisplayName("empties the list when everything matches")
    void removeShorterThan_withAllMatching_returnsEmptyList() {
        assertThat(D10RemoveWhileIterating.removeShorterThan(mutableList(), 10)).isEmpty();
    }

    @Test
    @DisplayName("accepts an empty list")
    void removeShorterThan_withEmptyList_returnsEmptyList() {
        assertThat(D10RemoveWhileIterating.removeShorterThan(new ArrayList<>(), 2)).isEmpty();
    }
}
