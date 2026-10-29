package com.fgiaquinta.katas.streams.reduction;

import com.fgiaquinta.katas.domain.Aircraft;

import java.util.List;

/**
 * Exercise C01 — Total Flight Hours.
 *
 * <h2>Statement</h2>
 * Given a list of {@link Aircraft}, return the sum of their {@code flightHours} values as a single
 * {@code long}.
 *
 * <h2>Concepts</h2>
 * {@code mapToLong} turning a {@code Stream<Aircraft>} into a primitive {@code LongStream}, avoiding boxing; {@code LongStream.sum()} as a specialised {@code reduce} over primitives; primitive streams versus {@code Stream<Long>} and the boxing cost.
 *
 * <h2>Internals</h2>
 * TODO after solving: explain what happens inside the pipeline, not what the code does.
 * <h2>Anchor</h2>
 * TODO after solving: one sentence, your own image from a world you know, that ties this operation to its mechanism.
 */
public final class C01TotalFlightHours {

    private C01TotalFlightHours() {
    }

    public static long totalFlightHours(List<Aircraft> aircraft) {
        throw new UnsupportedOperationException("Not solved yet");
    }
}
