package com.fgiaquinta.katas.streams.flatmap;

import com.fgiaquinta.katas.domain.Branch;
import com.fgiaquinta.katas.domain.VehicleCategory;

import java.util.List;
import java.util.Set;

/**
 * Exercise A03 — Cities offering a category.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Branch} and a {@link VehicleCategory}, return the set of city names of
 * branches that have at least one vehicle of that category in their fleet. Two branches sharing the
 * same city collapse into a single entry, since city identity is what matters here, not branch identity.
 *
 * <h2>Concepts</h2>
 * a nested predicate evaluated over an inner collection; {@code anyMatch} as a short-circuiting terminal on the inner stream; {@code toSet()} versus {@code toCollection(...)} for a specific {@code Set} implementation.
 *
 * <h2>Internals</h2>
 * TODO after solving: explain what happens inside the pipeline, not what the code does.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class A03CitiesOfferingCategory {

    private A03CitiesOfferingCategory() {
    }

    public static Set<String> citiesOffering(List<Branch> branches, VehicleCategory category) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
