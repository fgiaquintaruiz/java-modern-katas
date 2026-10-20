package com.fgiaquinta.katas.streams.flatmap;

import com.fgiaquinta.katas.domain.Branch;
import com.fgiaquinta.katas.domain.Vehicle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Exercise A02 — Distinct features sorted.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Branch}, collect the distinct feature tags of every vehicle across all
 * branches, sorted alphabetically. Two levels of nesting must be flattened: branches into vehicles,
 * and vehicles into feature tags.
 *
 * <h2>Concepts</h2>
 * two chained {@code flatMap} calls (branches to vehicles, vehicles to features); {@code distinct()} is a stateful buffering operation; {@code sorted()} is a full barrier.
 *
 * <h2>Internals</h2>
 * The flatMaps are converting the fleet list and the features list: instead of having a list of lists they become just a list, they literally flatten. We need two of them because of the number of lists: in the branches we have fleets and in the vehicles we have features, so we need to flatten both of them.
 * distinct adds each feature to its internal HashSet, which evaluates it: if it is not in the HashSet it actually adds it. The one that is added continues downstream; the other one does not.
 * sorted needs to receive all the elements before applying the sorting, otherwise it won't know if a soon-to-arrive element will require rearranging the order, and it would send the wrong element downstream if the soon-to-arrive one modifies the order.
 * distinct and sorted need to be in the outer stream to apply to the whole set of data before going into the terminal operation; if they sit in the inner stream they only apply to the partial result per vehicle, not to the whole list of features of all vehicles.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class A02DistinctFeaturesSorted {

    private A02DistinctFeaturesSorted() {
    }

    public static List<String> distinctFeaturesSorted(List<Branch> branches) {
        List<String> distinctFeaturesSorted = new ArrayList<>();
        if (!branches.isEmpty()) {
            distinctFeaturesSorted.addAll(
                    branches.stream()
                    .flatMap(branch -> branch.fleet().stream()
                            .flatMap(vehicle -> vehicle.features().stream())
//                                    .map(Vehicle::features).flatMap(List::stream)
                    )
                    .peek(feature -> System.out.printf("bd: %s ", feature))
                    .distinct()
                    .peek(feature -> System.out.printf("ad-bs: %s ", feature))
                    .sorted()
                    .peek(feature -> System.out.printf("as: %s ", feature)).toList()
            );
        }
        return distinctFeaturesSorted;
    }
}
