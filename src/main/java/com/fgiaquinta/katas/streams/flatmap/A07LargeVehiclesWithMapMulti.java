package com.fgiaquinta.katas.streams.flatmap;

import com.fgiaquinta.katas.domain.Branch;
import com.fgiaquinta.katas.domain.Vehicle;

import java.util.List;

/**
 * Exercise A07 — Large vehicles with {@code mapMulti}.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Branch} and a minimum seat count, return every vehicle across all branches
 * whose {@code seats} is greater than or equal to {@code minSeats}. This exercise MUST be implemented
 * with {@link java.util.stream.Stream#mapMulti}; a {@code flatMap}-based solution is NOT accepted,
 * even though it produces the same result.
 *
 * <h2>Concepts</h2>
 * {@code mapMulti} (Java 16) pushes elements downstream without allocating an inner {@code Stream}; it beats {@code flatMap} for small or empty inner groups; the {@code <Vehicle>mapMulti(...)} type-witness quirk.
 *
 * <h2>Internals</h2>
 * TODO after solving: explain what happens inside the pipeline, not what the code does.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class A07LargeVehiclesWithMapMulti {

    private A07LargeVehiclesWithMapMulti() {
    }

    public static List<Vehicle> largeVehicles(List<Branch> branches, int minSeats) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
