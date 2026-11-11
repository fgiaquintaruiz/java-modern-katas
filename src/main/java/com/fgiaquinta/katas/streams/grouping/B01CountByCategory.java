package com.fgiaquinta.katas.streams.grouping;

import com.fgiaquinta.katas.domain.Aircraft;
import com.fgiaquinta.katas.domain.AircraftCategory;

import java.text.Collator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Exercise B01 — Count aircraft by category.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Aircraft}, return a {@link Map} from each {@link AircraftCategory} present
 * in the list to the count of aircraft in that category.
 *
 * <h2>Concepts</h2>
 * {@code Collectors.groupingBy} with {@code counting()} as the downstream; the default map is a {@code HashMap}; the downstream runs independently per key; the count is typed as {@code Long}, not {@code Integer}.
 *
 * <h2>Internals</h2>
 * The classifier, {@code classifier.apply(t)}, produces the key from the aircraft, and
 * {@code m.computeIfAbsent(key, k -> downstreamSupplier.get())} looks it up. If the key is new, the
 * supplier {@code () -> new long[1]} creates a long array holding zero as the container. On the second
 * aircraft of the same key the container count is incremented in place. When all the aircraft have
 * passed, groupingBy turns each long array into a {@code Long} object in the final map.
 * <h2>Anchor</h2>
 * It behaves like a coin size counter: we get a 1 euro coin, we put it in the 1 euro bucket and
 * increment the counter, and the same for the other coin sizes.
 */
public final class B01CountByCategory {

    private B01CountByCategory() {
    }

    public static Map<AircraftCategory, Long> countByCategory(List<Aircraft> aircraft) {
        return aircraft.stream().collect(Collectors.groupingBy(Aircraft::category, Collectors.counting()));
    }
}
