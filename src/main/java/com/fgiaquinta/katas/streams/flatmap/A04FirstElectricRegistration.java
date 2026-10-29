package com.fgiaquinta.katas.streams.flatmap;

import com.fgiaquinta.katas.domain.Airport;
import com.fgiaquinta.katas.domain.EngineType;
import com.fgiaquinta.katas.domain.Aircraft;

import java.util.List;
import java.util.Optional;

/**
 * Exercise A04 — First electric registration.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Airport}, scan airports and their fleets in order for the first aircraft
 * whose engine type is {@code ELECTRIC}, and return its registration wrapped in an {@code Optional<String>}.
 * The scan must stop logically at the first match; nothing after it needs to be inspected for the
 * result to be correct.
 *
 * <h2>Concepts</h2>
 * {@code findFirst} as a short-circuiting terminal; {@code flatMap} laziness across the inner stream (eager pre-JDK 10, lazy from JDK 10 on); encounter order and {@code findFirst} versus {@code findAny}.
 *
 * <h2>Internals</h2>
 * filter sees only 3 aircraft in the first test: the third one, EC-E01, receives true from the equals and therefore moves downstream until findFirst, and EC-E02 is never read.
 * The cancellation flag is raised by findFirst the moment it holds an element and is checked by flatMap before pushing the next inner element downstream.
 * Before JDK 10 flatMap ignored that flag and waited for the inner stream to exhaust or drain before the outer stream noticed the cancellation.
 * In sequential there is one worker, so EC-E01 is the result for both findFirst and findAny; in parallel there are many workers, so findAny can return EC-E01 or EC-E02 depending on which worker finds it first, while findFirst still returns EC-E01 because the worker needs to wait for the others.
 * <h2>Anchor</h2>
 * The doorman at the entrance of the disco is flatMap letting people in one by one, the owner sees the people entering and raises a hand to the doorman saying stop there, and so does the doorman.
 */
public final class A04FirstElectricRegistration {

    private A04FirstElectricRegistration() {
    }

    public static Optional<String> firstElectricRegistration(List<Airport> airports) {
        return airports.stream()
                .flatMap(airport -> airport.fleet().stream()
                        .filter(aircraft -> EngineType.ELECTRIC.equals(aircraft.engine()))
                        .map(Aircraft::registration)
                ).findFirst();
    }
}
