package com.fgiaquinta.katas.streams.flatmap;

import com.fgiaquinta.katas.domain.Airport;
import com.fgiaquinta.katas.domain.AircraftCategory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Exercise A03 — Cities offering a category.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Airport} and a {@link AircraftCategory}, return the set of city names of
 * airports that have at least one aircraft of that category in their fleet. Two airports sharing the
 * same city collapse into a single entry, since city identity is what matters here, not airport identity.
 *
 * <h2>Concepts</h2>
 * a nested predicate evaluated over an inner collection; {@code anyMatch} as a short-circuiting terminal on the inner stream; {@code toSet()} versus {@code toCollection(...)} for a specific {@code Set} implementation.
 *
 * <h2>Internals</h2>
 * No flatMap here because I need to stay at airport level to get the city; after flattening to aircraft the airport, and its city, would be gone.
 * The inner stream asks each airport's fleet a yes-or-no question: anyMatch returns a boolean, true if there is an aircraft with the matching category, false otherwise, and it stops reading the fleet at the first true.
 * filter uses that boolean: when it receives true it passes the current airport downstream, when false the airport is dropped.
 * toSet collapses the two "Madrid" by trying to add them to an internal HashSet that does not allow duplicates: hashCode, computed from the content of the String, gives a number that picks a box in the set, and only if that box already has something inside does the set call equals to check whether it is the same city.
 * <h2>Anchor</h2>
 * Airport security finds the first offending bag and stops there, and then tries to put a bag into a luggage bucket that already contains the same bag.
 */
public final class A03CitiesOfferingCategory {

    private A03CitiesOfferingCategory() {
    }

    public static Set<String> citiesOffering(List<Airport> airports, AircraftCategory category) {
        return airports.stream().filter(airport ->
                airport.fleet().stream()
                        .anyMatch(aircraft -> category.equals(aircraft.category()))).map(Airport::city)
                .collect(Collectors.toSet());
    }
}
