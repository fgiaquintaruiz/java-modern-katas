package com.fgiaquinta.katas.streams.grouping;

import com.fgiaquinta.katas.domain.Aircraft;
import com.fgiaquinta.katas.domain.Airport;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
 * The filter is an intermediate operation whose contract is to let through the elements that match the
 * predicate; it runs before collect to drop the airports without aircraft, so they are not added to the
 * grouping as an empty bucket. The bucket is created in the accumulator with {@code computeIfAbsent},
 * keyed by the airport city. The lambda returns a stream of registrations, the flatMapping accumulator
 * walks it, {@code accept} puts each one in, and the stream is closed once it is drained. madrid2 gets the
 * existing list, and {@code add} puts EC-P04 at the end of it.
 * <h2>Anchor</h2>
 * In a wholesale supermarket I go through the big boxes on the shelf, choose only the ones I want, open
 * each big box, take out the product boxes inside and group them by flavour using the barcode.
 */
public final class B02RegistrationsByCity {

    private B02RegistrationsByCity() {
    }

    public static Map<String, List<String>> registrationsByCity(List<Airport> airports) {
        return airports.stream()
                .filter(airport -> !airport.fleet().isEmpty())
                .collect(Collectors.groupingBy(Airport::city,
                        Collectors.flatMapping(airport -> airport.fleet().stream()
                                .map(Aircraft::registration), Collectors.toList())
                ));
    }
}
