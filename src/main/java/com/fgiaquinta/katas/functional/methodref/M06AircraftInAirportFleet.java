package com.fgiaquinta.katas.functional.methodref;

import com.fgiaquinta.katas.domain.Airport;
import com.fgiaquinta.katas.domain.Aircraft;

import java.util.List;

/**
 * Exercise M06 — Aircraft in airport fleet.
 *
 * <h2>Statement</h2>
 * Keep only the candidates that belong to the airport fleet, in input order. A bound instance method
 * reference on the airport fleet is REQUIRED as the predicate; a lambda is not accepted.
 *
 * <h2>Concepts</h2>
 * bound instance method reference on an expression result ({@code airport.fleet()::contains}).
 *
 * <h2>Internals</h2>
 * TODO after solving: explain what happens inside the pipeline, not what the code does.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class M06AircraftInAirportFleet {

    private M06AircraftInAirportFleet() {
    }

    public static List<Aircraft> inFleet(List<Aircraft> candidates, Airport airport) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
