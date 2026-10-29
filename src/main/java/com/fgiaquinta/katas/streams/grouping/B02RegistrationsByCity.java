package com.fgiaquinta.katas.streams.grouping;

import com.fgiaquinta.katas.domain.Airport;

import java.util.List;
import java.util.Map;

/**
 * Exercise B02 — Registrations grouped by city.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Airport}, return a {@link Map} from each city with at least one aircraft to
 * the list of registrations of all aircraft parked in airports of that city. Airports that share the same
 * city merge their registrations into that city's single list.
 *
 * <h2>Concepts</h2>
 * {@code flatMap} into (city, registration) pairs, then {@code groupingBy} with a {@code mapping()} downstream; the per-key list is created lazily; encounter order survives inside each group.
 *
 * <h2>Internals</h2>
 * TODO after solving: explain what happens inside the pipeline, not what the code does.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class B02RegistrationsByCity {

    private B02RegistrationsByCity() {
    }

    public static Map<String, List<String>> registrationsByCity(List<Airport> airports) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
