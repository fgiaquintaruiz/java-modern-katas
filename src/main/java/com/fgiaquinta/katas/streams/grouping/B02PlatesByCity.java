package com.fgiaquinta.katas.streams.grouping;

import com.fgiaquinta.katas.domain.Branch;

import java.util.List;
import java.util.Map;

/**
 * Exercise B02 — Plates grouped by city.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Branch}, return a {@link Map} from each city with at least one vehicle to
 * the list of plates of all vehicles parked in branches of that city. Branches that share the same
 * city merge their plates into that city's single list.
 *
 * <h2>Concepts</h2>
 * {@code flatMap} into (city, plate) pairs, then {@code groupingBy} with a {@code mapping()} downstream; the per-key list is created lazily; encounter order survives inside each group.
 *
 * <h2>Internals</h2>
 * TODO after solving: explain what happens inside the pipeline, not what the code does.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class B02PlatesByCity {

    private B02PlatesByCity() {
    }

    public static Map<String, List<String>> platesByCity(List<Branch> branches) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
