package com.fgiaquinta.katas.streams.flatmap;

import com.fgiaquinta.katas.domain.Airport;
import com.fgiaquinta.katas.domain.Aircraft;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Exercise A02 — Distinct features sorted.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Airport}, collect the distinct feature tags of every aircraft across all
 * airports, sorted alphabetically. Two levels of nesting must be flattened: airports into aircraft,
 * and aircraft into feature tags.
 *
 * <h2>Concepts</h2>
 * two chained {@code flatMap} calls (airports to aircraft, aircraft to features); {@code distinct()} is a stateful buffering operation; {@code sorted()} is a full barrier.
 *
 * <h2>Internals</h2>
 * The flatMaps are converting the fleet list and the features list: instead of having a list of lists they become just a list, they literally flatten. We need two of them because of the number of lists: in the airports we have fleets and in the aircraft we have features, so we need to flatten both of them.
 * distinct adds each feature to its internal HashSet, which evaluates it: if it is not in the HashSet it actually adds it. The one that is added continues downstream; the other one does not.
 * sorted needs to receive all the elements before applying the sorting, otherwise it won't know if a soon-to-arrive element will require rearranging the order, and it would send the wrong element downstream if the soon-to-arrive one modifies the order.
 * distinct and sorted need to be in the outer stream to apply to the whole set of data before going into the terminal operation; if they sit in the inner stream they only apply to the partial result per aircraft, not to the whole list of features of all aircraft.
 * <h2>Anchor</h2>
 * A gatekeeper for twins is distinct, and sorted is a packaging machine that has to fill one box of 10 cookies before it can deliver it.
 */
public final class A02DistinctFeaturesSorted {

    private A02DistinctFeaturesSorted() {
    }

    public static List<String> distinctFeaturesSorted(List<Airport> airports) {
        List<String> distinctFeaturesSorted = new ArrayList<>();
        if (!airports.isEmpty()) {
            distinctFeaturesSorted.addAll(
                    airports.stream()
                    .flatMap(airport -> airport.fleet().stream()
                            .flatMap(aircraft -> aircraft.features().stream())
//                                    .map(Aircraft::features).flatMap(List::stream)
                    )
                    .distinct()
                    .sorted()
                    .toList()
            );
        }
        return distinctFeaturesSorted;
    }
}
