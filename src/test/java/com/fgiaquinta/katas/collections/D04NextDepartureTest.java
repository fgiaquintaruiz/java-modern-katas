package com.fgiaquinta.katas.collections;

import com.fgiaquinta.katas.collections.D04NextDeparture.Neighbours;
import com.fgiaquinta.katas.domain.Aircraft;
import com.fgiaquinta.katas.domain.AircraftCategory;
import com.fgiaquinta.katas.domain.Airport;
import com.fgiaquinta.katas.domain.Charter;
import com.fgiaquinta.katas.domain.Customer;
import com.fgiaquinta.katas.domain.EngineType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static com.fgiaquinta.katas.domain.TestData.aircraft;
import static com.fgiaquinta.katas.domain.TestData.airport;
import static com.fgiaquinta.katas.domain.TestData.charter;
import static com.fgiaquinta.katas.domain.TestData.customer;
import static org.assertj.core.api.Assertions.assertThat;

class D04NextDepartureTest {

    private final Customer ana = customer("C1", "Ana", "Spain", true);
    private final Aircraft plane = aircraft("EC-001", AircraftCategory.REGIONAL, EngineType.PISTON, 11, 1000, "30.00");
    private final Airport madrid = airport("MAD", "Madrid", "Spain", plane);

    private final Charter r1 = charter("R1", ana, plane, madrid, madrid, "2024-01-01", "2024-01-02", "30.00");
    private final Charter r2 = charter("R2", ana, plane, madrid, madrid, "2024-01-01", "2024-01-02", "30.00");
    private final Charter r3 = charter("R3", ana, plane, madrid, madrid, "2024-01-01", "2024-01-02", "30.00");

    private Map<LocalTime, Charter> schedule() {
        Map<LocalTime, Charter> schedule = new HashMap<>();
        schedule.put(LocalTime.of(18, 45), r3);
        schedule.put(LocalTime.of(8, 0), r1);
        schedule.put(LocalTime.of(12, 30), r2);
        return schedule;
    }

    @Test
    @DisplayName("finds the surrounding departures for a time between two of them")
    void neighbours_withTimeBetweenDepartures_returnsPreviousAndNext() {
        Neighbours result = D04NextDeparture.neighbours(schedule(), LocalTime.of(10, 0));

        assertThat(result.previous()).contains(r1);
        assertThat(result.next()).contains(r2);
    }

    @Test
    @DisplayName("treats the next as at-or-after and the previous as strictly before")
    void neighbours_withTimeEqualToDeparture_returnsThatDepartureAsNext() {
        Neighbours result = D04NextDeparture.neighbours(schedule(), LocalTime.of(12, 30));

        assertThat(result.next()).contains(r2);
        assertThat(result.previous()).contains(r1);
    }

    @Test
    @DisplayName("has no previous departure before the first one")
    void neighbours_withTimeBeforeFirstDeparture_returnsEmptyPrevious() {
        Neighbours result = D04NextDeparture.neighbours(schedule(), LocalTime.of(7, 0));

        assertThat(result.previous()).isEmpty();
        assertThat(result.next()).contains(r1);
    }

    @Test
    @DisplayName("has no next departure after the last one")
    void neighbours_withTimeAfterLastDeparture_returnsEmptyNext() {
        Neighbours result = D04NextDeparture.neighbours(schedule(), LocalTime.of(19, 0));

        assertThat(result.next()).isEmpty();
        assertThat(result.previous()).contains(r3);
    }

    @Test
    @DisplayName("returns two empty sides for an empty schedule")
    void neighbours_withEmptySchedule_returnsBothEmpty() {
        Neighbours result = D04NextDeparture.neighbours(Map.of(), LocalTime.NOON);

        assertThat(result.previous()).isEmpty();
        assertThat(result.next()).isEmpty();
    }
}
