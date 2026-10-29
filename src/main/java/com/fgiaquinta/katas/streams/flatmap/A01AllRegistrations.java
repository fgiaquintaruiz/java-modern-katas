package com.fgiaquinta.katas.streams.flatmap;

import com.fgiaquinta.katas.domain.Airport;
import com.fgiaquinta.katas.domain.Aircraft;

import java.util.ArrayList;
import java.util.List;

/**
 * Exercise A01 — All registrations.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Airport}, flatten the fleets of all airports into a single list of registrations.
 * This is the canonical one-to-many flattening exercise: airports map to fleets, and fleets map to registrations.
 *
 * <h2>Concepts</h2>
 * {@code flatMap} as a one-to-many mapping; the inner stream is opened and closed per element; encounter order survives flattening.
 *
 * <h2>Internals</h2>
 * Nothing in the pipeline starts moving until it reaches the terminal operation, toList; the terminal operation is the trigger.
 * flatMap removes the fleet lists and flattens them, so registrations come out of it one at a time, directly, instead of a list of fleets.
 * One registration travels all the way down, goes through the map and then goes through the peek, one at a time, all the way to the end.
 * When an airport is done, its inner stream is closed.
 * <h2>Anchor</h2>
 * Panama canal: the inner stream is the higher water level, the door opens, the boat goes into the lock and passes to the outer stream one by one, and the lock closes before the next boat.
 */
public final class A01AllRegistrations {

    private A01AllRegistrations() {
    }

    public static List<String> allRegistrations(List<Airport> airports) {
        List<String> allRegistrations = new ArrayList<>();
        if (!airports.isEmpty()) {
            allRegistrations.addAll(airports.stream()
                    .flatMap(airport -> airport.fleet().stream()
                            .map(Aircraft::registration))
                    .toList());
        }
        return allRegistrations;
    }
}
