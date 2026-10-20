package com.fgiaquinta.katas.streams.flatmap;

import com.fgiaquinta.katas.domain.Branch;
import com.fgiaquinta.katas.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;

/**
 * Exercise A01 — All plates.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Branch}, flatten the fleets of all branches into a single list of plates.
 * This is the canonical one-to-many flattening exercise: branches map to fleets, and fleets map to plates.
 *
 * <h2>Concepts</h2>
 * {@code flatMap} as a one-to-many mapping; the inner stream is opened and closed per element; encounter order survives flattening.
 *
 * <h2>Internals</h2>
 * Nothing in the pipeline starts moving until it reaches the terminal operation, toList; the terminal operation is the trigger.
 * flatMap removes the fleet lists and flattens them, so plates come out of it one at a time, directly, instead of a list of fleets.
 * One plate travels all the way down, goes through the map and then goes through the peek, one at a time, all the way to the end.
 * When a branch is done, its inner stream is closed.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class A01AllPlates {

    private A01AllPlates() {
    }

    public static List<String> allPlates(List<Branch> branches) {
        List<String> allPlates = new ArrayList<>();
        if (!branches.isEmpty()) {
            allPlates.addAll(branches.stream()
                    .flatMap(branch -> branch.fleet().stream()
                            .map(Vehicle::plate))
                    .toList());
        }
        return allPlates;
    }
}
