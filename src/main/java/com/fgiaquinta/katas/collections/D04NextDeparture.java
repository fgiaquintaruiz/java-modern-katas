package com.fgiaquinta.katas.collections;

import com.fgiaquinta.katas.domain.Charter;

import java.time.LocalTime;
import java.util.Map;
import java.util.Optional;

/**
 * Exercise D04 — Departure neighbours.
 *
 * <h2>Statement</h2>
 * Given a departure schedule (departure time to charter, in no particular order) and a time, return the
 * first charter departing at or after that time and the last charter departing strictly before it.
 * Either side is empty when there is none.
 *
 * <h2>Concepts</h2>
 * {@code TreeMap} sorted by key; {@code ceilingEntry} (at or after) versus {@code higherEntry}; {@code floorEntry} (at or before) versus {@code lowerEntry}; the {@code headMap}/{@code tailMap} views with their inclusive flags.
 *
 * <h2>Internals</h2>
 * TODO: fill after solving.
 * <h2>Anchor</h2>
 * TODO: your own image, written by you.
 */
public final class D04NextDeparture {

    private D04NextDeparture() {
    }

    public record Neighbours(Optional<Charter> previous, Optional<Charter> next) {
    }

    public static Neighbours neighbours(Map<LocalTime, Charter> schedule, LocalTime at) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
