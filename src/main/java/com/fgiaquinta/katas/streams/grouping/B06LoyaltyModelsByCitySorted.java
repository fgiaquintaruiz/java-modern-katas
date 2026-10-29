package com.fgiaquinta.katas.streams.grouping;

import com.fgiaquinta.katas.domain.Charter;

import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;

/**
 * Exercise B06 — Loyalty members' aircraft models, grouped by city, sorted.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Charter}, return a {@link SortedMap} from each origin city to a
 * {@link SortedSet} of the aircraft models chartered there by loyalty-member customers.
 *
 * <h2>Concepts</h2>
 * {@code groupingBy} with {@code TreeMap::new} as the map factory; {@code filtering()} downstream still creates the key, unlike filtering the source before grouping; {@code mapping()} with {@code toCollection(TreeSet::new)} per key.
 *
 * <h2>Internals</h2>
 * TODO after solving: explain what happens inside the pipeline, not what the code does.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class B06LoyaltyModelsByCitySorted {

    private B06LoyaltyModelsByCitySorted() {
    }

    public static SortedMap<String, SortedSet<String>> loyaltyModelsByCity(List<Charter> charters) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
