package com.fgiaquinta.katas.functional.methodref;

import java.util.List;
import java.util.Optional;

/**
 * Exercise M02 — Largest reading.
 *
 * <h2>Statement</h2>
 * Return the largest flight-hours reading, or an empty {@code Optional} when there are none. A static
 * method reference to a two-argument method is REQUIRED as the reducer; a lambda is not accepted.
 *
 * <h2>Concepts</h2>
 * static method reference ({@code Math::max}) as a {@code BinaryOperator<Integer>}.
 *
 * <h2>Internals</h2>
 * TODO after solving: explain what happens inside the pipeline, not what the code does.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class M02LargestReading {

    private M02LargestReading() {
    }

    public static Optional<Integer> largestReading(List<Integer> flightHourReadings) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
