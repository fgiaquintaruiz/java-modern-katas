package com.fgiaquinta.katas.collections;

import com.fgiaquinta.katas.domain.Airport;

import java.util.List;
import java.util.Map;

/**
 * Exercise D02 — Aircraft per city, first-seen order.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Airport}, return a map from city to the total number of aircraft in the fleets
 * of the airports in that city. Cities must iterate in the order they first appear in the list.
 *
 * <h2>Concepts</h2>
 * {@code LinkedHashMap} insertion order; {@code Collectors.toMap} with a merge function and a map supplier ({@code LinkedHashMap::new}), or {@code Map.merge}; re-putting an existing key does not change its position.
 *
 * <h2>Internals</h2>
 * TODO: fill after solving.
 * <h2>Anchor</h2>
 * TODO: your own image, written by you.
 */
public final class D02FirstSeenCityCounts {

    private D02FirstSeenCityCounts() {
    }

    public static Map<String, Integer> aircraftCountByCity(List<Airport> airports) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
