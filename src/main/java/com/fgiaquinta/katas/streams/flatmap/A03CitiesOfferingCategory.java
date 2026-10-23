package com.fgiaquinta.katas.streams.flatmap;

import com.fgiaquinta.katas.domain.Branch;
import com.fgiaquinta.katas.domain.VehicleCategory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
 * No flatMap here because I need to stay at branch level to get the city; after flattening to vehicles the branch, and its city, would be gone.
 * The inner stream asks each branch's fleet a yes-or-no question: anyMatch returns a boolean, true if there is a vehicle with the matching category, false otherwise, and it stops reading the fleet at the first true.
 * filter uses that boolean: when it receives true it passes the current branch downstream, when false the branch is dropped.
 * toSet collapses the two "Madrid" by trying to add them to an internal HashSet that does not allow duplicates: hashCode, computed from the content of the String, gives a number that picks a box in the set, and only if that box already has something inside does the set call equals to check whether it is the same city.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class A03CitiesOfferingCategory {

    private A03CitiesOfferingCategory() {
    }

    public static Set<String> citiesOffering(List<Branch> branches, VehicleCategory category) {
        return branches.stream().filter(branch ->
                branch.fleet().stream()
                        .anyMatch(vehicle -> category.equals(vehicle.category()))).map(Branch::city)
                .collect(Collectors.toSet());
    }
}
